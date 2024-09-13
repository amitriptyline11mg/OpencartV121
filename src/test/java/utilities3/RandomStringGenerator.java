package utilities3;

import java.util.Random;

public class RandomStringGenerator {

	public static String randomAlphabeticGen(int length) {

		String myCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		// importing Random class
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
//			sb.append(myCharacters.charAt(23));

			// Now the below line will choose random number, & then we will pich the alphap
			// at that possition
			sb.append(myCharacters.charAt(random.nextInt(52)));
		}

		return sb.toString(); // Convert the StringBuilder into String
	}

	public static String randomNumberGen(int length) {

		String myDigits = "0123456789";
		// importing Random class
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
//			sb.append(myCharacters.charAt(23));

			// Now the below line will choose random number, & then we will pich the alphap
			// at that possition
			sb.append(myDigits.charAt(random.nextInt(myDigits.length())));
		}

		return sb.toString(); // Convert the StringBuilder into String
	}

}
