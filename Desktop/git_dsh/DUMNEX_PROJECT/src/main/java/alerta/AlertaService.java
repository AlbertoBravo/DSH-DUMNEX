package alerta;

import database.Jdbc;
import io.swagger.model.Alerta;
import io.swagger.model.Medicion;
import io.swagger.model.Usuario;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AlertaService {

    // Identificador del mensaje a enviar

    public static int id_base = 4096;

    public static Properties prop = null;

    public static Session session = null;

    public int init = init();

    public int init(){

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("albertobravog4@gmail.com", "Bionicle");
            }
        });

        if (session == null){
            return -1;
        }else{
            return 0;
        }

    }



    public static void sendMessage (){

        try {

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress("albertobravog4@gmail.com"));

        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("albravog@alumnos.unex.es"));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }



    public static boolean enviarAlerta (Usuario usuario_par, String mensaje_par, String tipo_par){

        boolean enviada = false;


       // id_mensaje =

        System.out.println("Init sending email");



        // ahora 4097 base 4096
        try {

            int id_mensaje = id_base + Jdbc.obtenerNumerDeAlertas();



            // Funciona, pero faltan mas cosas
            /*

            String path = "https://api.smsarena.es/http/sms.php?auth_key=lwkqOX41c298kfdmiVp9cgd9YFSnYlSa"+
                    "&id="+id_mensaje+"&to="+"34617929906"+"&from=34660871699&text="+"Paciente"+"%20"+
                    usuario_par.getNombre()+"%0D%0A"+"Comprobar%20estado%20de%20salud%20del%20paciente";


            */

            // String replaceString = s1.replaceAll(" ", "%20");

            String mensaje_en = mensaje_par.replaceAll(" ","%20");


            String nombre_en = usuario_par.getNombre().replaceAll(" ","%20");

            String apellidos_en = usuario_par.getApellidos().replaceAll(" ","%20");

            String telefono_en = usuario_par.getTelefono().replaceAll(" ","%20");

            String path = "https://api.smsarena.es/http/sms.php?auth_key=lwkqOX41c298kfdmiVp9cgd9YFSnYlSa"+
                    "&id="+id_mensaje+"&to="+telefono_en+"&from=34660871699&text="+
                    "DUMNEX:%20Alerta%20del%20paciente%20"+
                    nombre_en+"%20"+apellidos_en+"."+"%0D%0A"+mensaje_en+"%0D%0A"+
                    "Se%20necesita%20comprobar%20inmediatamente%20el%20estado%20de%20salud%20del%20paciente.";



            URL url = new URL(path);

            System.out.println(path);

            /*
            URL url = new URL("https://api.smsarena.es/http/sms.php?auth_key=lwkqOX41c298kfdmiVp9cgd9YFSnYlSa&id=4096" +
                    "&to=34648448058&from=34660871699&text=hola%20nito%0AAlberto%0D%0APD");
            */

            // %0D%0A

            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            // con.setRequestMethod("GET");
            con.setRequestMethod("GET");

            System.out.println(con.getResponseMessage());

            enviada = true;

            // Se inserta en la base de datos la alerta enviada

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date actualDate = new Date();
            System.out.println(dateFormat.format(actualDate)); //2016-11-16 12:08:43

            String fecha_en = dateFormat.format(actualDate);

            Jdbc.insertarAlerta(new Alerta(usuario_par.getUsuarioId(),id_mensaje,fecha_en,tipo_par,mensaje_par));

            /*

            Jdbc.insertarAlerta(new Alerta("12345678A",4097,"2020-11-09","P",
                   "Valores de pulso (45) fuera de rango"));

             */

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finish sending email");

        return enviada;
    }


    public static void main(String[] args) {

        /*
        System.out.println("Init sending email");
        sendMessage();
        System.out.println("Finish sending email");
        */

        /*

        String s1 = "javatpoint is a very good website";

        //replaces all occurrences of ' ' to 'e'

        String replaceString = s1.replaceAll(" ", "%20");

        System.out.println(replaceString);

        */

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016-11-16 12:08:43


    }

}
