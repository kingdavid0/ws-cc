package com.tcs.ziprange;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestZipRangeComparator {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Runs each expected case along with a Number Format Exception
	 *  and a case provided to trigger a ZipRangeComparator.ERROR
	 */
	@Test
	public void testCompare() {
		// Case A
		final Range inA = null;
		final Range outA = null;
		assertEquals(ComparatorCode.INSERT, ZipRangeComparator.compare(inA, outA));
		
		// Case B
		final Range inB = new Range();
		final Range outB = new Range();
		inB.setLowerBound("3");
		inB.setUpperBound("4");
		outB.setLowerBound("1");
		outB.setUpperBound("2");
		assertEquals(ComparatorCode.ITERATE, ZipRangeComparator.compare(inB, outB));
		
		// Case C
		final Range inC = new Range();
		final Range outC = new Range();
		inC.setLowerBound("1");
		inC.setUpperBound("2");
		outC.setLowerBound("3");
		outC.setUpperBound("4");
		assertEquals(ComparatorCode.ITERATE, ZipRangeComparator.compare(inC, outC));
		
		// Case D
		final Range inD = new Range();
		final Range outD = new Range();
		inD.setLowerBound("2");
		inD.setUpperBound("3");
		outD.setLowerBound("1");
		outD.setUpperBound("4");
		assertEquals(ComparatorCode.CONTINUE, ZipRangeComparator.compare(inD, outD));
		
		// Case E
		final Range inE = new Range();
		final Range outE = new Range();
		inE.setLowerBound("1");
		inE.setUpperBound("4");
		outE.setLowerBound("2");
		outE.setUpperBound("3");
		assertEquals(ComparatorCode.REPLACEOUTIN, ZipRangeComparator.compare(inE, outE));
		
		// Case F
		final Range inF = new Range();
		final Range outF = new Range();
		inF.setLowerBound("1");
		inF.setUpperBound("3");
		outF.setLowerBound("2");
		outF.setUpperBound("4");
		assertEquals(ComparatorCode.REPLACEILOU, ZipRangeComparator.compare(inF, outF));
		
		// Case G
		final Range inG = new Range();
		final Range outG = new Range();
		inG.setLowerBound("2");
		inG.setUpperBound("4");
		outG.setLowerBound("1");
		outG.setUpperBound("3");
		assertEquals(ComparatorCode.REPLACEOLIU, ZipRangeComparator.compare(inG, outG));
		
		// Case H
		final Range inH = null;
		final Range outH = new Range();
		assertEquals(ComparatorCode.END, ZipRangeComparator.compare(inH, outH));
		
		// ZipRangeComparator.ERROR
		final Range inError = new Range();
		final Range outError = new Range();
		inError.setLowerBound("9");
		inError.setUpperBound("0");
		outError.setLowerBound("0");
		outError.setUpperBound("9");
		assertEquals(ComparatorCode.ERROR, ZipRangeComparator.compare(inError, outError));
		
		// Expected invalid number
		thrown.expect(NumberFormatException.class);
		final Range inException = new Range();
		final Range outException = new Range();
		ZipRangeComparator.compare(inException, outException);
	}

}
