package net.simplesoft.resume.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="skill")
public class Skill extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SKILL_ID_GENERATOR", sequenceName="SKILL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SKILL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=50)
	private String category;

	@Column(nullable=false, length=2147483647)
	private String value;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile", nullable=false)
	private Profile profile;

	public Skill() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Skill))
			return false;
		Skill other = (Skill) obj;
		
		return Objects.equals(id, other.id) 
				&& Objects.equals(category, other.category) 
				&& Objects.equals(value, other.value);
	}
}