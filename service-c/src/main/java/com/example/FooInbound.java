package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author claus
 */
public interface FooInbound {
   
    public final String INPUT_FOO_EVENT = "fooEvent";
    
    @Input(INPUT_FOO_EVENT)
    SubscribableChannel fooEvent();
    
}
