package io.swagger.api.factories;

import io.swagger.api.PulsoApiService;
import io.swagger.api.impl.PulsoApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")
public class PulsoApiServiceFactory {
    private final static PulsoApiService service = new PulsoApiServiceImpl();

    public static PulsoApiService getPulsoApi() {
        return service;
    }
}
