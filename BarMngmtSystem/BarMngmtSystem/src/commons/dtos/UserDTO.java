package commons.dtos;


public class UserDTO {

    private Integer id;
    private String name;
    private String password;
    private RoleDTO roleDto;

    public UserDTO(Integer id, String name, String password, RoleDTO roleDto) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.roleDto = roleDto;
    }

    public void setRoleDto(RoleDTO roleDto) {
	this.roleDto = roleDto;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setName(String name) {
	this.name = name;
    }

    public RoleDTO getRoleDto() {
	return roleDto;
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
