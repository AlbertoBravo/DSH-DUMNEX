package io.swagger.api.impl;

import com.google.gson.Gson;
import com.sun.deploy.net.HttpResponse;
import database.Jdbc;
import io.swagger.api.*;
import io.swagger.model.*;


import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import io.swagger.api.NotFoundException;

import io.swagger.util.Json;
import io.swagger.util.ResponseDeserializer;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

import static com.google.common.base.Predicates.equalTo;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-29T18:41:10.265Z[GMT]")

public class PulsoApiServiceImpl extends PulsoApiService {
    
    @Override
    public Response obtenerPulso( @NotNull String usuarioId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        boolean encontrado = false;

        int valoracion = 0;

        Mediciones mediciones = null;
        try {
            mediciones = Jdbc.obtenerTodosLasMediciones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mediciones.size() && !encontrado; i++) {

            if (mediciones.get(i).getUsuarioId().equalsIgnoreCase(usuarioId)){
                valoracion = mediciones.get(i).getPulso();
                encontrado = true;

            }

        }

        Gson gson = new Gson();

        // 1. Java object to JSON, and save into a file
        // gson.toJson(obj, new FileWriter("D:\\file.json"));

        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(valoracion);

        if (encontrado) {
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        }else{
            String notFound = "NotFound";
            return Response.ok(notFound, MediaType.APPLICATION_JSON).build();
        }

    }



    public static void main(String[] args) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://192.168.1.103:8080/v1/mediciones");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("usuarioId", "12345678A"));
        params.add(new BasicNameValuePair("tiempo", "2018-11-23 19:26:11"));
        params.add(new BasicNameValuePair("pulso", "79"));
        params.add(new BasicNameValuePair("oxigeno", "109"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = client.execute(httpPost);

        System.out.println(response.getStatusLine().getStatusCode());
        // assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    
}

