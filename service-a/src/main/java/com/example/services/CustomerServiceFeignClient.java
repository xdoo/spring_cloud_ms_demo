/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 *
 * @author roland.werner
 */
@FeignClient(value = "serviceb")
public interface CustomerServiceFeignClient extends CustomerService {
    
}
