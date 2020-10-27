package com.fetchrewards.server;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ApplicationTest {

  @LocalServerPort
  int port;

  WebClient client;

  @PostConstruct
  public void postConstruct() {
    client = WebClient
        .builder()
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @Test
  @Order(1)
  public void test1() throws Throwable {
    Map<String, String> actuatorHealth = client
        .get()
        .uri("/actuator/health")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
        .block();

    Assertions.assertNotNull(actuatorHealth);
    Assertions.assertEquals("UP", actuatorHealth.get("status"));
  }

  @Test
  @Order(2)
  public void test2() throws Throwable {
    List<String> emails = List.of(
        "test.email@gmail.com",
        "test.email+spam@gmail.com",
        "testemail@gmail.com",

        "test.email@gmail.com",
        "test.email@fetchrewards.com"
    );

    Integer uniqueCount = client
        .post()
        .uri("/api/email/unique-count")
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(emails)
        .retrieve()
        .bodyToMono(Integer.class)
        .block();

    System.out.println(uniqueCount);
  }

}
