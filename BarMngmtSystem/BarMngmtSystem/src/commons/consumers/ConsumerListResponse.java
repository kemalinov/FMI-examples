package commons.consumers;

import java.util.List;

import commons.dtos.ConsumerDTO;


public class ConsumerListResponse {
    
    private List<ConsumerDTO> consumers;
    
    public ConsumerListResponse(List<ConsumerDTO> consumers) {
	this.consumers = consumers;
    }

    public List<ConsumerDTO> getConsumers() {
	return consumers;
    }

    public void setConsumers(List<ConsumerDTO> consumers) {
	this.consumers = consumers;
    }
    
    

}
