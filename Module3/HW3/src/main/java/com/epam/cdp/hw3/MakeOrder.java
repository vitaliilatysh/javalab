package com.epam.cdp.hw3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.epam.cdp.hw3.Constants.ORDER_QUEUE;

public class MakeOrder {

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(ORDER_QUEUE, true, false, false, null);

            Scanner in = new Scanner(System.in);
            Order order = new Order();
            String stopWord = "send";
            while (in.hasNext()) {
                String s = in.nextLine();
                order.setCustomerName(s);

                System.out.println("Add goods to order. Type 'send' when finished.");
                List<Good> goods = new ArrayList<>();
                while (in.hasNext()) {
                    String goodRow = in.nextLine();
                    if (goodRow.equalsIgnoreCase(stopWord)) {
                        break;
                    }
                    Good goodItem = new Good();

                    String[] goodParts = goodRow.split(",");
                    goodItem.setName(goodParts[0]);

                    if (goodParts[1].equalsIgnoreCase(GoodType.LIQUIDS.name())) {
                        goodItem.setType(GoodType.LIQUIDS);
                    } else {
                        goodItem.setType(GoodType.ITEMS);
                    }

                    goodItem.setAmount(Integer.parseInt(goodParts[2].trim()));
                    goodItem.setPrice(new BigDecimal(Double.parseDouble(goodParts[3])));


                    goods.add(goodItem);
                }
                order.setListGoods(goods);
                byte[] orderBytes = serialize(order);

                channel.basicPublish("", ORDER_QUEUE,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        orderBytes);
                System.out.println(" [x] Sent '" + order.toString() + "'");
            }
        }
    }

    public static byte[] serialize(Order order) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        byte[] orderBytes;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(order);
            out.flush();
            orderBytes = bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return orderBytes;
    }

}
