package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.simplesoft.resume.validator.MinSpecCharCountConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { MinSpecCharCountConstraintValidator.class })
public @interface MinSpecCharCount {

	int value() default 1;

	String specSymbols() default "!@~`#$%^&*()_-+=|\\/{}[].,;:/?";

	String message() default "Пароль должен содержать специальные символы буквы";

	Class<? extends Payload>[] payload() default {};

	Class<?>[] groups() default {};
}