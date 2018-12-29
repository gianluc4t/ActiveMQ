package com.gt.queue.rabbitmq.sample.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gt.queue.rabbitmq.sample.util.QueueEnum;

public class MessageSender {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static String URL = ActiveMQConnection.DEFAULT_BROKER_URL; // tcp://localhost:61616"

	public void sendTextMessage(String inputMessage, QueueEnum queue) throws JMSException {
		LOGGER.info(String.format("sending %s to [%s]", inputMessage, queue.toString()));

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue.toString());

			MessageProducer producer = session.createProducer(destination);

			TextMessage textMessage = session.createTextMessage(inputMessage);
			producer.send(textMessage);

			connection.close();
		} catch (JMSException e) {
			LOGGER.error(String.format("ERROR sending %s to [%s]", inputMessage, queue.toString()));
			throw e;
		}
	}
}