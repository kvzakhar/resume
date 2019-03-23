package net.simplesoft.resume.component;

import java.nio.file.Path;

import javax.validation.constraints.NotNull;

public interface ImageOptimizator {
	
	void optimize (@NotNull Path image);

}
