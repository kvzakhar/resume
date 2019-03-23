package net.simplesoft.resume.annotation.constraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { })
@Size(min = 8, message="Пароль должен содержать минимум 8 символов")
@MinDigitCount
@MinUpperCharCount
@MinLowerCharCount
@MinSpecCharCount
public @interface PasswordStrength {
	
	String message() default "PasswordStrength";
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?>[] groups() default { };

}
