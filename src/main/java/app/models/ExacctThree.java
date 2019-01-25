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

public class ExacctThree {
	private Integer id;
	@NotBlank
	private String name;
	
	private ExacctTwo exacctTwo;	
	private Set<Expense> expenses = new LinkedHashSet<Expense>();
	private List<Integer> expenseIds = new ArrayList<Integer>();
	

	public ExacctThree() {
		super();
	}
	
	public ExacctThree(
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
	
	public ExacctTwo getExacctTwo() {
		return exacctTwo;
	}
	
	public void setExacctTwo(ExacctTwo exacctTwo) {
		this.exacctTwo = exacctTwo;
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