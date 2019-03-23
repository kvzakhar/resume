package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Language;

public class LanguageForm {
	@Valid
	private Set<Language> items = new HashSet();
	
	public LanguageForm() {
		super();
	}

	public LanguageForm(Set<Language> items) {
		super();
		this.items = items;
	}

	public Set<Language> getItems() {
		return items;
	}

	public void setItems(Set<Language> items) {
		this.items = items;
	}
}