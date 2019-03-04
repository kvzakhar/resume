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
@Table(name="hobby")
public class Hobby extends AbstractEntity<Long> implements Comparable<Hobby>, ProfileEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOBBY_ID_GENERATOR", sequenceName="HOBBY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOBBY_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to Profile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile", referencedColumnName = "id", nullable=false)
	private Profile profile;
	
	@Transient
	private boolean selected;

	public Hobby() {
	}

	public Hobby(String name) {
		super();
		this.name = name;
	}

	public Hobby(String name, boolean selected) {
		super();
		this.name = name;
		this.selected = selected;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Transient
	public String getCssClassName(){
		return name.replace(" ", "-").toLowerCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Hobby))
			return false;
		Hobby other = (Hobby) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	@Override
	public int compareTo(Hobby o) {
		if(o == null || getName() == null) {
			return 1;
		} else{
			return getName().compareTo(o.getName());
		}
	}

	@Override
	public String toString() {
		return String.format("Hobby [name=%s]", name);
	}
}
