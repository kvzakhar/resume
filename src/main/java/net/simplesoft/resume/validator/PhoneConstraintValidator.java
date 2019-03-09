package net.simplesoft.resume.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.simplesoft.resume.annotation.constraints.Phone;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^\\+?[0-9]+");
	}	

}
