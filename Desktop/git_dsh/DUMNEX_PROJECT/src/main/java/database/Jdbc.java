package database;


import com.sun.image.codec.jpeg.JPEGDecodeParam;
import io.swagger.model.Alerta;
import io.swagger.model.Medicion;
import io.swagger.model.Mediciones;
import io.swagger.model.Usuario;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {


    // private Connection connect = null;
    // private Statement statement = null;
    // private PreparedStatement preparedStatement = null;
    // private ResultSet resultSet = null;

    public static Connection connection;

    static {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*

    public Jdbc() throws SQLException {

        connection = getConnection();

    }

    */


    public static Connection getConnection() throws SQLException {

            Connection conn = null;

            try {
                /*
                conn =
                        DriverManager.getConnection("Jdbc:mysql://localhost/dumnex?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" +
                                "user=root&password=admin");

                                */

                // Class.forName("com.mysql.Jdbc.Driver");
                conn = DriverManager.getConnection("Jdbc:mysql://localhost/dumnex?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin");

                // Do something with the Connection

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        return conn;

    }



    public static void readDataBase() throws Exception {
        try {
            /*
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.Jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("Jdbc:mysql://localhost/feedback?"
                            + "user=sqluser&password=sqluserpw");



            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL quer

             */


            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            // Connection connection = getConnection();

            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("select * from dumnex.usuarios");
            // writeResultSet(resultSet);





            System.out.println("The columns in the table are: ");

            System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
            for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
            }


            /*
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

            preparedStatement = connect
                    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            resultSet = preparedStatement.executeQuery();
            // writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = connect
                    .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("select * from feedback.comments");
            // writeMetaData(resultSet);

            */

        } catch (Exception e) {
            throw e;
        } finally {
            // close();
        }

    }


    /*
    Métodos para CRUD de los Usuarios

     */

    public static void mostrarTodosLosUsuarios() throws Exception {


        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        // Connection connection = getConnection();

        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.usuarios");

        while (rset.next()) {
            System.out.println("DNI: "+rset.getString(1));

            System.out.println("Nombre: "+rset.getString(2));

            System.out.println("Apellidos: "+rset.getString(3));

            System.out.println("Email: "+rset.getString(4));

            System.out.println("Teléfono: "+rset.getString(5));

            System.out.println("Localidad: "+rset.getString(6));

            System.out.println("--------------------------------------");

        }

    }


    public static List<Usuario> obtenerTodosLosUsuarios() throws Exception {


        List<Usuario> usuarios = new ArrayList<Usuario>();


        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni = "";
        String nombre = "";
        String apellidos = "";
        String email = "";
        String telefono = "";
        String localidad = "";

        // Connection connection = getConnection();

        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.usuarios");

        while (rset.next()) {
            System.out.println("DNI: "+rset.getString(1));
            dni = rset.getString(1);

            System.out.println("Nombre: "+rset.getString(2));
            nombre = rset.getString(2);

            System.out.println("Apellidos: "+rset.getString(3));
            apellidos = rset.getString(3);

            System.out.println("Email: "+rset.getString(4));
            email = rset.getString(4);

            System.out.println("Teléfono: "+rset.getString(5));
            telefono = rset.getString(5);

            System.out.println("Localidad: "+rset.getString(6));
            localidad = rset.getString(6);

            System.out.println("--------------------------------------");

            usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

        }

        return usuarios;

    }


    public static Usuario obtenerUsuario(String dni_par) throws Exception {

        Usuario usuario = null;

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni = "";
        String nombre = "";
        String apellidos = "";
        String email = "";
        String telefono = "";
        String localidad = "";

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.usuarios where usuario_id = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {
            System.out.println("DNI: "+rset.getString(1));
            dni = rset.getString(1);

            System.out.println("Nombre: "+rset.getString(2));
            nombre = rset.getString(2);

            System.out.println("Apellidos: "+rset.getString(3));
            apellidos = rset.getString(3);

            System.out.println("Email: "+rset.getString(4));
            email = rset.getString(4);

            System.out.println("Teléfono: "+rset.getString(5));
            telefono = rset.getString(5);

            System.out.println("Localidad: "+rset.getString(6));
            localidad = rset.getString(6);

            System.out.println("--------------------------------------");

           // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            usuario = new Usuario(dni,nombre,apellidos,email,telefono,localidad);

        }

        return usuario;

    }

    public static void mostrarUsuario(String dni_par) throws Exception {


        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;


        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.usuarios where usuario_id = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {
            System.out.println("DNI: "+rset.getString(1));

            System.out.println("Nombre: "+rset.getString(2));

            System.out.println("Apellidos: "+rset.getString(3));

            System.out.println("Email: "+rset.getString(4));

            System.out.println("Teléfono: "+rset.getString(5));

            System.out.println("Localidad: "+rset.getString(6));

            System.out.println("--------------------------------------");


        }

    }


    public static void insertarUsuario(Usuario usuario) throws Exception {

        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        int rset =0;


        String consulta = "";

        String dni = usuario.getUsuarioId();
        String nombre = usuario.getNombre();
        String apellidos = usuario.getApellidos();
        String email = usuario.getEmail();
        String telefono = usuario.getTelefono();
        String localidad = usuario.getLocalidad();

        /*

        consulta ="INSERT INTO SECTORES VALUES ("+codigo+','+"'"+nombre+"'"+','+porcentaje+','+ingresos+")";
			// OJO: Las cadenas en el insert deben ir entre comillas simples ''
			resultado = ps.executeUpdate(consulta); //ExecuteUpdate porque es una insercion.

         */

        statement = connection.createStatement();

        consulta ="INSERT INTO USUARIOS VALUES ("+"'"+dni+"'"+','+"'"+nombre+"'"+','+"'"+apellidos+"'"+','
                +"'"+email+"'"+','+"'"+telefono+"'"+","+"'"+localidad+"'"+")";

        rset = statement.executeUpdate(consulta);

        // Connection connection = getConnection();


        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }


    public static void eliminarUsuario(String dni_par) throws Exception {


        // Statement statement = null;
        Statement statement = null;
        int rset = 0;

        // Necesario ponerle comillas si es un string
        dni_par = "'"+dni_par+"'";

        // Connection connection = getConnection();

        statement = connection.createStatement();

        /*
        consulta ="DELETE FROM SECTORES WHERE CODS = " + numero;
        resultado = ps.executeUpdate(consulta);
        */

        String consulta = "DELETE FROM usuarios WHERE usuario_id = " +dni_par;

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        rset = statement.executeUpdate(consulta);

        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }


    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Métodos para CRUD de las Alertas

     */


    public static void mostrarTodasLasAlertas() throws Exception {


        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        // Connection connection = getConnection();

        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.alertas");

        while (rset.next()) {
            System.out.println("Alerta_id: "+rset.getString(1));

            System.out.println("Usuario_id: "+rset.getString(2));

            System.out.println("Mensaje_id: "+rset.getString(3));

            System.out.println("Tiempo: "+rset.getString(4));

            System.out.println("Tipo: "+rset.getString(5));

            System.out.println("Mensaje: "+rset.getString(6));

            System.out.println("--------------------------------------");

        }

    }


    public static List<Alerta> obtenerTodosLasAlertas() throws Exception {


        // Mediciones mediciones = new Mediciones();

        List<Alerta> alertas = new ArrayList<Alerta>();

        Statement statement = null;

        // PreparedStatement preparedStatement = null;

        ResultSet rset = null;

        String alerta_id = "";
        String usuario_id = "";
        int mensaje_id = 0;
        String tiempo = "";
        String tipoAlerta = "";
        String mensaje = "";

        // Connection connection = getConnection();

        // statement = connection.createStatement();


        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.alertas");

        while (rset.next()) {

            System.out.println("Alerta_id: "+rset.getString(1));
            alerta_id = rset.getString(1);

            System.out.println("Usuario_id: "+rset.getString(2));
            usuario_id = rset.getString(2);

            System.out.println("Mensaje_id: "+rset.getInt(3));
            mensaje_id = rset.getInt(3);

            System.out.println("Tiempo: "+rset.getString(4));
            tiempo = rset.getString(4);

            System.out.println("Tipo: "+rset.getString(5));
            tipoAlerta = rset.getString(5);

            System.out.println("Mensaje: "+rset.getString(6));
            mensaje = rset.getString(6);

            System.out.println("--------------------------------------");

            /*
            String alertaId, String usuarioId,Integer mensaje_id,String fecha, String tipoAlerta,
                 String mensaje

             */
            alertas.add(new Alerta(alerta_id,usuario_id,mensaje_id,tiempo,tipoAlerta,mensaje));

        }


        return alertas;

    }


    public static int obtenerNumerDeAlertas() throws Exception {


        // Mediciones mediciones = new Mediciones();

        List<Alerta> alertas = new ArrayList<Alerta>();

        Statement statement = null;

        // PreparedStatement preparedStatement = null;

        ResultSet rset = null;

        String alerta_id = "";
        String usuario_id = "";
        int mensaje_id = 0;
        String tiempo = "";
        String tipoAlerta = "";
        String mensaje = "";

        // Connection connection = getConnection();

        // statement = connection.createStatement();


        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.alertas");

        while (rset.next()) {

            System.out.println("Alerta_id: "+rset.getString(1));
            alerta_id = rset.getString(1);

            System.out.println("Usuario_id: "+rset.getString(2));
            usuario_id = rset.getString(2);

            System.out.println("Mensaje_id: "+rset.getInt(3));
            mensaje_id = rset.getInt(3);

            System.out.println("Tiempo: "+rset.getString(4));
            tiempo = rset.getString(4);

            System.out.println("Tipo: "+rset.getString(5));
            tipoAlerta = rset.getString(5);

            System.out.println("Mensaje: "+rset.getString(6));
            mensaje = rset.getString(6);

            System.out.println("--------------------------------------");

            /*
            String alertaId, String usuarioId,Integer mensaje_id,String fecha, String tipoAlerta,
                 String mensaje

             */
            alertas.add(new Alerta(alerta_id,usuario_id,mensaje_id,tiempo,tipoAlerta,mensaje));

        }


        return alertas.size();

    }


    public static List<Alerta> obtenerAlertasUsuario(String dni_par) throws Exception {

        List<Alerta> alertas = new ArrayList<Alerta>();

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String alerta_id = "";
        String usuario_id = "";
        int mensaje_id = 0;
        String tiempo = "";
        String tipoAlerta = "";
        String mensaje = "";

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.alertas where usuario = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        while (rset.next()) {

            System.out.println("Alerta_id: "+rset.getString(1));
            alerta_id = rset.getString(1);

            System.out.println("Usuario_id: "+rset.getString(2));
            usuario_id = rset.getString(2);

            System.out.println("Mensaje_id: "+rset.getInt(3));
            mensaje_id = rset.getInt(3);

            System.out.println("Tiempo: "+rset.getString(4));
            tiempo = rset.getString(4);

            System.out.println("Tipo: "+rset.getString(5));
            tipoAlerta = rset.getString(5);

            System.out.println("Mensaje: "+rset.getString(6));
            mensaje = rset.getString(6);

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            alertas.add(new Alerta(alerta_id,usuario_id,mensaje_id,tiempo,tipoAlerta,mensaje));

        }

        return alertas;

    }

    public static Alerta obtenerAlerta(int alerta_id_par) throws Exception {

        Alerta alerta = null;

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String alerta_id = "";
        String usuario_id = "";
        int mensaje_id = 0;
        String tiempo = "";
        String tipoAlerta = "";
        String mensaje = "";

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.alertas where alerta_id = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setInt(1,alerta_id_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {

            System.out.println("Alerta_id: "+rset.getString(1));
            alerta_id = rset.getString(1);

            System.out.println("Usuario_id: "+rset.getString(2));
            usuario_id = rset.getString(2);

            System.out.println("Mensaje_id: "+rset.getInt(3));
            mensaje_id = rset.getInt(3);

            System.out.println("Tiempo: "+rset.getString(4));
            tiempo = rset.getString(4);

            System.out.println("Tipo: "+rset.getString(5));
            tipoAlerta = rset.getString(5);

            System.out.println("Mensaje: "+rset.getString(6));
            mensaje = rset.getString(6);

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            alerta = new Alerta(alerta_id,usuario_id,mensaje_id,tiempo,tipoAlerta,mensaje);

        }

        return alerta;

    }

    public static void mostrarAlertasUsuario(String dni_par) throws Exception {


        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;


        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.alertas where usuario = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        while (rset.next()) {
            System.out.println("Alerta_id: "+rset.getString(1));

            System.out.println("Usuario_id: "+rset.getString(2));

            System.out.println("Mensaje_id: "+rset.getInt(2));

            System.out.println("Tiempo: "+rset.getString(3));

            System.out.println("Tipo: "+rset.getString(4));

            System.out.println("Mensaje: "+rset.getString(5));


            System.out.println("--------------------------------------");


        }

    }


    public static void insertarAlerta(Alerta alerta) throws Exception {

        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        int rset =0;

        String consulta = "";

        // int medicion_id = Integer.parseInt(medicion.getMedicionId());
        String dni_usuario = alerta.getUsuarioId();
        int mensaje_id = alerta.getMensaje_id();
        String tiempo = alerta.getFecha();
        String tipo = alerta.getTipoAlerta();
        String mensaje = alerta.getMensaje();

        /*

        consulta ="INSERT INTO SECTORES VALUES ("+codigo+','+"'"+nombre+"'"+','+porcentaje+','+ingresos+")";
			// OJO: Las cadenas en el insert deben ir entre comillas simples ''
			resultado = ps.executeUpdate(consulta); //ExecuteUpdate porque es una insercion.

         */

        statement = connection.createStatement();

        consulta ="INSERT INTO alertas (usuario, mensaje_id, tiempo, tipo, mensaje) VALUES " +
                "("+"'"+dni_usuario+"'"+','+mensaje_id+','+"'"+tiempo+"'"+','+"'"+tipo+"'"+','+"'"+mensaje+"'"+")";

        System.out.println(consulta);

        rset = statement.executeUpdate(consulta);

        // Connection connection = getConnection();


        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }



    public static void eliminarAlerta(int alerta_id_par) throws Exception {


        // Statement statement = null;
        Statement statement = null;
        int rset = 0;

        // Necesario ponerle comillas si es un string
        // dni_par = "'"+dni_par+"'";

        // Connection connection = getConnection();

        statement = connection.createStatement();

        /*
        consulta ="DELETE FROM SECTORES WHERE CODS = " + numero;
        resultado = ps.executeUpdate(consulta);
        */

        String consulta = "DELETE FROM alertas WHERE alerta_id = " +alerta_id_par;

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        rset = statement.executeUpdate(consulta);

        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }




     /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Métodos para CRUD de las Mediciones

     */



    public static void mostrarTodasLasMediciones() throws Exception {


        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        // Connection connection = getConnection();

        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.mediciones");

        while (rset.next()) {
            System.out.println("Medicion_id: "+rset.getString(1));

            System.out.println("Usuario: "+rset.getString(2));

            System.out.println("Tiempo: "+rset.getString(3));

            System.out.println("Pulso: "+rset.getString(4));

            System.out.println("Oxígeno: "+rset.getString(5));

            System.out.println("--------------------------------------");

        }

    }


    public static Mediciones obtenerTodosLasMediciones() throws Exception {


        Mediciones mediciones = new Mediciones();

        Statement statement = null;

        // PreparedStatement preparedStatement = null;

        ResultSet rset = null;

        String dni_usuario = "";
        String medicion_id = "";
        String tiempo = "";
        int pulso = 0;
        int oxigeno = 0;

        // Connection connection = getConnection();

        // statement = connection.createStatement();


        statement = connection.createStatement();

        rset = statement
                .executeQuery("select * from dumnex.mediciones");

        while (rset.next()) {

            System.out.println("ID_Medicion: "+rset.getString(1));
            medicion_id = rset.getString(1);

            System.out.println("DNI_Usuario: "+rset.getString(2));
            dni_usuario = rset.getString(2);

            System.out.println("Tiempo: "+rset.getString(3));
            tiempo = rset.getString(3);

            System.out.println("Pulso: "+rset.getInt(4));
            pulso = rset.getInt(4);

            System.out.println("Teléfono: "+rset.getInt(5));
            oxigeno = rset.getInt(5);

            System.out.println("--------------------------------------");

            mediciones.add(new Medicion(medicion_id,dni_usuario,tiempo,pulso,oxigeno));

        }


        return mediciones;

    }


    public static Mediciones obtenerMedicionesUsuario(String dni_par) throws Exception {

        Mediciones mediciones = new Mediciones();

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni_usuario = "";
        String medicion_id = "";
        String tiempo = "";
        int pulso = 0;
        int oxigeno = 0;

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.mediciones where usuario = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        while (rset.next()) {

            System.out.println("ID_Medicion: "+rset.getString(1));
            medicion_id = rset.getString(1);

            System.out.println("DNI_Usuario: "+rset.getString(2));
            dni_usuario = rset.getString(2);

            System.out.println("Tiempo: "+rset.getString(3));
            tiempo = rset.getString(3);

            System.out.println("Pulso: "+rset.getInt(4));
            pulso = rset.getInt(4);

            System.out.println("Oxígeno: "+rset.getInt(5));
            oxigeno = rset.getInt(5);

            System.out.println("--------------------------------------");

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            mediciones.add(new Medicion(medicion_id,dni_usuario,tiempo,pulso,oxigeno));

        }

        return mediciones;

    }

    public static Medicion obtenerMedicion(int medicion_id_par) throws Exception {

        Medicion medicion_re = null;

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni_usuario = "";
        String medicion_id = "";
        String tiempo = "";
        int pulso = 0;
        int oxigeno = 0;

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.mediciones where medicion_id = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setInt(1,medicion_id_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {

            System.out.println("ID_Medicion: "+rset.getString(1));
            medicion_id = rset.getString(1);

            System.out.println("DNI_Usuario: "+rset.getString(2));
            dni_usuario = rset.getString(2);

            System.out.println("Tiempo: "+rset.getString(3));
            tiempo = rset.getString(3);

            System.out.println("Pulso: "+rset.getInt(4));
            pulso = rset.getInt(4);

            System.out.println("Teléfono: "+rset.getInt(5));
            oxigeno = rset.getInt(5);

            System.out.println("--------------------------------------");

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            medicion_re = new Medicion(medicion_id,dni_usuario,tiempo,pulso,oxigeno);

        }

        return medicion_re;

    }

    public static void mostrarMedicionesUsuario(String dni_par) throws Exception {


        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;


        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.mediciones where usuario = ?");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,dni_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        while (rset.next()) {
            System.out.println("Medicion_id: "+rset.getString(1));

            System.out.println("Usuario: "+rset.getString(2));

            System.out.println("Tiempo: "+rset.getString(3));

            System.out.println("Pulso: "+rset.getString(4));

            System.out.println("Oxígeno: "+rset.getString(5));

            System.out.println("--------------------------------------");


        }

    }


    public static void insertarMedicion(Medicion medicion) throws Exception {

        Statement statement = null;
        // PreparedStatement preparedStatement = null;
        int rset =0;

        String consulta = "";

        // int medicion_id = Integer.parseInt(medicion.getMedicionId());
        String dni_usuario = medicion.getUsuarioId();
        String tiempo = medicion.getTiempo();
        int pulso = medicion.getPulso();
        int oxigeno = medicion.getOxigeno();

        /*

        consulta ="INSERT INTO SECTORES VALUES ("+codigo+','+"'"+nombre+"'"+','+porcentaje+','+ingresos+")";
			// OJO: Las cadenas en el insert deben ir entre comillas simples ''
			resultado = ps.executeUpdate(consulta); //ExecuteUpdate porque es una insercion.

         */

        statement = connection.createStatement();

        consulta ="INSERT INTO mediciones (usuario, tiempo, pulso, oxigeno) VALUES " +
                "("+"'"+dni_usuario+"'"+','+"'"+tiempo+"'"+','+pulso+','+oxigeno+")";

        System.out.println(consulta);

        rset = statement.executeUpdate(consulta);

        // Connection connection = getConnection();


        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }


    public static void eliminarMedicion(int medicion_id_par) throws Exception {


        // Statement statement = null;
        Statement statement = null;
        int rset = 0;

        // Necesario ponerle comillas si es un string
        // dni_par = "'"+dni_par+"'";

        // Connection connection = getConnection();

        statement = connection.createStatement();

        /*
        consulta ="DELETE FROM SECTORES WHERE CODS = " + numero;
        resultado = ps.executeUpdate(consulta);
        */

        String consulta = "DELETE FROM mediciones WHERE medicion_id = " +medicion_id_par;

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        rset = statement.executeUpdate(consulta);

        System.out.println(consulta);
        System.out.println("Numero de filas afectadas: "+rset);


    }


    /*

    Funciones extra para poder obtener oxigeno y pulso, si existen valores
    de ambos

     */


    public static Medicion obtenerUltimoOxigeno(String usuario_id_par) throws Exception {

        Medicion medicion_re = null;

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni_usuario = "";
        String medicion_id = "";
        String tiempo = "";
        int pulso = 0;
        int oxigeno = 0;

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.mediciones where usuario = ?" +
                "and oxigeno <> '-1' order by tiempo desc");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,usuario_id_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {

            System.out.println("ID_Medicion: "+rset.getString(1));
            medicion_id = rset.getString(1);

            System.out.println("DNI_Usuario: "+rset.getString(2));
            dni_usuario = rset.getString(2);

            System.out.println("Tiempo: "+rset.getString(3));
            tiempo = rset.getString(3);

            System.out.println("Pulso: "+rset.getInt(4));
            pulso = rset.getInt(4);

            System.out.println("Teléfono: "+rset.getInt(5));
            oxigeno = rset.getInt(5);

            System.out.println("--------------------------------------");

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            medicion_re = new Medicion(medicion_id,dni_usuario,tiempo,pulso,oxigeno);

        }

        return medicion_re;

    }


    public static Medicion obtenerUltimoPulso(String usuario_id_par) throws Exception {

        Medicion medicion_re = null;

        // Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rset = null;

        String dni_usuario = "";
        String medicion_id = "";
        String tiempo = "";
        int pulso = 0;
        int oxigeno = 0;

        // Connection connection = getConnection();

        // statement = connection.createStatement();

        preparedStatement = connection.prepareStatement("select * from dumnex.mediciones where usuario = ?" +
                "and pulso <> '-1' order by tiempo desc");

        // ps = conexion.prepareStatement("SELECT NOMBRE, APELLIDO1,
        // APELLIDO2, DIRECCION, INGRESOS FROM POBLACION WHERE DNI = ?");

        preparedStatement.clearParameters();
        preparedStatement.setString(1,usuario_id_par);
        rset = preparedStatement.executeQuery(); //ExecuteQuery -> para consultas de tipo SELECT.

        if (rset.next()) {

            System.out.println("ID_Medicion: "+rset.getString(1));
            medicion_id = rset.getString(1);

            System.out.println("DNI_Usuario: "+rset.getString(2));
            dni_usuario = rset.getString(2);

            System.out.println("Tiempo: "+rset.getString(3));
            tiempo = rset.getString(3);

            System.out.println("Pulso: "+rset.getInt(4));
            pulso = rset.getInt(4);

            System.out.println("Teléfono: "+rset.getInt(5));
            oxigeno = rset.getInt(5);

            System.out.println("--------------------------------------");

            // usuarios.add(new Usuario(dni,nombre,apellidos,email,telefono,localidad));

            medicion_re = new Medicion(medicion_id,dni_usuario,tiempo,pulso,oxigeno);

        }

        return medicion_re;

    }

    public static void main(String[] args) {
        // create a new connection from MySQLJDBCUtil


        /*

        try (Connection conn = Jdbc.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        */

        try {


            /*
            Jdbc Jdbc = new Jdbc();


            Jdbc.readDataBase();

            */

            /*

            Jdbc.readDataBase();

            System.out.println("Senunga llamada.");

            Jdbc.mostrarUsuario("12345678A");

            // Jdbc.insertarUsuario(new Usuario("87654321A","d","d","d","d","d"));

            Jdbc.eliminarUsuario("87654321A");

            */

            // Llamadas a mediciones

            /*

            Jdbc.mostrarTodasLasMediciones();

            System.out.println();

            Jdbc.obtenerTodosLasMediciones();

            System.out.println();

            Jdbc.obtenerMedicionesUsuario("12345678A");

            System.out.println();

            Jdbc.mostrarMedicionesUsuario("12345678A");

            System.out.println();

            Jdbc.insertarMedicion(new Medicion("12345678A","2018-01-11",23,13));

            Jdbc.eliminarMedicion(5);

            */

            // Llamadas a mediciones

            Jdbc.mostrarTodasLasAlertas();

            System.out.println();

            System.out.println("Número de alertas: "+Jdbc.obtenerNumerDeAlertas());

            // Jdbc.obtenerMedicionesUsuario("12345678A");

            System.out.println();

            Jdbc.obtenerAlerta(1);

            System.out.println();

            // Jdbc.insertarAlerta(new Alerta("12345678A",4097,"2020-11-09","P",
            //     "Valores de pulso (45) fuera de rango"));

            System.out.println();

            // Jdbc.insertarMedicion(new Medicion("12345678A","2018-01-11",23,13));

            // Jdbc.eliminarAlerta(2);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
