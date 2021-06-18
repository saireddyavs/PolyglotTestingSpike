package com.tw.Microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicroserviceApplication.class, args);
  }
}

@RestController
class microServiceTwoController {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());
  @Autowired RestTemplate restTemplate;

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @GetMapping("/microservice2")
  public String methodTwo() {
    LOG.info("Inside method2");
    // String baseUrl = "http://localhost:8062/microservice3";
    String baseUrl = "http://microservice-three:8062/microservice3";
    String response = (String)restTemplate
                          .exchange(baseUrl, HttpMethod.GET, null, String.class)
                          .getBody();
    LOG.info("The response received by method2 is " + response);
    return response;
  }
}
