package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UsuarioApiService;
import io.swagger.api.factories.UsuarioApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Usuario;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

import javax.validation.constraints.*;


@Path("/usuario")


@io.swagger.annotations.Api(description = "the usuario API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class UsuarioApi  {
   private final UsuarioApiService delegate;

   public UsuarioApi(@Context ServletConfig servletContext) {
      UsuarioApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UsuarioApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UsuarioApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UsuarioApiServiceFactory.getUsuarioApi();
      }

      this.delegate = delegate;
   }


    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Consulta un  usuario", notes = "", response = Usuario.class, tags={ "usuario", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operación satisfactoria", response = Usuario.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Id de usuario inválido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response consultarUsuario(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consultarUsuario(usuarioId,securityContext);
    }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Crea un nuevo usuario", notes = "", response = Integer.class, tags={ "usuario", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "La inserción se ha realizado correctamente. Devuelve el id asignado al usuario.", response = Integer.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Los parámetros de usuario especificados no son correctos", response = Void.class) })
    public Response crearUsuario(@ApiParam(value = "" ) Object body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.crearUsuario(body,securityContext);
    }

    @DELETE
    
    
    
    @io.swagger.annotations.ApiOperation(value = "Elimina un  usuario", notes = "", response = Void.class, tags={ "usuario", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Usuario eliminado", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Usuario no válido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response eliminarUsuario(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eliminarUsuario(usuarioId,securityContext);
    }

    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Modifica un  usuario", notes = "", response = Usuario.class, tags={ "usuario", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operación satisfactoria", response = Usuario.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Id de usuario inválido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response modificarUsuario(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "" ) Usuario body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.modificarUsuario(usuarioId,body,securityContext);
    }

}

