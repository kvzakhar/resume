package net.simplesoft.resume;

public class Constants {

	public static final int MAX_PROFILES_PER_PAGE = 10;

	public static final String USER = "USER";
	
	public static final String[] EMPTY_ARRAY = {};
	
	public static enum UIImageType {

		AVATARS(110, 110, 400, 400),

		CERTIFICATES(142, 100, 900, 400);
		
		private final int smallWidth;
		private final int smallHeight;
		private final int largeWidth;
		private final int largeHeight;
		
		private UIImageType(int smallWidth, int smallHeight, int largeWidth, int largeHeight) {
			this.smallWidth = smallWidth;
			this.smallHeight = smallHeight;
			this.largeWidth = largeWidth;
			this.largeHeight = largeHeight;
		}
		public String getFolderName() {
			return name().toLowerCase();
		}
		public int getSmallWidth() {
			return smallWidth;
		}
		public int getSmallHeight() {
			return smallHeight;
		}
		public int getLargeWidth() {
			return largeWidth;
		}
		public int getLargeHeight() {
			return largeHeight;
		}
	}
}
