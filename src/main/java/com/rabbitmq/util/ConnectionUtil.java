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
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("172.16.0.46");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
