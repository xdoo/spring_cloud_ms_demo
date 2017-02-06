package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author claus
 */
public interface FooOutbound {
    
    public final String FOO_EVENT = "fooEvent";
    
    @Output(FOO_EVENT)
    MessageChannel fooEvent();
}
