package io.swagger.api.factories;

import io.swagger.api.MedicionesApiService;
import io.swagger.api.impl.MedicionesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class MedicionesApiServiceFactory {
    private final static MedicionesApiService service = new MedicionesApiServiceImpl();

    public static MedicionesApiService getMedicionesApi() {
        return service;
    }
}
