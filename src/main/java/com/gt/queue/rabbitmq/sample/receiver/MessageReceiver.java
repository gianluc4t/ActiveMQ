package com.gt.queue.rabbitmq.sample.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gt.queue.rabbitmq.sample.util.QueueEnum;

public class MessageReceiver {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static String URL = ActiveMQConnection.DEFAULT_BROKER_URL; // tcp://localhost:61616"

	public void receive(QueueEnum queue, boolean loop) throws JMSException {
		LOGGER.info(String.format("receiving from [%s]", queue.toString()));

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue.toString());

			MessageConsumer consumer = session.createConsumer(destination);

			while (loop) {
				Message message = consumer.receive();

				if (message instanceof TextMessage) {
					process((TextMessage) message);
				}
			}
			connection.close();

		} catch (JMSException e) {
			LOGGER.error(String.format("ERROR receiving from [%s]", e.getMessage()));
			throw e;
		}

	}

	private void process(TextMessage textMessage) throws JMSException {
		LOGGER.info(String.format("Received message '%s'", textMessage.getText()));
	}

}