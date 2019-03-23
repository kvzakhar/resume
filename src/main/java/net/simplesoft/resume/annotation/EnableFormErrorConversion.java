package net.simplesoft.resume.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableFormErrorConversion {

	String formName();
	
	String fieldReference();
	
	Class<? extends Annotation> validationAnnotationClass();
	
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		EnableFormErrorConversion[] value();
	}
}