package io.swagger.api.impl;

import alerta.AlertaService;
import com.google.gson.Gson;
import database.Jdbc;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Medicion;
import io.swagger.model.Mediciones;


import java.util.*;

import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javafx.util.Pair;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class MedicionesApiServiceImpl extends MedicionesApiService {
    
    @Override
    public Response consultarMedicionesUsuario( @NotNull String usuarioId, SecurityContext securityContext) throws NotFoundException {

        // do some magic!

        Mediciones re_medicones = new Mediciones();

        Mediciones mediciones = null;
        try {
            mediciones = Jdbc.obtenerTodosLasMediciones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int medicionIdint = 3;

        for (int i = 0; i < mediciones.size(); i++) {

            // Cast de string para igualar a cast de long
            if (mediciones.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                re_medicones.add(mediciones.get(i));

            }

        }

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(re_medicones);

        if (re_medicones.size()!=0) {
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }

    }
    
    @Override
    public Response crearMedicion( Object body, SecurityContext securityContext) throws NotFoundException {

        LinkedHashMap mapa = (LinkedHashMap) body;

        String dni_usuario = mapa.get( "usuario_id" ).toString();
        String tiempo = mapa.get( "tiempo" ).toString();
        Integer pulso = Integer.parseInt(mapa.get( "pulso" ).toString());
        Integer oxigeno = Integer.parseInt( mapa.get( "oxigeno" ).toString());

        Medicion medicionNueva = new Medicion(dni_usuario,tiempo,pulso,oxigeno);

        System.out.println(medicionNueva.toString());

        // El id de la medicion se genera dentro del servidor

        try {
            Jdbc.insertarMedicion(medicionNueva);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Key -> mensaje, Value -> tipo
        Pair mensaje_tipo = comprobarAlerta(medicionNueva.getPulso(),medicionNueva.getOxigeno());

        // Si no es "no" el mensaje, se ejecuta la acción correspondiente
        if (!mensaje_tipo.getKey().toString().equalsIgnoreCase("no")){

            try {

                String mensaje_alerta = mensaje_tipo.getKey().toString();
                String tipo_alerta = mensaje_tipo.getValue().toString();

                Usuario usuario_param = Jdbc.obtenerUsuario(medicionNueva.getUsuarioId());

                AlertaService.enviarAlerta(usuario_param,mensaje_alerta,tipo_alerta);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(medicionNueva);

        return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
    }
    
    @Override
    public Response eliminarMedicionUsuario( @NotNull String usuarioId,  @NotNull Long medicionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!

        boolean encontrado = false;

        Mediciones mediciones = null;
        try {
            mediciones = Jdbc.obtenerTodosLasMediciones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mediciones.size() && !encontrado; i++) {

            if (mediciones.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                try {
                    Jdbc.eliminarMedicion(Integer.parseInt(mediciones.get(i).getMedicionId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                encontrado = true;

            }

        }

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String

        if (encontrado) {
            String Eliminated = "Medicion "+medicionId+" correctamente eliminado.";
            return Response.ok(Eliminated, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }


    }
    
    @Override
    public Response modificarMedicionesUsuario( @NotNull String usuarioId,  @NotNull Long medicionId, Medicion body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }




    public static Pair<String,String> comprobarAlerta(int pulso_par, int oxigeno_par){

        boolean pulso = false;

        boolean oxigeno = false;

        // First -> mensaje
        // Second -> tipo
        Pair<String,String> mensajeTipo = null;

        String mensaje = "no";

        String tipo = "";

        if (pulso_par >= 180 || pulso_par <= 60){

            pulso = true;
        }

        if (oxigeno_par < 94){

            oxigeno = true;

        }

        if (pulso && oxigeno){

            mensaje = "Valores de pulso ("+pulso_par+") y oxigeno ("+oxigeno_par+"%) fuera de sus rangos.";
            tipo = "PO";

        }else{

            if (pulso){

                mensaje = "Valores de pulso ("+pulso_par+") fuera de rango.";
                tipo = "P";

            }else{
                if (oxigeno){

                    mensaje = "Valores de oxigeno ("+oxigeno_par+"%) fuera de rango.";
                    tipo = "O";

                }

            }

        }

        mensajeTipo = new Pair<String,String>(mensaje,tipo);

        return mensajeTipo;

    }

    
}

