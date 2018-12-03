package io.swagger.api.factories;

import io.swagger.api.AlertasApiService;
import io.swagger.api.impl.AlertasApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class AlertasApiServiceFactory {
    private final static AlertasApiService service = new AlertasApiServiceImpl();

    public static AlertasApiService getAlertasApi() {
        return service;
    }
}
