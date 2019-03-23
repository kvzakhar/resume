package net.simplesoft.resume.component;

import java.io.IOException;
import java.nio.file.Path;

import javax.validation.constraints.NotNull;

public interface ImageResizer {

	void resize(@NotNull Path sourceImageFile, @NotNull Path destImageFile, int width, int height) throws IOException;
}