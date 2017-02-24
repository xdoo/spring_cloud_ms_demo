/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    //TODO kriege das hier leider nicht ohne Autowired hin. Geht das irgendwie?
    @Autowired
    private RestTemplate restTemplate;

    public CustomerServiceStandardClient() {
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = restTemplate.exchange(
                "http://serviceB/customer/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Customer>() {
        },
                id).getBody();

        return customer;
    }

    public String sayHelloFromB() {
        return this.restTemplate.getForObject("http://serviceB/hello", String.class);
    }
}
