package com.rabbitmq.util;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * Created by hanzhiqiang@lenztechretail.com
 * on 2019/6/15.
 */
public class RabbitMQSend {
    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] argv) throws Exception {
        // ��ȡ�������Լ�mqͨ��
        Connection connection = ConnectionUtil.getConnection();
        // �������д���ͨ��
        Channel channel = connection.createChannel();

        // ����������������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            // ��Ϣ����
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        //�ر�ͨ��������
        channel.close();
        connection.close();
    }
}
