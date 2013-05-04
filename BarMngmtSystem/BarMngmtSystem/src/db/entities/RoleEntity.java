package db.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "App.Roles")
@NamedNativeQueries({
	@NamedNativeQuery(name = "RoleEntity.findAll", query = "SELECT * FROM app.roles", resultClass = RoleEntity.class),
	@NamedNativeQuery(name = "RoleEntity.findRoleById", query = "SELECT * FROM app.roles WHERE role_id = ?", resultClass = RoleEntity.class)
// ? @NamedNativeQuery(name = "RoleEntity.findRoleByUserId", query =
// "SELECT roleid_role_id FROM app.user_role_map WHERE userid_user_id = ?",
// resultClass = RoleEntity.class) // like the nested one in UserEntity!
})
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    private String name;

    public Integer getId() {
	return role_id;
    }

    public void setId(Integer id) {
	this.role_id = id;
    }

    public String getRoleName() {
	return name;
    }

    public void setRoleName(String roleName) {
	this.name = roleName;
    }

}
