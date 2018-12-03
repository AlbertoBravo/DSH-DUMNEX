package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.NotificacionesApiService;
import io.swagger.api.factories.NotificacionesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.util.Date;


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


@Path("/notificaciones")


@io.swagger.annotations.Api(description = "the notificaciones API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class NotificacionesApi  {
   private final NotificacionesApiService delegate;

   public NotificacionesApi(@Context ServletConfig servletContext) {
      NotificacionesApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("NotificacionesApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (NotificacionesApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = NotificacionesApiServiceFactory.getNotificacionesApi();
      }

      this.delegate = delegate;
   }


    @PUT
    
    
    @Produces({ "text/plain", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Actualiza una notificacion", notes = "Actualiza una notificacion", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "La actualización se ha realizado correctamente", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Los valores introducidos no son correctos", response = Object.class) })
    public Response actualizarNotificacion(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "Identificador de la notificacion",required=true) @QueryParam("notificacionId") String notificacionId
,@ApiParam(value = "gravedad de la notificacion") @QueryParam("tipo") String tipo
,@ApiParam(value = "fecha de la notificacion") @QueryParam("fecha") Date fecha
,@ApiParam(value = "Estado de la alerta", allowableValues="esperando, enviada") @QueryParam("estado") String estado
,@ApiParam(value = "destinatario de la notificacion") @QueryParam("destinatario") String destinatario
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.actualizarNotificacion(usuarioId,notificacionId,tipo,fecha,estado,destinatario,securityContext);
    }

    @DELETE
    
    
    @Produces({ "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Borra una notificación especifica", notes = "Borra una notificación especifica", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "El borrado se ha realizado correctamente", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "La notificacion no ha sido borrada", response = String.class) })
    public Response borrarNotificacion(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "Identificador de la notificacion",required=true) @QueryParam("notificacionId") String notificacionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.borrarNotificacion(usuarioId,notificacionId,securityContext);
    }

    @POST
    
    
    @Produces({ "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Envía una nueva notificación de alerta", notes = "Envía una nueva notificación de alerta", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Notificación enviada correctamente", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "", response = Void.class) })
    public Response enviarNotificacion(@ApiParam(value = "Identificador del usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@ApiParam(value = "Identificador de la alerta que va a enviarse",required=true) @QueryParam("alertaId") String alertaId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.enviarNotificacion(usuarioId,alertaId,securityContext);
    }

    @GET
    
    
    @Produces({ "application/json", "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Obtiene todas las notificaciones enviadas para el usuario dado", notes = "Obtiene todas las notificaciones enviadas para el usuario dado", response = Object.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Notificaciones obtenidas correctamente", response = Object.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "No ha podido realizarse dicha accion. Comprobar que el identificador introducido es correcto", response = String.class) })
    public Response obtenerNotificaciones(@ApiParam(value = "Identificador del Usuario",required=true) @QueryParam("usuarioId") String usuarioId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.obtenerNotificaciones(usuarioId,securityContext);
    }

}

