package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.AlertasApiService;
import io.swagger.api.factories.AlertasApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.TodasAlertas;


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


@Path("/alertas")


@io.swagger.annotations.Api(description = "the alertas API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class AlertasApi  {
   private final AlertasApiService delegate;

   public AlertasApi(@Context ServletConfig servletContext) {
      AlertasApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("AlertasApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (AlertasApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = AlertasApiServiceFactory.getAlertasApi();
      }

      this.delegate = delegate;
   }


    @GET
    
    
    @Produces({ "application/json", "text/plain" })
    @io.swagger.annotations.ApiOperation(value = "Obtener todas las alertas de todos los usuarios.", notes = "Obtener todas las alertas de todos los usuarios.", response = TodasAlertas.class, tags={ "alertas", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Array de alertas correspondientes al usuario", response = TodasAlertas.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Error al obtener las alertas.", response = String.class) })
    public Response alertasGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.alertasGet(securityContext);
    }

}

