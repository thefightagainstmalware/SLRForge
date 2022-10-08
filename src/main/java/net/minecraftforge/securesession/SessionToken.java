package net.minecraftforge.securesession;

public class SessionToken {
	private static String token;
	public static void setToken(String token) {
		SessionToken.token = token;
	}
	
	public static String getToken() {
		return token;
	}
}
