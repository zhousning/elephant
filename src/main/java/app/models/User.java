package app.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
    private Integer id;
    private String name;
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    
	private Set<Role> roles = new LinkedHashSet<Role>();
	private Set<Expense> expenses = new LinkedHashSet<Expense>();
	private Department department;
	
	

	@Override
   	public String toString() {
   		return "User [name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password + "]";
   	}

   	public User() {
   		super();
   	}

   	public User(String email, String name, String password) {
   		super();
   		this.name = name;
   		this.email = email;
   		this.password = password;
   	}
   	
	public User(String email, String password) {
   		super();
   		this.email = email;
   		this.password = password;
   	}

	
   	public User(String name, String phone, String email, String password) {
   		super();
   		this.name = name;
   		this.phone = phone;
   		this.email = email;
   		this.password = password;
   	}
   	
	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
        this.name = name == null ? null : name.trim();
    }

   

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}