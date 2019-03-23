package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Certificate;

public class CertificateForm {
	@Valid
	private Set<Certificate> items = new HashSet();
	
	public CertificateForm() {
		super();
	}

	public CertificateForm(Set<Certificate> items) {
		super();
		this.items = items;
	}

	public Set<Certificate> getItems() {
		return items;
	}

	public void setItems(Set<Certificate> items) {
		this.items = items;
	}
}