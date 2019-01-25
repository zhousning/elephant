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

public class ExacctTwo {
	private Integer id;
	@NotBlank
	private String name;
	
	private Set<ExacctThree> exacctThrees = new LinkedHashSet<ExacctThree>();
	private List<Integer> exacctThreeIds = new ArrayList<Integer>();
	private ExacctOne exacctOne;	
	

	public ExacctTwo() {
		super();
	}
	
	public ExacctTwo(
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
	
	public Set<ExacctThree> getExacctThrees() {
		return exacctThrees;
	}
	
	public void setExacctThrees(Set<ExacctThree> exacctThrees) {
		this.exacctThrees = exacctThrees;
	}
	
	public List<Integer> getExacctThreeIds() {
		return exacctThreeIds;
	}
	
	public void setExacctThreeIds(List<Integer> exacctThreeIds) {
		this.exacctThreeIds = exacctThreeIds;
	}
	public ExacctOne getExacctOne() {
		return exacctOne;
	}
	
	public void setExacctOne(ExacctOne exacctOne) {
		this.exacctOne = exacctOne;
	}
	
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}