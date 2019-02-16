package net.simplesoft.resume.service;

public class NameService {

	public static NameService getInstance() {
		return new NameService();
	}

	public String convertName(String name) {
		return name.toUpperCase();
	}
	
}
