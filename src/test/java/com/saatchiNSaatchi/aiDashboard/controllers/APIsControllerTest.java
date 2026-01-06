package com.saatchiNSaatchi.aiDashboard.controllers;

import com.saatchiNSaatchi.aiDashboard.views.TeamIdsView;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

//@WebMvcTest(APIsController.class) // Focuses context on YourController
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureRestTestClient // Configures RestTestClient
class APIsControllerTest {

//    @Autowired
    private RestTestClient restTestClient;

    private static final Logger logger = LoggerFactory.getLogger(APIsController.class);

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindToController(new APIsController())
                .baseUrl("/api")
                .build();
    }

    @Test
    void helloWorld() throws Exception {
        restTestClient.get().uri("/usageTeams")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
//        restTestClient.get()
////                .uri("/api/greeting")
//                .uri("http://localhost:8080/api")
//                .exchange() // Perform the request
//                .expectStatus().isOk(); // Assert the status
////                .expectBody(MyResponse.class).isEqualTo(expectedResponse);
    }

    @Test
    void testHelloWorld() {
        restTestClient.get()
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).consumeWith(response -> {
                    // You can log the response body directly here
                    System.out.println(response.getResponseBody());
                    // Further assertions can go here
                });
    }

    @Test
    void getAIStats() {
        restTestClient.get().uri("/usageTeams")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TeamIdsView.class).consumeWith(response -> {
                    System.out.println("--- Response Status: " + response.getStatus() + " ---");
                    System.out.println("--- Response Body: " + response.getResponseBody() + " ---");
//                    response.getResponseBody().getIdRange().length() > 0;
                });
    }

    @Test
    void testGetAIStats() {
    }

    @Test
    void getAIStatsByTeamAndDate() {
    }

    @Test
    void getAIUsageTeamIds() {
    }
}