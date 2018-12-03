package io.swagger.api.impl;

import com.google.gson.Gson;
import database.Jdbc;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Medicion;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class MedicionApiServiceImpl extends MedicionApiService {
    
    @Override
    public Response consultarMedicionUsuario( @NotNull Long medicionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        boolean encontrado = false;

        Medicion re_medicion = null;

        Mediciones mediciones = null;
        try {
            mediciones = Jdbc.obtenerTodosLasMediciones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int medicionIdint = 3;

        for (int i = 0; i < mediciones.size() && !encontrado; i++) {

            // Cast de string para igualar a cast de long
            if (Integer.parseInt(mediciones.get(i).getMedicionId()) == (int) medicionIdint){
                re_medicion = mediciones.get(i);
                encontrado = true;

            }

        }

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(re_medicion);

        if (encontrado) {
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }

    }
    
}

