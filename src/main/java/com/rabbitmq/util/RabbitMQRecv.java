package com.rabbitmq.util;
import com.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * Created by hanzhiqiang@lenztechretail.com
 * on 2019/6/15.
 */
public class RabbitMQRecv {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] argv) throws Exception {
        final RabbitMQRecv rabbitMQRecv = new RabbitMQRecv();
        Runnable runnable_test = ()->{
            try {
                rabbitMQRecv.recvMessage(Math.ceil(Math.random()*100)+"");
            }catch (Exception e){}
        };

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);

        rabbitMQRecv.MathOperationTest();
        for (int i = 0; i <2 ; i++) {
            final String flag = i+"";
            poolExecutor.submit(runnable_test);
        }


    }

    public void recvMessage(String flag) throws Exception{
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        //一次只接受一个未包装的消息
        channel.basicQos(1);
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" ["+flag+"] Received '" + message + "'");
            Thread.sleep(2000);
            //消息处理完成确认,delivery.getEnvelope().getDeliveryTag()信息标识
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
    public void MathOperationTest(){
        RabbitMQRecv tester = new RabbitMQRecv();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        List<String> names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(item -> getString(item));
//        names.forEach(item -> {
//            System.out.printf("%s %n",item);
//        });

        Optional<Integer> a=Optional.empty();
        System.out.println(a);

    }

    public static void getString(String str){
        System.out.printf("%s %n",str);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


}
