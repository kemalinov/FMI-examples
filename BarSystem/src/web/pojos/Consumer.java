package web.pojos;

import java.util.Date;

import ejb.User;

public class Consumer {

	private Integer id;

	private Date date;
	private String place;

	private User user; // FK to app.users
	private Boolean isClosed;

	public Consumer(Integer id, Date date, String place, Boolean isClosed, User user) {
		this.id = id;
		this.date = date;
		this.place = place;
		this.user = user;
		this.isClosed = isClosed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer consumer_id) {
		this.id = consumer_id;
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

	public Boolean isClosed() {
		return isClosed;
	}

	public void setClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

}
