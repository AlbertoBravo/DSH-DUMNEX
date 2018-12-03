package io.swagger.api.impl;

import com.google.gson.Gson;
import database.Jdbc;
import io.swagger.api.*;

import io.swagger.model.Usuario;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import io.swagger.api.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class UsuarioApiServiceImpl extends UsuarioApiService {
    
    @Override
    public Response consultarUsuario( @NotNull String usuarioId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        boolean encontrado = false;

        Usuario re_usuario = null;

        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            usuarios = Jdbc.obtenerTodosLosUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < usuarios.size() && !encontrado; i++) {

            if (usuarios.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                re_usuario = usuarios.get(i);
                encontrado = true;

            }

        }

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(re_usuario);

        if (encontrado) {
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }

    }
    
    @Override
    public Response crearUsuario(Object body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        LinkedHashMap mapa = (LinkedHashMap) body;

        String dni = mapa.get( "usuario_id" ).toString();
        String nombre = mapa.get( "nombre" ).toString();
        String apellidos = mapa.get( "apellidos" ).toString();
        String email = mapa.get("alerta").toString();
        String telefono = mapa.get( "telefono" ).toString();
        String localidad = mapa.get( "localidad" ).toString();

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setUsuarioId( dni );
        usuarioNuevo.setNombre( nombre );
        usuarioNuevo.setApellidos( apellidos );
        usuarioNuevo.setEmail( email );
        usuarioNuevo.setTelefono( telefono );
        usuarioNuevo.setLocalidad( localidad );

        try {
            Jdbc.insertarUsuario(usuarioNuevo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        String json = gson.toJson( usuarioNuevo.getUsuarioId() );
        return Response.ok(json, MediaType.APPLICATION_JSON).build();

    }
    
    @Override
    public Response eliminarUsuario( @NotNull String usuarioId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!

        boolean encontrado = false;

        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            usuarios = Jdbc.obtenerTodosLosUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < usuarios.size() && !encontrado; i++) {

            if (usuarios.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                try {
                    Jdbc.eliminarUsuario(usuarioId);
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
            String Eliminated = "Usuario "+usuarioId+" correctamente eliminado.";
            return Response.ok(Eliminated, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }


    }
    
    @Override
    public Response modificarUsuario( @NotNull String usuarioId, Usuario body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}

