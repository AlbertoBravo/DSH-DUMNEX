package io.swagger.api.impl;

import com.google.gson.Gson;
import database.Jdbc;
import io.swagger.api.*;
import io.swagger.model.*;



import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;
import java.util.Random;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

import static java.lang.Math.toIntExact;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class ValoracionApiServiceImpl extends ValoracionApiService {


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }



    // Es solo de ejemplo, para que realice algo de procesamiento
    public int generarValoracion (int pulso, int oxigeno){

        int valoracion = 0;

        if(oxigeno == 0){

            if (pulso >= 60 && pulso <= 180){

                valoracion =getRandomNumberInRange(5,7);
                // valoracion = 7;

            }else{

                valoracion =getRandomNumberInRange(3,4);
                // valoracion = 2;

            }

        }else{

            if (pulso >= 60 && oxigeno >= 89 && pulso <= 180 && oxigeno <=100){

                // valoracion = 9;

                valoracion = getRandomNumberInRange(8,10);


            }else{

                if (oxigeno >= 89 && oxigeno <=100){

                    // valoracion = 3;

                    valoracion =getRandomNumberInRange(5,7);

                }else{

                    // valoracion = 3;

                    valoracion =getRandomNumberInRange(0,2);

                }


            }

        }

        return valoracion;
    }


    @Override
    public Response obtenerValoracion( @NotNull String usuarioId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        // boolean encontrado = false;

        int valoracion = 0;

        /*

        Mediciones mediciones = null;
        try {
            mediciones = Jdbc.obtenerTodosLasMediciones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mediciones.size() && !encontrado; i++) {

            if (mediciones.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                valoracion = generarValoracion(mediciones.get(i).getPulso(),mediciones.get(i).getOxigeno());
                encontrado = true;

            }

        }

        */

        Medicion med_pulso = null;
        Medicion med_oxigeno = null;

        try {
            med_pulso = Jdbc.obtenerUltimoPulso(usuarioId);
            med_oxigeno = Jdbc.obtenerUltimoOxigeno(usuarioId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        valoracion = generarValoracion(med_pulso.getPulso(),med_oxigeno.getOxigeno());

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(valoracion);

        return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();

    }



    public static void main(String[] args) {


        /*

        try {
            Jdbc.readDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

       */



        long foo = 10L;
        String foot = "10";
        int bar = (int) foo;

        int bor = Integer.parseInt(foot);

        System.out.println(bar);
        System.out.println(foot);

    }


}

