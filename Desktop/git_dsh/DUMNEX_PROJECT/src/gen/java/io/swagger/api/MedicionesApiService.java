package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Medicion;
import io.swagger.model.Mediciones;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public abstract class MedicionesApiService {
    
    public abstract Response consultarMedicionesUsuario( @NotNull String usuarioId,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response crearMedicion(Object body,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response eliminarMedicionUsuario( @NotNull String usuarioId, @NotNull Long medicionId,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response modificarMedicionesUsuario( @NotNull String usuarioId, @NotNull Long medicionId,Medicion body,SecurityContext securityContext) throws NotFoundException;
    
}

