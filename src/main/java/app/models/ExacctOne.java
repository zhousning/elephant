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

public class ExacctOne {
	private Integer id;
	private String name;
	
	private Set<ExacctTwo> exacctTwos = new LinkedHashSet<ExacctTwo>();
	private List<Integer> exacctTwoIds = new ArrayList<Integer>();
	

	public ExacctOne() {
		super();
	}
	
	public ExacctOne(
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
	
	public Set<ExacctTwo> getExacctTwos() {
		return exacctTwos;
	}
	
	public void setExacctTwos(Set<ExacctTwo> exacctTwos) {
		this.exacctTwos = exacctTwos;
	}
	
	public List<Integer> getExacctTwoIds() {
		return exacctTwoIds;
	}
	
	public void setExacctTwoIds(List<Integer> exacctTwoIds) {
		this.exacctTwoIds = exacctTwoIds;
	}
	
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}