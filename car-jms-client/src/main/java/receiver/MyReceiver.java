package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");

			QueueConnection connection = null;
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			connection = factory.createQueueConnection();
			// Open a session
			QueueSession session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
			//Call of a class named 'SESSION' which have an object named 'AUTO_AKNOWLEGDGE'
			// start the connection
			connection.start();
			// Create a receive
			QueueReceiver receiver = session.createReceiver(queue);
			// Receive the message
			while (true) {
				Message m = receiver.receive();
				System.out.println("Message is" + m);//display the message sent
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
