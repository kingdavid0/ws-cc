package com.tcs.ziprange;

/**
 * A simple comparator to determine what action to take when given two Zip Code Ranges.
 * @author David King
 *
 */
public class ZipRangeComparator {

	/**
	 * The following method uses the cases below to determine output.
		A: If there is no Output Range, insert Input Range as new Range for Output List.
		B: If numIL is greater than numOU , iterate Output List.
		C: If numIU is less than numOL, iterate Output List.
		D: If numIL is greater than numOL AND numIU is less than numOU, do nothing and continue.
		E: If numOL is greater than numIL AND numOU is less than numIU, replace Output Range with Input Range and continue.
		F: If numIL is less than or equal to numOL AND numIU is less than or equal to numOU, replace Output Range with new Range of [numIL,numOU] and continue.
		G: If numIL is greater than or equal to numOL and numIU is greater than or equal to numOU, replace Output Range with new Range of [numOL,numIU] and continue.
		H: If there is no Input Range, end loop.
	 * @param inputRange a Zip Code Range
	 * @param outputRange a Zip Code Range
	 * @return the action to take based on the given Zip Code Ranges
	 * @throws NumberFormatException
	 * 		throws when one or more upper or lower bound strings within the given ranges is not an integer
	 */
	public static ComparatorCode compare(Range inputRange, Range outputRange) throws NumberFormatException {
		ComparatorCode output = ComparatorCode.ERROR;
		if (inputRange == null) {
			// Case H
			output = ComparatorCode.END;
		} else if (outputRange == null) {
			// Confirm that inputRange does not have reverse bounds
			int numIL = Integer.parseInt(inputRange.getLowerBound());
			int numIU = Integer.parseInt(inputRange.getUpperBound());
			if (numIL <= numIU) {
				// Case A
				output = ComparatorCode.INSERT;
			}
		} else {
			int numIL = Integer.parseInt(inputRange.getLowerBound());
			int numIU = Integer.parseInt(inputRange.getUpperBound());
			int numOL = Integer.parseInt(outputRange.getLowerBound());
			int numOU = Integer.parseInt(outputRange.getUpperBound());
			if (numIL > numIU || numOL > numOU) {
				// Reversed bounds, not accepted
				output = ComparatorCode.ERROR;
			} else if (numIL > numOU || numIU < numOL) {
				// Case B and C
				output = ComparatorCode.ITERATE;
			} else if (numIL > numOL && numIU < numOU) {
				// Case D
				output = ComparatorCode.CONTINUE;
			} else if (numOL > numIL && numOU < numIU) {
				// Case E
				output = ComparatorCode.REPLACEOUTIN;
			} else if (numIL <= numOL && numIU <= numOU) {
				// Case F
				output = ComparatorCode.REPLACEILOU;
			} else if (numIL >= numOL && numIU >= numOU) {
				// Case G
				output = ComparatorCode.REPLACEOLIU;
			}
		}
		return output;
	}
}
