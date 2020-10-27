package com.fetchrewards.server.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class EmailServiceTest {

  @Test
  @Order(1)
  public void test1() {
    List<String> emails = List.of(
        "test.email@gmail.com",
        "test.email+spam@gmail.com",
        "testemail@gmail.com",

        "test.email@gmail.com",
        "test.email@fetchrewards.com"
    );

    EmailService emailService = new EmailService();
    Integer uniqueCount = emailService.uniqueCount(emails);

    Assertions.assertEquals(2, uniqueCount);
  }

  @Test
  @Order(2)
  public void test2() {

    Assertions.assertEquals(
        "testemail@gmail.com",
        new EmailService().toMatchingEmail("test.email+spam@gmail.com")
    );
  }

}
