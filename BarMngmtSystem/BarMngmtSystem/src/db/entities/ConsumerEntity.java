package db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "app.consumers")
@NamedNativeQueries({
    	@NamedNativeQuery(name = "ConsumerEntity.findAll", query = "SELECT * FROM app.consumers", resultClass = ConsumerEntity.class),
	@NamedNativeQuery(name = "ConsumerEntity.findConsumerById", query = "SELECT * FROM app.consumers WHERE consumer_id = ?", resultClass = ConsumerEntity.class),
	@NamedNativeQuery(name = "ConsumerEntity.findUserIDByConsumerId", query = "SELECT user_id FROM app.consumers WHERE consumer_id = ?", resultClass = Integer.class)
})
public class ConsumerEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consumer_id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_;
    private String place;
    private Integer user_id; // FK to app.users

    
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
