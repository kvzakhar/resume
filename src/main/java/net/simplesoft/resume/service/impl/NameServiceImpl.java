package net.simplesoft.resume.service.impl;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import net.simplesoft.resume.service.NameService;

@Service
public class NameServiceImpl implements NameService{

	@Override
	public String convertName(String name) {
		if(name != null && name.contains("-")) {
			String[] parts  = name.split("-");
			return WordUtils.capitalize(parts[0] + " " + parts[1]);
		}
		return WordUtils.capitalize(name);
	}
}