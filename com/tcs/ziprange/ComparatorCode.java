package com.tcs.ziprange;

/**
 * ENUM used by the Zip Code Range comparator function to determine what the
 * correct course of action to take with the two given ranges.
 * 
 * @author David King
 *
 */
public enum ComparatorCode {
	INSERT, // Insert the given Input Range into the Output List
	ITERATE, // Do nothing with the given Input Range and iterate the Output List
	CONTINUE, // Do nothing with the given Input Range and iterate the Input List
	REPLACEOUTIN, // Replace the Output Range with the Input Range
	REPLACEILOU, // Replace the Output Range with a new Range using the Input Lower Bound and
					// Output Upper Bound
	REPLACEOLIU, // Replace the Output Range with a new Range using the Output Lower Bound and
					// Input Upper Bound
	END, // End iterating the Input List (this will normally occur naturally without the
			// use of the comparator function)
	ERROR // Occurs only if an unexpected value or event takes place, the assumption that
			// any range has an upper or lower bound set as an non integer
}
