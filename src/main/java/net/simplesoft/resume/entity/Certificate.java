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
@Table(name="certificate")
public class Certificate extends AbstractEntity<Long> implements ProfileEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, nullable = false)
	@SequenceGenerator(name="CERTIFICATE_ID_GENERATOR", sequenceName="CERTIFICATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="CERTIFICATE_ID_GENERATOR")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}
	
	@Column(name="large_url", nullable=false, length=255)
	private String largeUrl;
	
	@Column(name="small_url", nullable=false, length=255)
	private String smallUrl;

	@Column(nullable=false, length=50)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_profile", referencedColumnName = "id", nullable = false)
	Profile profile;
	
	public Certificate() {}	

	public void setId(Long id) {
		this.id = id;
	}

	public String getLargeUrl() {
		return largeUrl;
	}

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Certificate))
			return false;
		
		Certificate other = (Certificate) obj;
		
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}	

}
