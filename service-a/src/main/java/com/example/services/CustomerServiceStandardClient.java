/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.model.Customer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author roland.werner
 */
@Service
public class CustomerServiceStandardClient implements CustomerService {

    private final RestTemplate restTemplate;

    public CustomerServiceStandardClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = restTemplate.exchange(
                "http://localhost:8080/serviceb/customer/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Customer>() {
        },
                id).getBody();

        return customer;
    }

    public String sayHelloFromB() {
        return this.restTemplate.getForObject("http://localhost:8080/serviceb/hello", String.class);
    }
}
