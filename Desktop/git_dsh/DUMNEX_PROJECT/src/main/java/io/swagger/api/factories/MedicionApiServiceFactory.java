package io.swagger.api.factories;

import io.swagger.api.MedicionApiService;
import io.swagger.api.impl.MedicionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class MedicionApiServiceFactory {
    private final static MedicionApiService service = new MedicionApiServiceImpl();

    public static MedicionApiService getMedicionApi() {
        return service;
    }
}
