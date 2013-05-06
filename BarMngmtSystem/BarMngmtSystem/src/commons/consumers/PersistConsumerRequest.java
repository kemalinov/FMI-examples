package commons.consumers;

import commons.dtos.ConsumerDTO;

public class PersistConsumerRequest {

    private ConsumerDTO consumer;
    
    public PersistConsumerRequest(ConsumerDTO consumer) {
	this.setConsumer(consumer);
    }

    public ConsumerDTO getConsumer() {
	return consumer;
    }

    public void setConsumer(ConsumerDTO consumer) {
	this.consumer = consumer;
    }
}
