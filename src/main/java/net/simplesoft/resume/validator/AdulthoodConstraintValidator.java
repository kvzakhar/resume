package net.simplesoft.resume.validator;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.simplesoft.resume.annotation.constraints.Adulthood;

public class AdulthoodConstraintValidator implements ConstraintValidator<Adulthood, Date>{

	private int age;
	
	@Override
	public void initialize(Adulthood constraintAnnotation) {
		this.age = constraintAnnotation.adulthoodAge();
		
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		} else {			
			LocalDate critical = LocalDate.now().minusYears(age);
			return value.before( Date.from(critical.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		
	}

}
