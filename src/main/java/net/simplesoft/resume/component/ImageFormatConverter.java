package net.simplesoft.resume.component;

import java.io.IOException;
import java.nio.file.Path;

import javax.validation.constraints.NotNull;

public interface ImageFormatConverter {

	void convert(@NotNull Path sourceImageFile, @NotNull Path destImageFile) throws IOException;
}