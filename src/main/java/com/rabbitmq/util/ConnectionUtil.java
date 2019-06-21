package com.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
/**
 * Created by hanzhiqiang@lenztechretail.com
 * on 2019/6/15.
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        //�������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        //���÷����ַ
        factory.setHost("172.16.0.46");
        //�˿�
        factory.setPort(5672);
        //�����˺���Ϣ���û��������롢vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // ͨ�����̻�ȡ����
        Connection connection = factory.newConnection();
        return connection;
    }
}
