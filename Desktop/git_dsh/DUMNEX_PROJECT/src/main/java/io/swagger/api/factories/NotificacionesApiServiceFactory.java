package io.swagger.api.factories;

import io.swagger.api.NotificacionesApiService;
import io.swagger.api.impl.NotificacionesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class NotificacionesApiServiceFactory {
    private final static NotificacionesApiService service = new NotificacionesApiServiceImpl();

    public static NotificacionesApiService getNotificacionesApi() {
        return service;
    }
}
