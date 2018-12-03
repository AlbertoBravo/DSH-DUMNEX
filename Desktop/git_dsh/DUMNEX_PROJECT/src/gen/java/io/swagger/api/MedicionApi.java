package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MedicionApiService;
import io.swagger.api.factories.MedicionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Medicion;


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


@Path("/medicion")


@io.swagger.annotations.Api(description = "the medicion API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class MedicionApi  {
   private final MedicionApiService delegate;

   public MedicionApi(@Context ServletConfig servletContext) {
      MedicionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MedicionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MedicionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = MedicionApiServiceFactory.getMedicionApi();
      }

      this.delegate = delegate;
   }


    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Cosulta una medición para un usuario", notes = "", response = Medicion.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Operación satisfactoria", response = Medicion.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Id de usuario inválido", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Usuario no encontrado", response = Void.class) })
    public Response consultarMedicionUsuario(@ApiParam(value = "Identificador del Usuario",required=true) @QueryParam("usuarioId") Long usuarioId
,@ApiParam(value = "Identificador de la medición",required=true) @QueryParam("medicionId") Long medicionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consultarMedicionUsuario(medicionId,securityContext);
    }

}

