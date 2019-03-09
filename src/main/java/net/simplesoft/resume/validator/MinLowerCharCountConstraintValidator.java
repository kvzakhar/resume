package net.simplesoft.resume.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.simplesoft.resume.annotation.constraints.MinLowerCharCount;

public class MinLowerCharCountConstraintValidator implements ConstraintValidator<MinLowerCharCount, CharSequence>{

	private int minValue;
	@Override
	public void initialize(MinLowerCharCount constraintAnnotation) {
		minValue = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		int count = 0;
		for(int i=0;i<value.length();i++){
			if(Character.isLowerCase(value.charAt(i))) {
				count++;
			}
		}
		return count >= minValue;
	}

}
