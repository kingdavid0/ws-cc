package com.tcs.ziprange;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Range POJO.
 * 
 * @author David King
 *
 */
public class TestRange {

	/**
	 * Test for the lower bound variable.
	 */
	@Test
	public void testSetAndGetLowerBound() {
		final Range r1 = new Range();
		final String lowerBound0 = "one";
		final String lowerBound1 = "zero";

		// Value can be set
		r1.setLowerBound(lowerBound0);

		assertEquals(lowerBound0, r1.getLowerBound());

		// Value can change
		r1.setLowerBound(lowerBound1);

		assertEquals(lowerBound1, r1.getLowerBound());

		// Null value accepted
		r1.setLowerBound(null);

		assertNull(r1.getLowerBound());

	}

	/**
	 * Test for the upper bound variable.
	 */
	@Test
	public void testSetAndGetUpperbound() {
		final Range r1 = new Range();
		final String uppperBound0 = "one";
		final String uppperBound1 = "zero";

		// Value can be set
		r1.setLowerBound(uppperBound0);

		assertEquals(uppperBound0, r1.getLowerBound());

		// Value can change
		r1.setLowerBound(uppperBound1);

		assertEquals(uppperBound1, r1.getLowerBound());

		// Null value accepted
		r1.setLowerBound(null);

		assertNull(r1.getLowerBound());
	}

}
