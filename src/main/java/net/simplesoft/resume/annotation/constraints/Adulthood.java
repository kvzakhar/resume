package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.simplesoft.resume.validator.AdulthoodConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AdulthoodConstraintValidator.class})
public @interface Adulthood {

	String message() default "Adulthood";
	
	int adulthoodAge() default 18;
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?>[] groups() default { };
}