package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Usuario;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public abstract class UsuarioApiService {
    
    public abstract Response consultarUsuario( @NotNull String usuarioId,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response crearUsuario(Object body,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response eliminarUsuario( @NotNull String usuarioId,SecurityContext securityContext) throws NotFoundException;
    
    public abstract Response modificarUsuario( @NotNull String usuarioId,Usuario body,SecurityContext securityContext) throws NotFoundException;
    
}

