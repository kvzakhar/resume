package net.simplesoft.resume.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course extends AbstractFinishDateEntity<Long> implements Serializable, ProfileEntity {

	private static final long serialVersionUID = -7576863746938588835L;
	
	@Id
	@SequenceGenerator(name = "COURSE_ID_GENERATOR", sequenceName = "COURSE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(length=60)
	private String name;
	
	@Column(length=60)
	private String school;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profile", referencedColumnName = "id",  nullable = false)
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	Profile profile;
	
	public Course(){}
	
	@Override
	public Long getId() {
		return this.id;
	}	
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getFinishDate(), id, name, school);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		return Objects.equals(getFinishDate(), other.getFinishDate()) 
				&& Objects.equals(id,  other.id) 
				&& Objects.equals(name, other.name)
				&& Objects.equals(school, other.school);
	}

}
