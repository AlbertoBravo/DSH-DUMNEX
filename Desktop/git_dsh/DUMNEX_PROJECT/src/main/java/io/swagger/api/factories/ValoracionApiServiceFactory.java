package io.swagger.api.factories;

import io.swagger.api.ValoracionApiService;
import io.swagger.api.impl.ValoracionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class ValoracionApiServiceFactory {
    private final static ValoracionApiService service = new ValoracionApiServiceImpl();

    public static ValoracionApiService getValoracionApi() {
        return service;
    }
}
