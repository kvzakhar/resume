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
import javax.persistence.Transient;

@Entity
@Table(name="education")
public class Education extends AbstractEntity<Long> implements ProfileEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATION_ID_GENERATOR", sequenceName="EDUCATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATION_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=255)
	private String faculty;

	@Column(nullable=false, length=100)
	private String summary;

	@Column(nullable=false, length=2147483647)
	private String university;
	
	@Column(name="begin_year", nullable=false)
	private Integer beginYear;
	
	@Column(name="finish_year")
	private Integer finishYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_profile", referencedColumnName = "id", nullable=false)
	private Profile profile;

	public Education() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Integer getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(Integer beginYear) {
		this.beginYear = beginYear;
	}

	public Integer getFinishYear() {
		return finishYear;
	}

	public void setFinishYear(Integer finishYear) {
		this.finishYear = finishYear;
	}
	
	@Transient
	public boolean isFinish(){
		return finishYear != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, university);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Education))
			return false;
		Education other = (Education) obj;
		return Objects.equals(id, other.id) && Objects.equals(university, other.university);
	}
}
