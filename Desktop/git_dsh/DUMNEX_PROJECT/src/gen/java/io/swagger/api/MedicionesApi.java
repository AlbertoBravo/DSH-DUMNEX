package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MedicionesApiService;
import io.swagger.api.factories.MedicionesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Medicion;
import io.swagger.model.Mediciones;


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


@Path("/mediciones")


@io.swagger.annotations.Api(description = "the mediciones API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class MedicionesApi  {
   private final MedicionesApiService delegate;

   public MedicionesApi(@Context ServletConfig servletContext) {
      MedicionesApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MedicionesApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MedicionesApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = MedicionesApiServiceFactory.getMedicionesApi();
      }

      this.delegate = delegate;
   }


    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Cosulta las mediciones para un usuario", notes = "", response = Mediciones.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operación satisfactoria", response = Mediciones.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Id de usuario inválido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response consultarMedicionesUsuario(@ApiParam(value = "Identificador del Usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consultarMedicionesUsuario(usuarioId,securityContext);
    }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Crea una nueva medición de valores para un usuario", notes = "", response = Integer.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "La inserción se ha realizado correctamente. Devuelve el id asignado a la medición.", response = Integer.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Los parámetros de medición especificados no son correctos", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "El usuario especificado no se encuentra", response = Void.class) })
    public Response crearMedicion(@ApiParam(value = "" ) Object body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.crearMedicion(body,securityContext);
    }

    @DELETE
    
    
    
    @io.swagger.annotations.ApiOperation(value = "Elimina una medición para un usuario", notes = "", response = Void.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Medición eliminada", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Usuario o medición no válidos", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response eliminarMedicionUsuario(@ApiParam(value = "Identificador del Usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "Identificador de la Medición",required=true) @QueryParam("medicionId") Long medicionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.eliminarMedicionUsuario(usuarioId,medicionId,securityContext);
    }

    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Actiualiza las mediciones para un usuario", notes = "", response = Medicion.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operación satisfactoria", response = Medicion.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Id de usuario inválido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response modificarMedicionesUsuario(@ApiParam(value = "Identificador del Usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "Identificador de la Medición",required=true) @QueryParam("medicionId") Long medicionId
,@ApiParam(value = "" ) Medicion body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.modificarMedicionesUsuario(usuarioId,medicionId,body,securityContext);
    }

}

