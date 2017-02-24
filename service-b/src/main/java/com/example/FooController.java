/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roland.werner
 */
@RestController
public class FooController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
    
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
    public Customer getCustomer(@PathVariable("id") int id) {
        Customer customer = new Customer();
        customer.setVorname("Johnny" + id);
        customer.setNachname("Knoxville");
        return customer;
    }
}
