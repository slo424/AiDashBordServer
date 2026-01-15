package com.saatchiNSaatchi.aiDashboard.controllers;

import com.saatchiNSaatchi.aiDashboard.views.AiStatView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(APIsController.class)
public class APIsControllerTest {

    WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToController(new APIsController())
                .configureClient()
                .baseUrl("http://localhost:8080/api")
                .build();
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String defaultUser = "Test";
        String expectedGreeting = "Hello, Test!";

        client.get().uri("/greeting?name="+defaultUser)
//                .queryParam("name", "Test") // Add query params
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(expectedGreeting);
    }

    @Test
    void getAIStats() throws Exception {
        client.get().uri("/usage")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> {
                    assertNotNull(response.getResponseBody());
                });
    }

    @Test
    void testGetAIStats() {
        client.get().uri("/usage/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AiStatView.class)
                .consumeWith(response -> {
                    System.out.println("Response Body: " + response.getResponseBody().getTotalCalls());
                    assertNotNull(response.getResponseBody().getTopModels());
                    assertNotNull(response.getResponseBody());
                });
    }
}
