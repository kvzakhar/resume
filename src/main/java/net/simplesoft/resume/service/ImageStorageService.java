package net.simplesoft.resume.service;

import java.nio.file.Path;

import javax.validation.constraints.NotNull;

import net.simplesoft.resume.Constants.UIImageType;

public interface ImageStorageService {
	
	@NotNull String createImageLink (@NotNull String imageName, @NotNull UIImageType imageType);
	
	void save(@NotNull String imageLink, @NotNull Path tempImageFile);

	void remove (String ... imageLinks);
}