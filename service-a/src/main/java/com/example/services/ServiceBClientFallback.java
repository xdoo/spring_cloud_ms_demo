/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.model.Customer;
import org.springframework.stereotype.Service;

/**
 *
 * @author roland.werner
 */
@Service
class ServiceBClientFallback implements CustomerServiceAlternativeClient {

    @Override
    public Customer getCustomer(int id) {
        Customer customer = new Customer();
        customer.setVorname("N.");
        customer.setNachname("N.");
        return customer;
    }
    
}
