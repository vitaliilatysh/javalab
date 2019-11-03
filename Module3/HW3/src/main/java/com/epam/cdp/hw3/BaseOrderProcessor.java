package com.epam.cdp.hw3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.MessageProperties;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.epam.cdp.hw3.Constants.ORDER_LOGGER_QUEUE;
import static com.epam.cdp.hw3.Constants.ORDER_TOTAL_THRESHOLD;

abstract class BaseOrderProcessor {

    static boolean checkTotalOrder(Order order) {
        List<Good> goodList = order.getListGoods();
        BigDecimal totalOrder = goodList.stream().map(Good::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(goodList.stream().map(Good::getAmount).reduce(0, Integer::sum)));
        return totalOrder.doubleValue() < ORDER_TOTAL_THRESHOLD;
    }

    static void publishLog(Channel channel, Order order, String status) throws IOException {
        channel.basicPublish("", ORDER_LOGGER_QUEUE,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                status.getBytes(StandardCharsets.UTF_8));
        System.out.println(String.format(" [x] %s '" + order.toString() + "'", status));
    }

    static Order deserialize(Delivery delivery) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
        ObjectInput in = null;
        Order order = null;
        try {
            in = new ObjectInputStream(bis);
            order = (Order) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return order;
    }
}
