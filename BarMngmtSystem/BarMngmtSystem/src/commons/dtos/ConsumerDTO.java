package commons.dtos;

import java.util.Date;

public class ConsumerDTO {

    private Integer consumer_id;
    private Date time_;
    private String place;
    private Integer user_id; // FK to app.users
    
    public ConsumerDTO(Integer id, Date date, String table, Integer userId) {
	this.consumer_id = id;
	this.time_ = date;
	this.place = table;
	this.user_id = userId;
    }

    public Integer getConsumer_id() {
	return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
	this.consumer_id = consumer_id;
    }

    public String getPlace() {
	return place;
    }

    public void setPlace(String place) {
	this.place = place;
    }

    public Date getTime_() {
	return time_;
    }

    public void setTime_(Date time_) {
	this.time_ = time_;
    }

    public Integer getUserId() {
	return user_id;
    }

    public void setUserId(Integer user_id) {
	this.user_id = user_id;
    }
}
