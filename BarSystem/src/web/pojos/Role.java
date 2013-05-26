package web.pojos;

public class Role {

	private Integer id;
	private String role;

	public Role(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public Integer getId() {
		return id;
	}

}
