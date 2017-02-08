package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author claus
 */
public interface FooProcessor {
    
    public final String FOO_EVENT = "fooEvent";
    public final String BAR_EVENT = "barEvent";
    
    @Output(FOO_EVENT)
    MessageChannel fooEvent();
    
    @Output(BAR_EVENT)
    MessageChannel barEvent();
}
