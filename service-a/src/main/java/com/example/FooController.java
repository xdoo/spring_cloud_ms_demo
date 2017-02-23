package com.example;

import com.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.CustomerServiceFeignClient;
import com.example.services.CustomerServiceStandardClient;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author claus
 */
@RestController
@EnableBinding(FooProcessor.class)
public class FooController {

    private final FooProcessor processor;

    @Autowired
    private CustomerServiceFeignClient customerServiceFeignClient;
    @Autowired
    private CustomerServiceStandardClient customerServiceStandardClient;

    public FooController(FooProcessor fooOutbound) {
        this.processor = fooOutbound;
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
        return customerServiceFeignClient.getCustomer(id);
    }

    @GetMapping("/customerWithoutFeign/{id}")
    public Customer customerWithoutFeign(@PathVariable("id") int id) {
        return customerServiceStandardClient.getCustomer(id);
    }
}
