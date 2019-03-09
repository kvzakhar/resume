package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Practic;

public class PracticForm {
	@Valid
	private List<Practic> items = new ArrayList<>();
	
	public PracticForm() {
		super();
	}

	public PracticForm(List<Practic> items) {
		this.items = items;
	}

	public List<Practic> getItems() {
		return items;
	}

	public void setItems(List<Practic> items) {
		this.items = items;
	}
}
