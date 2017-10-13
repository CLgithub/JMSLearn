package com.cl.learn.jmslearn2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by L on 17/10/12.
 *
 * 准备在ubuntu下安装activemq，
 * 正确配置java环境变量：
 *      export JAVA_HOME=/usr/lib/jvm/java-8-oracle
         export CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
         export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

    下载activemq包，解压，编辑bin/env文件，设置JAVA_HOME

    ./activemq start 启动
    ./activemq stop 停止
    ./activemq status 查看状态
 */
public class ActiveMQProducer {

    public static void main(String[] args){
        // 创建连接工厂类
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://127.0.0.1:61616"
        );
        try {
            Connection connection=connectionFactory.createConnection();// 利用连接工厂类创建连接
            connection.start(); //启动连接

            //利用连接创建session，前一个参数代表是否采用事务消息，
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//            Queue messageQueue1 = session.createQueue("MessageQueue1");
            Destination destination=session.createQueue("MessageQueue1");   //创建一个消息通道
            MessageProducer producer = session.createProducer(destination); //创建一个producer 生产者 发送者 使用消息通道destination
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            ObjectMessage objectMessage = session.createObjectMessage("你们好 everyone!");   //创建一条消息
            producer.send(objectMessage);   //发送消息
            session.commit();   //提交
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
