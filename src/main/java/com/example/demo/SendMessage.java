package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.apache.activemq.artemis.api.core.Message.HDR_DUPLICATE_DETECTION_ID;

@Component
public class SendMessage {

    private static Date date = new Date();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    private JmsTemplate jmsTemplate;

    @Retryable(value = {JMSException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void sendMessage(String QueueName, int finalMessageCount) {
        System.out.println(String.format("%s Sending %d", dateFormat.format(date), finalMessageCount));
        String msgId = UUID.randomUUID().toString();
        this.jmsTemplate.convertAndSend(QueueName, finalMessageCount, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty(String.valueOf(HDR_DUPLICATE_DETECTION_ID), msgId);
                return message;
            }
        });
    }
}