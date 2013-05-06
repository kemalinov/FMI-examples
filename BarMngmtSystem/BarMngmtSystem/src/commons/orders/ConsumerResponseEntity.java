package commons.orders;

import commons.dtos.ConsumerDTO;

public class ConsumerResponseEntity {

    private ConsumerDTO consumer;
    
    public ConsumerResponseEntity(ConsumerDTO consumer) {
	this.consumer = consumer;
    }

    public ConsumerDTO getConsumer() {
	return consumer;
    }

    public void setConsumer(ConsumerDTO consumer) {
	this.consumer = consumer;
    }
}
