package com.example;

import com.example.model.Customer;
import com.example.services.CustomerServiceAlternativeClient;
import com.example.services.CustomerServiceStandardClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claus
 */
@RestController
@EnableBinding(FooProcessor.class)
public class FooController {
        
    private final FooProcessor processor;
    private final CustomerServiceAlternativeClient customerServiceAlternativeClient;
    private final CustomerServiceStandardClient customerServiceStandardClient;

    public FooController(FooProcessor fooOutbound,
            CustomerServiceAlternativeClient customerServiceAlternativeClient,
            CustomerServiceStandardClient customerServiceStandardClient) {
        this.processor = fooOutbound;
        this.customerServiceAlternativeClient = customerServiceAlternativeClient;
        this.customerServiceStandardClient = customerServiceStandardClient;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/helloFromB")
    public String sayHelloFromB() {
        return customerServiceStandardClient.sayHelloFromB();
    }

    @PostMapping("/fooEvent")
    public String fooEvent(@RequestBody FooEvent fooEvent) {
        System.out.println(fooEvent);
        // build message
        Message<FooEvent> msg = MessageBuilder.withPayload(fooEvent).build();
        // send message
        this.processor.fooEvent().send(msg);
        return fooEvent.getValueA() + " und " + fooEvent.getValueB();
    }

    @GetMapping("/customerWithFeign/{id}")
    public Customer customerWithFeign(@PathVariable("id") int id) {
        return customerServiceAlternativeClient.getCustomer(id);
    }

    @GetMapping("/customerWithoutFeign/{id}")
    public Customer customerWithoutFeign(@PathVariable("id") int id) {
        return customerServiceStandardClient.getCustomer(id);
    }
}
