package com.emse.spring.faircorp.integration;

import com.emse.spring.faircorp.model.Window;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IntegrationTests {

    @Test
    public void nonexistentPathThrows404() throws IOException {
        String name = "bobs";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/" + name);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        System.out.println(httpResponse.getStatusLine().getStatusCode());

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void
    givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
            throws ClientProtocolException, IOException {

        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/windows/-10" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals( jsonMimeType, mimeType );
    }

    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

    @Test
    public void
    givenWindowExists_whenWindowInformationIsRetrieved_thenRetrievedResourceIsCorrect()
            throws ClientProtocolException, IOException {

        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/windows/-10" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        Window resource = retrieveResourceFromResponse(
                response, Window.class);
        assertThat( "Window 1").isEqualTo( resource.getName());
    }
}
