package in.ashokit.utils;

import java.security.SecureRandom;

public class PasswordGenerator {

	private PasswordGenerator() {

	}

	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

	private static final SecureRandom random = new SecureRandom();
	private static final char[] dic = (ALPHA_CAPS + ALPHA + NUMERIC + SPECIAL_CHARS).toCharArray();

	public static String generatePassword(int len) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			sb.append(dic[random.nextInt(dic.length)]);
		}
		return sb.toString();
	}
}
