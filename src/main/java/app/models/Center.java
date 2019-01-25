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

public class Center {
	private Integer id;
	@NotBlank
	private String name;
	
	private Set<Department> departments = new LinkedHashSet<Department>();
	private List<Integer> departmentIds = new ArrayList<Integer>();
	

	public Center() {
		super();
	}
	
	public Center(
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
	
	public Set<Department> getDepartments() {
		return departments;
	}
	
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	public List<Integer> getDepartmentIds() {
		return departmentIds;
	}
	
	public void setDepartmentIds(List<Integer> departmentIds) {
		this.departmentIds = departmentIds;
	}
	
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}