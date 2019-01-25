package app.models;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Department {
	private Integer id;
	@NotBlank
	private String name;
	
	private Center center;	
	private Set<User> users = new LinkedHashSet<User>();
	private List<Integer> userIds = new ArrayList<Integer>();
	private Set<Expense> expenses = new HashSet<Expense>();
	private List<Integer> expenseIds = new ArrayList<Integer>();
	

	public Department() {
		super();
	}
	
	public Department(
		String name
	) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Center getCenter() {
		return center;
	}
	
	public void setCenter(Center center) {
		this.center = center;
	}
	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public List<Integer> getUserIds() {
		return userIds;
	}
	
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public Set<Expense> getExpenses() {
		return expenses;
	}
	
	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}
	
	public List<Integer> getExpenseIds() {
		return expenseIds;
	}
	
	public void setExpenseIds(List<Integer> expenseIds) {
		this.expenseIds = expenseIds;
	}
	
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}