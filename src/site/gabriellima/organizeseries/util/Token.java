package site.gabriellima.organizeseries.util;

import site.gabriellima.organizeseries.entities.User;

public class Token {

	public static String generateToken(User user) {
		String token = "ke";

		if (user.getName().length() % 2 == 0) {
			int len = user.getName().length() / 2;
			token += user.getName().substring(0, len);
			token += "@" + len;
		} else {
			int len = (user.getName().length() + 1) / 2;
			token += user.getName().substring(0, len);
			token += len + "@";
		}

		token += "y1";
		return token;
	}

	public static boolean verifyToken(String token, User user) {
		String tokenVerify = generateToken(user);
		if (tokenVerify.equals(token)) {
			return true;
		}

		return false;
	}
}
