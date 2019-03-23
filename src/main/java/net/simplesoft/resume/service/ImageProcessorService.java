package net.simplesoft.resume.service;

import org.springframework.web.multipart.MultipartFile;

import net.simplesoft.resume.model.UploadCertificateResult;
import net.simplesoft.resume.model.UploadResult;

public interface ImageProcessorService {

	UploadResult processNewProfilePhoto(MultipartFile uploadPhoto);

	UploadCertificateResult processNewCertificateImage(MultipartFile uploadCertificateImage);
}