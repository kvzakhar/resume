package net.simplesoft.resume.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import net.simplesoft.resume.entity.Course;

public class CourseForm {
	@Valid
	private Set<Course> items = new HashSet();
	
	public CourseForm() {
		super();
	}

	public CourseForm(Set<Course> items) {
		super();
		this.items = items;
	}

	public Set<Course> getItems() {
		return items;
	}

	public void setItems(Set<Course> items) {
		this.items = items;
	}
}