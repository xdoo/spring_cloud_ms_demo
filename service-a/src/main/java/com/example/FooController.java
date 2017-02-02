package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claus
 */
@RestController
public class FooController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
    
}
