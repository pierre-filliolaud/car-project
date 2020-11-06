package com.univ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class CarService {

    public void sendMsg() {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");

            Queue queue = (Queue) applicationContext.getBean("queue");
            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            QueueConnection connection = factory.createQueueConnection();
            // Open a session without transaction and acknowledge automatic
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            // Start the connection
            connection.start();
            // Create a sender
            QueueSender sender = session.createSender(queue);
            // Create a message
            TextMessage msg = session.createTextMessage("MESSAGE IS SENT FROM BACKEND");
            //System.out.println(msg);
            // Send the message
            sender.send(msg, DeliveryMode.PERSISTENT,4,10000);
            // Close the session
            session.close();
            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
