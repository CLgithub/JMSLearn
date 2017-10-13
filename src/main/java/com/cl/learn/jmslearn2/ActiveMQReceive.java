package com.cl.learn.jmslearn2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by chenlei on 2017/10/13.
 * 消息消费者
 */
public class ActiveMQReceive {
    public static void main(String[] args){
        // 创建连接工厂类
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://127.0.0.1:61616"
        );
        try {
            Connection connection = connectionFactory.createConnection();// 利用连接工厂类创建连接
            connection.start(); //启动连接

            //利用连接创建session，前一个参数代表是否采用事务消息，
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("MessageQueue1");
            MessageConsumer consumer = session.createConsumer(destination);

            while (true){
                ObjectMessage message= (ObjectMessage) consumer.receive(10000);
                if(null!=message){
                    System.out.println(message.getObject());
                }else {
                    break;
                }
            }
        }catch (Exception e){

        }
    }
}
