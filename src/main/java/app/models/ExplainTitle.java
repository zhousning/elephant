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

public class ExplainTitle {
	private Integer id;
	private String name;
	private Integer sequence;
	
	private Explain explain;	
	

	public ExplainTitle() {
		super();
	}
	
	public ExplainTitle(
		String name,
		Integer sequence
	) {
		super();
		this.name = name;
		this.sequence = sequence;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Integer getsequence() {
		return sequence;
	}
	
	public void setsequence(Integer sequence) {
		this.sequence = sequence;
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