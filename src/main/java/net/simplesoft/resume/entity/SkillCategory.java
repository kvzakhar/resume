package net.simplesoft.resume.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="skill_category")
public class SkillCategory extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 8798196791047283617L;
	
	@Id
	@Column
	private Long id;
	
	@Column(nullable=false, length=50)
	private String category;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category);
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof SkillCategory))
			return false;
		SkillCategory other = (SkillCategory) obj;
		
		return Objects.equals(id, other.id) 
				&& Objects.equals(category, other.category);
	}
	
}
