package net.simplesoft.resume.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.simplesoft.resume.annotation.ProfileDataFieldGroup;
import net.simplesoft.resume.annotation.constraints.Adulthood;
import net.simplesoft.resume.annotation.constraints.EnglishLanguage;
import net.simplesoft.resume.annotation.constraints.Phone;

@Entity
@Table(name = "profile")
//@Document(indexName="profile")
public class Profile extends AbstractEntity<Long>{

	private static final long serialVersionUID = -1011495888866727912L;

	@Id
	@SequenceGenerator(name = "PROFILE_ID_GENERATOR", sequenceName = "PROFILE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@ProfileDataFieldGroup
	@Column(name = "birth_day")
	@Adulthood
	private Date birthDay;
	
	@Version
	@Column(name="optlock")
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private int versionNum;	

	@ProfileDataFieldGroup
	@Column
	@Size(max=100)
	@NotNull
	@SafeHtml
	@EnglishLanguage(withNumbers=false, withSpechSymbols=false)
	private String city;

	@ProfileDataFieldGroup
	@Column
	@Size(max=60)
	@NotNull
	@SafeHtml
	@EnglishLanguage(withNumbers=false, withSpechSymbols=false)
	private String country;

	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@ProfileDataFieldGroup
	@Column(length = 2147483647)
	@NotNull
	@SafeHtml
	@EnglishLanguage
	private String objective;

	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	@Column(name = "large_photo", length = 255)
	private String largePhoto;

	@Column(name = "small_photo", length = 255)
	private String smallPhoto;

	@ProfileDataFieldGroup
	@Size(min = 9, max=20)
	@Phone
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private String phone;

	@ProfileDataFieldGroup
	@Column
	@NotNull
	@Size(max=100)
	@Email
	@EnglishLanguage
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private String email;
	
	@ProfileDataFieldGroup
	@Column
	private String info;

	@ProfileDataFieldGroup
	@Column(length = 2147483647)
	@NotNull
	@SafeHtml
	@EnglishLanguage
	private String summary;

	@Column(nullable = false, length = 100)
	private String uid;
	
	@Column(nullable = false, length = 100)
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private String password;
	
	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	@Column(nullable = false)
	private boolean completed;
	
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	@Column(insertable=false)
	private Timestamp created;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Certificate> certificates;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("finishYear DESC, beginYear DESC, id DESC")
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Education> educations;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("name ASC")
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Hobby> hobbies;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	//@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Language> languages;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("finishDate DESC")
	//@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Practic> practics;

	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("id ASC")
//	@JsonIgnore // in this case it relates to elasctic search(do not include field in index)
	private Set<Skill> skills;
	
	@OneToMany(mappedBy = "profile", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("finishDate DESC")
	private Set<Course> courses;
	
	@Embedded
	private Contacts contacts;

	public Profile() {
	}	

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersionNum() { 
		return versionNum; 
	}	

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Set<Certificate> getCertificates() {
		return this.certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Set<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	public Set<Hobby> getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Set<Language> getLanguages() {
		return this.languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public Set<Practic> getPractics() {
		return this.practics;
	}

	public void setPractics(Set<Practic> practics) {
		this.practics = practics;
	}

	public Set<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
		updateListSetProfile(this.skills);		
	}

	private void updateListSetProfile(Set<? extends ProfileEntity> list) {
		if(list != null) {
			list.forEach(s->{s.setProfile(this);});
		}
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
		updateListSetProfile(this.courses);
	}

	public String getLargePhoto() {
		return largePhoto;
	}

	public void setLargePhoto(String largePhoto) {
		this.largePhoto = largePhoto;
	}

	public String getSmallPhoto() {
		return smallPhoto;
	}

	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	@Transient
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	@Transient
	public int getAge(){
		LocalDate birthdate = new LocalDate (birthDay);
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);
		return age.getYears();
	}
	
	@Transient
	public String getProfilePhoto(){
		if(largePhoto != null) {
			return largePhoto;
		} else {
			return "/static/img/profile-placeholder.png";
		}
	}
	
	public String updateProfilePhotos(String largePhoto, String smallPhoto) {
		String oldLargeImage = this.largePhoto;
		setLargePhoto(largePhoto);
		setSmallPhoto(smallPhoto);
		return oldLargeImage;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Contacts getContacts() {
		if(contacts == null) {
			contacts = new Contacts();
		}
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

}
