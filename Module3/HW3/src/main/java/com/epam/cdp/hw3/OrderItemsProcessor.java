package com.epam.cdp.hw3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import static com.epam.cdp.hw3.Constants.*;

public class OrderItemsProcessor extends BaseOrderProcessor {

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

        channel.queueDeclare(ORDER_ITEM_QUEUE, true, false, false, null);
        channel.queueDeclare(ORDER_LOGGER_QUEUE, true, false, false, null);

        channel.queueBind(ORDER_ITEM_QUEUE, EXCHANGE_NAME, "ITEMS");

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
        channel.basicConsume(ORDER_ITEM_QUEUE, false, deliverCallback, consumerTag -> {
        });
    }

    private static boolean isOrderValid(Order order) {
        return checkTotalOrder(order);
    }

}
