package web.pojos;

import java.util.Date;

public class Consumer {
    
    private Integer consumer_id;
    
    private Date date;
    private String place;
    
    private User user; // FK to app.users

    
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

    public User getUserId() {
        return user;
    }

    public void setUserId(User user_id) {
        this.user = user_id;
    }
    
}
