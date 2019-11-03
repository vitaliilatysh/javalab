package com.epam.cdp.hw3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.cdp.hw3.Constants.*;

public class OrderLiquidsProcessor extends BaseOrderProcessor {

    private static Map<String, Integer> userOrderedLiquids = new HashMap<>();
    private static Map<String, Integer> litersPerGood = readFile().stream()
            .filter(line -> line.contains("liquids"))
            .map(row -> row.split(","))
            .map(array -> new Good(array[0], null, Integer.parseInt(array[2]), null))
            .collect(Collectors.toMap(Good::getName, Good::getAmount));


    private static List<String> readFile() {
        try {
            return Files.lines(Paths.get("./src/main/resources/products.csv")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Order processing
     *
     * @param argv param
     * @throws Exception exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(ORDER_LIQUID_QUEUE, true, false, false, null);
        channel.queueDeclare(ORDER_LOGGER_QUEUE, true, false, false, null);

        channel.queueBind(ORDER_LIQUID_QUEUE, EXCHANGE_NAME, "LIQUIDS");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            Order order = deserialize(delivery);

            if (!isOrderValid(order)) {
                publishLog(channel, order, "Rejected");
            } else {
                publishLog(channel, order, "Passed");
            }
            System.out.println(" [x] Done");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };
        channel.basicConsume(ORDER_LIQUID_QUEUE, false, deliverCallback, consumerTag -> {
        });
    }

    private static boolean isOrderValid(Order order) {
        return checkTotalOrder(order) && checkThresholdLiters(order);
    }

    private static boolean checkThresholdLiters(Order order) {
        int orderedLiters = order.getListGoods().get(0).getAmount();
        String good = order.getListGoods().get(0).getName();

        if (orderedLiters == 0) {
            return false;
        }

        String userName = order.getCustomerName();

        if ((litersPerGood.get(good) - orderedLiters) < 0) {
            return false;
        }

        if (userOrderedLiquids.get(userName) == null) {
            userOrderedLiquids.put(userName, orderedLiters);
        } else {
            int alreadyOrderedLiters = userOrderedLiquids.get(userName);
            userOrderedLiquids.put(userName, alreadyOrderedLiters + orderedLiters);
        }
        litersPerGood.put(good, litersPerGood.get(good) - orderedLiters);

        return true;

    }

}
