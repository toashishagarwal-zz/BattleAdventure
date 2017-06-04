package com.ba;

import java.util.Random;

public class RandomGenerator {
	public static int getRandomNumber(final int max) {
		Random random = new Random();
		return random.nextInt(max);
	}
}
