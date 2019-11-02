package com.epam.cdp.hw3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.epam.cdp.hw3.Constants.ORDER_LOGGER_QUEUE;

public class LogProcessor {
    private static int count_rejected, count_passed;

    /**
     * Log orders
     * @param argv param
     * @throws Exception exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(ORDER_LOGGER_QUEUE, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            if ("Rejected".equalsIgnoreCase(message)) {
                count_rejected++;
            } else {
                count_passed++;
            }
            List<String> summary = new ArrayList<>();
            summary.add("Rejected: " + count_rejected);
            summary.add("Passed: " + count_passed);

            Files.write(Paths.get("./src/main/resources/summary.log"), summary, StandardCharsets.UTF_8);

            System.out.println(" [x] Received '" + message + "'");
            System.out.println(" [x] Done");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };
        channel.basicConsume(ORDER_LOGGER_QUEUE, false, deliverCallback, consumerTag -> {
        });

    }
}
