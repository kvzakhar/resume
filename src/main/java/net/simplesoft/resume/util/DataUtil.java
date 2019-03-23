package net.simplesoft.resume.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.*;

import net.simplesoft.resume.form.SignUpForm;

public class DataUtil {

	public static <T extends Annotation> int copyFields(final Object from, final Object to, Class<T> annotation) {
		final CopiedFieldsCounter copiedFieldsCounter = new CopiedFieldsCounter();
		ReflectionUtils.doWithFields(to.getClass(), new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				copyAccessibleField(field, from, to, copiedFieldsCounter);
			}
		}, createFieldFilter(annotation));
		return copiedFieldsCounter.counter;
	}
	
	private static final class CopiedFieldsCounter {
		private int counter;
	}
	
	private static void copyAccessibleField(Field field, Object from, Object to, CopiedFieldsCounter copiedFieldsCounter) throws IllegalAccessException {
		Object fromValue = field.get(from);
		Object toValue = field.get(to);
		if (fromValue == null) {
			if (toValue != null) {
				field.set(to, null);
				copiedFieldsCounter.counter++;
			}
		} else {
			if (!fromValue.equals(toValue)) {
				field.set(to, fromValue);
				copiedFieldsCounter.counter++;
			}
		}
	}	

	private static <T extends Annotation> FieldFilter createFieldFilter(Class<T> annotation) {
		if (annotation == null) {
			return ReflectionUtils.COPYABLE_FIELDS;
		} else {
			return new org.springframework.data.util.ReflectionUtils.AnnotationFieldFilter(annotation);
		}
	}
	
	public static String normalizeName(String name) {
		return name.trim().toLowerCase();
	}

	public static String capitalizeName(String name) {
		return WordUtils.capitalize(normalizeName(name));
	}

	public static String generateProfileUid(SignUpForm profile) {
		return normalizeName(profile.getFirstName()) + UID_DELIMETER + normalizeName(profile.getLastName());
	}

	public static String regenerateUidWithRandomSuffix(String baseUid, String alphabet, int letterCount) {
		return baseUid + UID_DELIMETER + generateRandomSuffix(alphabet, letterCount);
	}

	public static String generateRandomSuffix(String alphabet, int letterCount) {
		Random r = new Random();
		StringBuilder uid = new StringBuilder();
		for (int i = 0; i < letterCount; i++) {
			uid.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return uid.toString();
	}
	
	public static String generateRandomString(String alphabet, int letterCount) {
		Random r = new Random();
		StringBuilder uid = new StringBuilder();
		for (int i = 0; i < letterCount; i++) {
			uid.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return uid.toString();
	}

	private static final String UID_DELIMETER = "-";	

	public static Object readProperty(Object obj, String propertyName) {
		try {
			return BeanUtils.getPropertyDescriptor(obj.getClass(), propertyName).getReadMethod().invoke(obj);
		} catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
			throw new IllegalArgumentException("Can't read property: '"+propertyName+"' from object:'"+obj.getClass()+"': "+e.getMessage(), e);
		}
	}
}
