package net.simplesoft.resume.entity;

import java.sql.Date;
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

import org.joda.time.DateTime;

@Entity
@Table(name = "practic")
public class Practic extends AbstractFinishDateEntity<Long> implements ProfileEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRACTIC_ID_GENERATOR", sequenceName = "PRACTIC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRACTIC_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 100)
	private String company;

	@Column(length = 255)
	private String demo;

	@Column(length = 255)
	private String src;

	@Column(nullable = false, length = 100)
	private String position;

	@Column(nullable = false, length = 2147483647)
	private String responsibilities;

	@Column(name = "begin_date", nullable = false)
	private Date beginDate;

	@Transient
	private Integer beginDateMonth;

	@Transient
	private Integer beginDateYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profile", nullable = false)
	private Profile profile;

	public Practic() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDemo() {
		return this.demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getResponsibilities() {
		return this.responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Transient
	public Integer getBeginDateMonth() {
		if (beginDate != null) {
			return new DateTime(beginDate).getMonthOfYear();
		} else {
			return null;
		}
	}

	@Transient
	public Integer getBeginDateYear() {
		if (beginDate != null) {
			return new DateTime(beginDate).getYear();
		} else {
			return null;
		}
	}

	public void setBeginDateMonth(Integer beginDateMonth) {
		this.beginDateMonth = beginDateMonth;
		setupBeginDate();
	}

	public void setBeginDateYear(Integer beginDateYear) {
		this.beginDateYear = beginDateYear;
		setupBeginDate();
	}

	private void setupBeginDate() {
		if (beginDateYear != null && beginDateMonth != null) {
			setBeginDate(new Date(new DateTime(beginDateYear, beginDateMonth, 1, 0, 0).getMillis()));
		} else {
			setBeginDate(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, company, position, getFinishDate());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Practic))
			return false;
		Practic other = (Practic) obj;
	
		return Objects.equals(id, other.id) 
		&& Objects.equals(company, other.company) 
		&& Objects.equals(position, other.position) 
		&& Objects.equals(getFinishDate(), other.getFinishDate());
	}
}
