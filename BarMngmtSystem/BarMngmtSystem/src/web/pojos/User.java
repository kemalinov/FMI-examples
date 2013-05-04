package web.pojos;


public class User {

    private Integer id;
    private String name;
    private String password;
    private Role role;

    public User(Integer id, String name, String password, Role roleDto) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.role = roleDto;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Role getRole() {
	return role;
    }

    public String getPassword() {
	return password;
    }

    public String getName() {
	return name;
    }

    public Integer getId() {
	return id;
    }

}
