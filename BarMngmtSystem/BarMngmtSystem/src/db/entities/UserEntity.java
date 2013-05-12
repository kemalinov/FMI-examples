package db.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "App.Users")
@NamedNativeQueries({
	@NamedNativeQuery(name = "UserEntity.findAll", query = "SELECT * FROM app.users", resultClass = UserEntity.class),
	@NamedNativeQuery(name = "UserEntity.findUserById", query = "SELECT * FROM app.users WHERE user_id = ?", resultClass = UserEntity.class),
	@NamedNativeQuery(name = "UserEntity.findUsersByRoleId", query = "SELECT * FROM APP.USERS "
		+ "WHERE user_id = ( "
		+ "SELECT userid_user_id "
		+ "FROM APP.USER_ROLE_MAP "
		+ "WHERE roleid_role_id = ? "
		+ ") " + "ORDER by user_id ASC", resultClass = UserEntity.class) })
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String name;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "App.user_role_map", joinColumns =
    // join to the current entity
    @JoinColumn(name = "userid_user_id", referencedColumnName = "user_id"), inverseJoinColumns =
    // "role_id" - a column in "roles" table
    @JoinColumn(name = "roleid_role_id", referencedColumnName = "role_id"))
    private RoleEntity role;

    //active
    
    public Integer getId() {
	return user_id;
    }

    public void setId(Integer user_id) {
	this.user_id = user_id;
    }

    public String getName() {
	return name;
    }

    public void setName(String username) {
	this.name = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public RoleEntity getRole() {
	return role;
    }

    public void setRole(RoleEntity role_id) {
	this.role = role_id;
    }

}
