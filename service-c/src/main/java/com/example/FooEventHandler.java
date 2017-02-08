package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author claus
 */
@Service
@EnableBinding(FooProcessor.class)
public class FooEventHandler {
    
    @StreamListener(FooProcessor.INPUT_FOO_EVENT)
    public void handleFooEvent(FooEvent fooEvent) {
        System.out.println(fooEvent.getValueA() + " und " + fooEvent.getValueB());
    }
    
    
}
