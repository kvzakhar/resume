package net.simplesoft.resume.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
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

import net.simplesoft.resume.model.LanguageLevel;
import net.simplesoft.resume.model.LanguageType;

@Entity
@Table(name="language")
public class Language extends AbstractEntity<Long> implements ProfileEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LANGUAGE_ID_GENERATOR", sequenceName="LANGUAGE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LANGUAGE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	@Convert(converter = LanguageLevel.PersistJPAConverter.class)
	private LanguageLevel level;

	@Column(nullable=false, length=30)
	private String name;
	
	@Column
	@Convert(converter = LanguageType.PersistJPAConverter.class)
	private LanguageType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_profile", nullable=false)
	private Profile profile;

	public Language() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LanguageLevel getLevel() {
		return level;
	}

	public void setLevel(LanguageLevel level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public LanguageType getType() {
		return type;
	}

	public void setType(LanguageType type) {
		this.type = type;
	}
	
	@Transient
	public boolean isHasLanguageType(){
		return type != LanguageType.ALL;
	}

	@Override
	public String toString() {
		return String.format("Language [id=%s, level=%s, name=%s, type=%s]", id, level, name, type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, level, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Language))
			return false;
		Language other = (Language) obj;
		
		return Objects.equals(id, other.id) 
				&& Objects.equals(name, other.name) 
				&& Objects.equals(level, other.level) 
				&& Objects.equals(type, other.type);
	}
}