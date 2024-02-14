package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import java.util.UUID;

@SpringBootApplication
@EnableJms
public class AmqCoreClient implements CommandLineRunner {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AmqCoreClient.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            String msg = UUID.randomUUID().toString();
            System.out.println(String.format("Sending '%s'", msg));
            sendMessage(msg, null);
        }
    }

    public void sendMessage(String msg, Exception t) {
        try {
            this.jmsTemplate.convertAndSend("test-queue", msg);
        } catch (JmsException e) {
            System.out.println(e.getMessage());
            while (e.getMessage().matches("(.*)Address(.*)is(\\s)full(.*)")) {
                System.err.printf("message is not sent %s", msg);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                sendMessage(msg ,e);
            }
        }
/*		new Thread(() -> {
            String text = UUID.randomUUID().toString();
            System.out.println(String.format("Sending '%s'", text));
            this.jmsTemplate.convertAndSend("test-queue", text);
        }
		).start();*/

    }

    @JmsListener(destination = "test-queue")
    public void receiveMessage(String text) {
        System.err.println(String.format("Received '%s'", text));
    }
}
