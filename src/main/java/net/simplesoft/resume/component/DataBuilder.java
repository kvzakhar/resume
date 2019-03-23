package net.simplesoft.resume.component;

public interface DataBuilder {

	String buildProfileUid(String firstName, String lastName);
	
	String buildRestoreAccessLink(String appHost, String token);
	
	String rebuildUidWithRandomSuffix(String baseUid, String alphabet, int letterCount);
	
	String buildCertificateName(String fileName);
}
