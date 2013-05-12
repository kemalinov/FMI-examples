package db.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "app.consumers")
@NamedNativeQueries({
    	@NamedNativeQuery(name = "ConsumerEntity.findAll", query = "SELECT * FROM app.consumers", resultClass = ConsumerEntity.class),
	@NamedNativeQuery(name = "ConsumerEntity.findConsumerById", query = "SELECT * FROM app.consumers WHERE consumer_id = ?", resultClass = ConsumerEntity.class),
	@NamedNativeQuery(name = "ConsumerEntity.findUserIdByConsumerId", query = "SELECT user_id FROM app.consumers WHERE consumer_id = ?", resultClass = UserEntity.class)
})
public class ConsumerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consumer_id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String place;
    
    @OneToOne
    private UserEntity user_id; // FK to app.users

    
    public Integer getId() {
        return consumer_id;
    }

    public void setId(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getUserId() {
        return user_id;
    }

    public void setUserId(UserEntity user_id) {
        this.user_id = user_id;
    }
    
}
