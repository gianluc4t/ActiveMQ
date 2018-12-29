package com.gt.queue.rabbitmq.sample.util;

public enum QueueEnum {
	ZANZARIERA("Zanzariera"), CARNAIO("Carnaio"), GUERRAFOSSO("Guerrafosso");
	private String subject;

	QueueEnum(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return this.subject;
	}
}
