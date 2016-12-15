package math.arithmetic.elementray;

import java.util.Arrays;
import java.util.Random;

/**
 * <pre>
 * --------------------------------------------------------
 * Copyright (c) 2016 by Nokia. All rights reserved.
 * --------------------------------------------------------
 * FILE :         Utility.java
 * --------------------------------------------------------
 * DESCRIPTION: 
 * CREATION DATE: Sep 20, 2016
 * AUTHOR:        shanh
 * PROJECT:       DaughtersArithmatic
 * --------------------------------------------------------
 * HISTORY: 
 * --------------------------------------------------------
 * </pre>
 */
public class Utility {
	public static final Random random = new Random(System.currentTimeMillis());

	public static final int random(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min: " + String.valueOf(min)
					+ " max: " + String.valueOf(max));
		}
		return min + random.nextInt(max - min);
	}

	/**
	 * Get all divisors of a integer
	 * 
	 * @param num
	 *            integer need to search all divisors
	 * @param maxDivisor
	 *            max divisor (exclude) in returned values
	 * @param forbiddenNumber
	 *            this number and num/forbiddenNumber should not in returned
	 *            value
	 * @return all legal divisors
	 */
	public static final int[] divisors(int num, int maxDivisor,
			int forbiddenNumber) {
		int sqrtNum = (int) Math.sqrt(num);
		int[] tmp = new int[sqrtNum * 2];
		int index = 0;
		int maxIterator = Math.min(maxDivisor, sqrtNum);
		for (int i = 2; i <= maxIterator; i++) {
			int quotient = num / i;
			if (i != forbiddenNumber && quotient != forbiddenNumber) {
				if (quotient < maxDivisor && quotient * i == num) {
					tmp[index] = i;
					index++;
					if (i != quotient) {
						tmp[index] = quotient;
						index++;
					}
				}
			}
		}
		return Arrays.copyOf(tmp, index);
	}
}
