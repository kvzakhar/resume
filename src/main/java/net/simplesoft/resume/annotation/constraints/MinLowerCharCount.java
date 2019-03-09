package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.simplesoft.resume.validator.MinLowerCharCountConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={MinLowerCharCountConstraintValidator.class})
public @interface MinLowerCharCount {

	int value() default 1;
	
	String message() default "MinLowerCharCount";
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?>[] groups() default { };
}