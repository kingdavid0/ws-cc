package com.tcs.ziprange;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for each expected input value and its appropriate responses.
 * 
 * @author David King
 *
 */
public class TestCondenseZipRange {

	/**
	 * Tests for all expected values without exceptions
	 */
	@Test
	public void testCondense() {
		final ZipRangeCondenser condenser = new ZipRangeCondenser();

		// Inputs
		final String nullString = null;
		final String emptyString = "";
		final String singleRange = "[12345,65432]";
		final String multipleRanges = "[23456,54321] [54323,98765] ";
		final String multipleOverlap = "[23456,54321] [54320,98765] ";
		final String multipleOverlapReversed = "[54320,98765] [23456,54321]";
		final String mixedRanges = "[54320,98765] [23456,54321] [10101,10102] ";
		final String singleNumber = "[10101,10101] [23456,54321] [54320,98765] ";
		final String singleNumberOverlap = "[23456,54321] [54320,98765] [30101,30101] ";
		final String badRange = "[45613,45612] ";
		final String badAndGoodRange = "[45613,45612] [12345,65432] ";
		
		// Invalid Inputs
		final String invalidInput0 = "dasjhfkjsadf23425";
		final String invalidInput1 = "12345,12345";
		final String invalidInput2 = "[1234,12345]";
		final String invalidInput3 = "[12345,123456]";
		final String invalidInput4 = "[1234a,12345]";

		// Expected Outputs
		final String nullExpected = emptyString;
		final String emptyExpected = emptyString;
		final String singleExpected = "[12345,65432] ";
		final String multipleExpected = multipleRanges;
		final String multipleOverlapExpected = "[23456,98765] ";
		final String multipleOverlapReversedExpected = multipleOverlapExpected;
		final String mixedExpected = "[23456,98765] [10101,10102] ";
		final String singleNumberExpected = "[10101,10101] [23456,98765] ";
		final String singleOverlapExpected = multipleOverlapExpected;
		final String badExpected = emptyString;
		final String badAndGoodExpected = "[12345,65432] ";
		
		// Expect Output for invalid inputs
		final String invalidExpected = ZipRangeCondenser.INCORRECTFORMAT;

		// Asserts and Tests for valid inputs
		assertEquals(nullExpected, condenser.condense(nullString));
		assertEquals(emptyExpected, condenser.condense(emptyString));
		assertEquals(singleExpected, condenser.condense(singleRange));
		assertEquals(multipleExpected, condenser.condense(multipleRanges));
		assertEquals(multipleOverlapExpected, condenser.condense(multipleOverlap));
		assertEquals(multipleOverlapReversedExpected, condenser.condense(multipleOverlapReversed));
		assertEquals(mixedExpected, condenser.condense(mixedRanges));
		assertEquals(singleNumberExpected, condenser.condense(singleNumber));
		assertEquals(singleOverlapExpected, condenser.condense(singleNumberOverlap));
		assertEquals(badExpected, condenser.condense(badRange));
		assertEquals(badAndGoodExpected, condenser.condense(badAndGoodRange));
		
		// Asserts and Test for invalid inputs
		assertEquals(invalidExpected, condenser.condense(invalidInput0));
		assertEquals(invalidExpected, condenser.condense(invalidInput1));
		assertEquals(invalidExpected, condenser.condense(invalidInput2));
		assertEquals(invalidExpected, condenser.condense(invalidInput3));
		assertEquals(invalidExpected, condenser.condense(invalidInput4));

	}

}
