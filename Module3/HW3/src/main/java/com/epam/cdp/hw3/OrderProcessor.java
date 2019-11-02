package com.epam.cdp.hw3;

import com.rabbitmq.client.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.epam.cdp.hw3.Constants.*;

public class OrderProcessor {
    /**
     * Order processing
     * @param argv param
     * @throws Exception exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(ORDER_QUEUE, true, false, false, null);
        channel.queueDeclare(ORDER_LOGGER_QUEUE, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            Order order = deserialize(delivery);

            List<Good> goodList = order.getListGoods();
            BigDecimal totalOrder = goodList.stream().map(Good::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                    .multiply(new BigDecimal(goodList.stream().map(Good::getAmount).reduce(0, Integer::sum)));

            if (totalOrder.doubleValue() > ORDER_TOTAL_THRESHOLD) {
                channel.basicPublish("", ORDER_LOGGER_QUEUE,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        "Rejected".getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Rejected '" + order.toString() + "'");
            } else {
                channel.basicPublish("", ORDER_LOGGER_QUEUE,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        "Passed".getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Passed '" + order.toString() + "'");
            }

            System.out.println(" [x] Done");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };
        channel.basicConsume(ORDER_QUEUE, false, deliverCallback, consumerTag -> {
        });
    }

    private static Order deserialize(Delivery delivery) throws IOException {
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
