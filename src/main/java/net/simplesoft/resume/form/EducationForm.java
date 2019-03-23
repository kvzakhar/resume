package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Education;

public class EducationForm {
	@Valid
	private Set<Education> items = new HashSet();
	
	public EducationForm() {
		super();
	}

	public EducationForm(Set<Education> items) {
		super();
		this.items = items;
	}

	public Set<Education> getItems() {
		return items;
	}

	public void setItems(Set<Education> items) {
		this.items = items;
	}
}