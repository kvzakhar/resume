package net.simplesoft.resume.model;

public enum LanguageType {
	ALL,

	SPOKEN,

	WRITING;
	
	public String getDbValue() {
		return name().toLowerCase();
	}

	public LanguageType getReverseType() {
		if (this == SPOKEN) {
			return WRITING;
		} else if (this == WRITING) {
			return SPOKEN;
		} else {
			throw new IllegalArgumentException(this+" does not have reverse type");
		}
	}

}
