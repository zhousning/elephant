package app.models;

import java.util.HashSet;
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

public class Expense {
	private Integer id;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	private String staffid;
	private String staffname;
	private Float sum;
	private String info;
	
	private ExacctThree exacctThree;	
	private Department department;	
	private User user;	
	private Explain explain;
	

	public Expense() {
		super();
	}
	
	public Expense(
			Date date,
			String staffid,
			String staffname,
			Float sum,
			String info
		) {
			super();
			this.date = date;
			this.sum = sum;
			this.staffid = staffid;
			this.staffname = staffname;
			this.sum = sum;
			this.info = info;
		}
	
	public Expense(
		Date date,
		Float sum
	) {
		super();
		this.date = date;
		this.sum = sum;
	}
	
	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getSum() {
		return sum;
	}
	
	public void setSum(Float sum) {
		this.sum = sum;
	}
	
	public ExacctThree getExacctThree() {
		return exacctThree;
	}
	
	public void setExacctThree(ExacctThree exacctThree) {
		this.exacctThree = exacctThree;
	}
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Explain getExplain() {
		return explain;
	}

	public void setExplain(Explain explain) {
		this.explain = explain;
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}