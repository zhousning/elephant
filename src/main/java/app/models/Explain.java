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

public class Explain {
	private Integer id;
	private String name;
	
	private Set<Expense> expenses = new LinkedHashSet<Expense>();
	private List<Integer> expenseIds = new ArrayList<Integer>();
	private Set<ExplainTitle> explainTitles = new LinkedHashSet<ExplainTitle>();
	private List<Integer> explainTitleIds = new ArrayList<Integer>();
	

	public Explain() {
		super();
	}
	
	public Explain(
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
	public Set<ExplainTitle> getExplainTitles() {
		return explainTitles;
	}
	
	public void setExplainTitles(Set<ExplainTitle> explainTitles) {
		this.explainTitles = explainTitles;
	}
	
	public List<Integer> getExplainTitleIds() {
		return explainTitleIds;
	}
	
	public void setExplainTitleIds(List<Integer> explainTitleIds) {
		this.explainTitleIds = explainTitleIds;
	}
	
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}