package net.simplesoft.resume.form;

import net.simplesoft.resume.entity.Profile;

public class InfoForm {

	private String info;

	public InfoForm() {
		super();
	}

	public InfoForm(String info) {
		super();
		this.info = info;
	}
	
	public InfoForm(Profile profile) {
		super();
		this.info = profile.getInfo();
	}

	public String getInfo() {
		return info != null ? info.trim() : null;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}