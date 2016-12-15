package math.arithmetic.elementray;

import static math.arithmetic.elementray.Utility.divisors;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * <pre>
 * --------------------------------------------------------
 * Copyright (c) 2016 by Nokia. All rights reserved.
 * --------------------------------------------------------
 * FILE :         UtilityTest.java
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
public class UtilityTest {

	@Test
	public void testDivisors() {
		int[] actualRet = divisors(7, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[0]));

		actualRet = divisors(2, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[0]));

		actualRet = divisors(4, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[] { 2 }));

		actualRet = divisors(9, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[] { 3 }));

		actualRet = divisors(36, 10, 4);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[] { 6 }));

		actualRet = divisors(30, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[] { 5, 6 }));
		
		actualRet = divisors(59, 10, 0);
		TestCase.assertTrue(Arrays.toString(actualRet),
				Arrays.equals(actualRet, new int[0]));
	}

}
