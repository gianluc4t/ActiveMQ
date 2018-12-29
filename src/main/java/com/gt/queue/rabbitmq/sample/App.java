package com.gt.queue.rabbitmq.sample;

import java.time.LocalDateTime;

import com.gt.queue.rabbitmq.sample.receiver.MessageReceiver;
import com.gt.queue.rabbitmq.sample.sender.MessageSender;
import com.gt.queue.rabbitmq.sample.util.QueueEnum;

public class App {

	public static void main(String[] args) throws Exception {
		
		
		MessageSender sender = new MessageSender();
		sender.sendTextMessage(String.format("message send at %s",LocalDateTime.now()), QueueEnum.ZANZARIERA);

		
		MessageReceiver receiver = new MessageReceiver();
		receiver.receive(QueueEnum.ZANZARIERA, true);
	}
}