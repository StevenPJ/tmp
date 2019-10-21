package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class JmsSender implements Dispatcher {

    private JmsTemplate jmsTemplate;

    @Override
    public void dispatch(Event event) {
        jmsTemplate.send(event.getDestination(), s -> {
            TextMessage message = s.createTextMessage(event.getContent());
            message.setJMSCorrelationID(event.props("correlationId"));
            return message;
        });

    }
}
