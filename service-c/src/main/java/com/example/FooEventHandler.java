package com.example;

import com.example.service.a.events.FooEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author claus
 */
@Service
@EnableBinding(FooInbound.class)
public class FooEventHandler {
    
    @StreamListener(FooInbound.INPUT_FOO_EVENT)
    public void handleFooEvent(FooEvent fooEvent) {
        System.out.println(fooEvent.getValueA() + " und " + fooEvent.getValueB());
    }
    
    
}
