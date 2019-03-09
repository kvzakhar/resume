package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.simplesoft.resume.validator.PhoneConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneConstraintValidator.class})
public @interface Phone {
	
	String message() default "Phone";
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?>[] groups() default { };

}
