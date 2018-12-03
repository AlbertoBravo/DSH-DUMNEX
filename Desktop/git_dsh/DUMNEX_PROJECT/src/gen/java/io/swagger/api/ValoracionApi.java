package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ValoracionApiService;
import io.swagger.api.factories.ValoracionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;



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


@Path("/valoracion")


@io.swagger.annotations.Api(description = "the valoracion API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class ValoracionApi  {
   private final ValoracionApiService delegate;

   public ValoracionApi(@Context ServletConfig servletContext) {
      ValoracionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ValoracionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ValoracionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ValoracionApiServiceFactory.getValoracionApi();
      }

      this.delegate = delegate;
   }


    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Obtener valoración del usuario", notes = "Obtener valoración del usuario", response = Object.class, tags={ "medicion", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "operación exitosa", response = Object.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "", response = Void.class) })
    public Response obtenerValoracion(@ApiParam(value = "First operand",required=true) @QueryParam("usuarioId") String usuarioId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.obtenerValoracion(usuarioId,securityContext);
    }

}

