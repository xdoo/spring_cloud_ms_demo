package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
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

    public FooController(FooProcessor fooOutbound) {
        this.processor = fooOutbound;
    }
    
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
    
    @PostMapping("/fooEvent")
    public String fooEvent(@RequestBody FooEvent fooEvent){
        System.out.println(fooEvent);
        // build message
        Message<FooEvent> msg = MessageBuilder.withPayload(fooEvent).build();
        // send message
        this.processor.fooEvent().send(msg);
        return fooEvent.getValueA() + " und " + fooEvent.getValueB();
    }
    
}
