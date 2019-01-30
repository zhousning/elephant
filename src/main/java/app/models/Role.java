package app.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;

public class Role {
	private Integer id;
	@NotBlank
	private String name;
	private String label;
	private Set<User> users = new LinkedHashSet<User>();
	private Set<Permission> permissions = new LinkedHashSet<Permission>();
	private List<Integer> permissionIds = new ArrayList<Integer>();
	
	public Role(){}
	
	
	public Role(String name) {
		super();
		this.name = name;
	}


	public Role(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public List<Integer> getPermissionIds() {
		return permissionIds;
	}


	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	

}
