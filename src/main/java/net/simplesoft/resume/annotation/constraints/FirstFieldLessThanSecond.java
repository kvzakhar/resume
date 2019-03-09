package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.simplesoft.resume.validator.FirstFieldLessThanSecondConstraintValidator;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FirstFieldLessThanSecondConstraintValidator.class)
@Documented
public @interface FirstFieldLessThanSecond {
	String message() default "FirstFieldLessThanSecond";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String first();

	String second();

	/**
	 * Defines several <code>@FirstFieldLessThanSecond</code> annotations on the same element
	 *
	 * @see FirstFieldLessThanSecond
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		FirstFieldLessThanSecond[] value();
	}
}