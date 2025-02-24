package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.retry.annotation.EnableRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableJms
@EnableRetry
public class AmqCoreRetry implements CommandLineRunner {

    private static String QNAME = "test-queue";

    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AmqCoreRetry.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        sendMessage();
    }

    public void sendMessage() {
        int messageCount = 0;
        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust the thread pool size as needed
        while (messageCount < 40000) {
            int finalMessageCount = messageCount;
            executor.submit(() -> {
                this.sendMessage.sendMessage(QNAME, finalMessageCount);
            });
            messageCount++;
        }
        executor.shutdown();
        applicationContext.close();
    }
}