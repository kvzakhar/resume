package net.simplesoft.resume.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.simplesoft.resume.entity.Hobby;
import net.simplesoft.resume.model.LanguageLevel;
import net.simplesoft.resume.model.LanguageType;

public interface StaticDataService {

	 Set<Hobby> findAllHobbies();
	
	List<Hobby> createHobbyEntitiesByNames(List<String> names);
	
	Map<Integer, String> findMonthMap();
	
	List<Integer> findPracticsYears();
	
	List<Integer> findCourcesYears();
	
	List<Integer> findEducationYears();
	
	Collection<LanguageType> findAllLanguageTypes(); 
	
	Collection<LanguageLevel> findAllLanguageLevels();
}