package net.simplesoft.resume.component;

import java.lang.annotation.Annotation;

import javax.validation.constraints.NotNull;

import org.springframework.validation.BindingResult;

public interface FormErrorConverter {

	void convertToFieldError(@NotNull Class<? extends Annotation> validationAnnotationClass, @NotNull Object formInstance, 
			@NotNull BindingResult bindingResult);
}
