package net.simplesoft.resume.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Skill;

public class SkillForm implements Serializable {
	private static final long serialVersionUID = 4135568197764740034L;
	@Valid
	private Set<Skill> items = new HashSet<>();
	
	public SkillForm() {
		super();
	}

	public SkillForm(Set<Skill> items) {
		super();
		this.items = items;
	}

	public Set<Skill> getItems() {
		return items;
	}

	public void setItems(Set<Skill> items) {
		this.items = items;
	}
}