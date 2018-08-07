package Utils;

import java.util.Properties;
import java.util.Random;
import com.acres.datasource.LoadProperty;
import com.acres.dbsource.*;


public class GenerateAuthKey {
	private static char[] buf;
	protected static Properties CONFIG = LoadProperty.getPropertiesFile("config");
	private final static Random random = new Random();
	

	public static String generateAPIAuthKey() {
		String EncryptedAuthKey = "";

		try {
			String authKeyQuery = CONFIG.getProperty("AuthKeyQuery");
			String userNameDB = CONFIG.getProperty("userNameTestDB");
			String pwdTestDB = CONFIG.getProperty("passwordTestDB");
			String portNo = CONFIG.getProperty("portno");
			String portTestDB = CONFIG.getProperty("portTestDB");
			String Akey = ConnectionDB.getDBData(authKeyQuery, "authkey", userNameDB, pwdTestDB, portNo, portTestDB);

			char[] randm = randomString(10);
			MCrypt mcrypt = new MCrypt();

			String fstr = nextString(randm);
			String rstr = reverseStr(fstr);

			String newAuthKey = fstr + "|" + Akey + "|" + rstr;

			try {
				EncryptedAuthKey = MCrypt.bytesToHex(mcrypt.encrypt(newAuthKey));
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		} catch (Exception e) {
		
		}

		System.out.println("EncryptedAuthKey : " + EncryptedAuthKey);
		return EncryptedAuthKey;
	}

	public static char[] randomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		buf = new char[length];
		return buf;
	}

	private static String nextString(char[] a) {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

	private static String reverseStr(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; --i)
			sb.append(s.charAt(i));
		return sb.toString();
	}

	private static final char[] symbols = new char[36];
	static {
		for (int idx = 0; idx < 10; ++idx)
			symbols[idx] = (char) ('0' + idx);
		for (int idx = 10; idx < 36; ++idx)
			symbols[idx] = (char) ('a' + idx - 10);
	}
}
