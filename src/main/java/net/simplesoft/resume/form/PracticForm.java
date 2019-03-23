package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Practic;

public class PracticForm {
	@Valid
	private Set<Practic> items = new HashSet<>();
	
	public PracticForm() {
		super();
	}

	public PracticForm(Set<Practic> items) {
		this.items = items;
	}

	public Set<Practic> getItems() {
		return items;
	}

	public void setItems(Set<Practic> items) {
		this.items = items;
	}
}
