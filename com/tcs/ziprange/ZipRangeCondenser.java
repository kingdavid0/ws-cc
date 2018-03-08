package com.tcs.ziprange;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * The main algorithm for condensing sets of Zip Code Range Sets.
 * The main method is the condense method which takes as input a String 
 * representing zero or more Zip Code Range Sets to be condensed into as few
 * as possible Zip Code Range Sets.  Trailing spaces are allowed if there is
 * at least one Zip Code Range present, but not required.
 * A single Zip Code Range set is seen as:
 * 	[12345,12345]
 * It is expected that multiple sets will be separated by a single space,
 * the lower bound will be the first number in a set, the upper bound will be the second number
 * in a set, there will be no whitespace within a set, only digits 0-9 will be expected
 * as a bound, a set will be bound by an open bracket and a close bracket, and a comma
 * will separate the lower and upper bound.
 * 
 * @author David King
 *
 */
public class ZipRangeCondenser {
	
	/**
	 *  The expected String to return if invalid inputs are given.
	 */
	public static final String INCORRECTFORMAT = "Incorrect Format of Zip Code Ranges";
	
	/**
	 * Expected format of input String of Zip Code Ranges in Java Pattern Regex format.
	 * Please see {@link com.tcs.ziprange.ZipRangeCondenser#correctFormat(String)}
	 *  for cases of a missing trailing space
	 */
	private static final String FORMAT = "(\\[(\\d{5},\\d{5})\\] )*";
	private static final Pattern PATTERN = Pattern.compile(FORMAT);
	private static final String SPACE = " ";

	/**
	 * Condenses all input zip code ranges in the given String to the least amount
	 * of ranges and filters out unexpected ranges.
	 * 
	 * @param inputRanges
	 *            a list of Zip Code Ranges in the form of a String
	 * @return a list of condensed Zip Code Ranges in the form of a String
	 */
	public String condense(String inputRanges) {
		String output = "";
		if (this.correctFormat(inputRanges)) {
			List<Range> inputList = this.createRanges(inputRanges);
			List<Range> outputList = new ArrayList<Range>();
			ComparatorCode code = null;
			if (!inputList.isEmpty()) {
				for(Range inputRange : inputList) {
					code = null;
					if(outputList.isEmpty()) {
						code = ZipRangeComparator.compare(inputRange, null);
						switch (code) {
						case INSERT:
							outputList.add(inputRange);
							break;
						case ERROR:
							// Do nothing, placeholder if additional information is needed
							// This case generally means the format was correct,
							// but the range itself was poorly constructed or unexpected
							break;
						default:
							// Additional unexpected error if this point is reached
							break;
						}
					} else {
						for(Range outputRange: outputList) {
							code = ZipRangeComparator.compare(inputRange, outputRange);
							boolean breakFromLoop = false;
							Range newRange;
							switch (code) {
							case CONTINUE:
								breakFromLoop = true;
								break;
							case ITERATE:
								// Nothing to do but continue the list
								break;
							case REPLACEILOU:
								newRange = new Range();
								newRange.setLowerBound(inputRange.getLowerBound());
								newRange.setUpperBound(outputRange.getUpperBound());
								outputList.remove(outputRange);
								outputList.add(newRange);
								breakFromLoop = true;
								break;
							case REPLACEOLIU:
								newRange = new Range();
								newRange.setLowerBound(outputRange.getLowerBound());
								newRange.setUpperBound(inputRange.getUpperBound());
								outputList.remove(outputRange);
								outputList.add(newRange);
								breakFromLoop = true;
								break;
							case REPLACEOUTIN:
								outputList.remove(outputRange);
								outputList.add(inputRange);
								breakFromLoop = true;
								break;
							case ERROR:
								// Do nothing, placeholder for unexpected values
								break;
							default:
								// Additional unexpected error if this point is reached
								break;
							}
							if(breakFromLoop) {
								break;
							}
						}
						if (code == ComparatorCode.ITERATE) {
							outputList.add(inputRange);
						}
					}
				}
			}
			
			StringBuilder outputBuilder = new StringBuilder();
			for (Range outputRange : outputList ) {
				outputBuilder.append("[").append(outputRange.getLowerBound()).append(",").append(outputRange.getUpperBound()).append("] ");
			}
			output = outputBuilder.toString();
		} else {
			output = INCORRECTFORMAT;
		}
		return output;
	}

	/**
	 * Confirms that the string fits the expected format for a list of zip code
	 * ranges.
	 * 
	 * @param inputRanges
	 *            a list of Zip Code Ranges in the form of a String
	 * @return true IFF the string fits the expected format or String is null
	 */
	private boolean correctFormat(String inputRanges) {
		// A simple correction if there is no space at the end to leave the regex less complicated
		if (inputRanges != null
				&& !inputRanges.isEmpty()
				&& !inputRanges.substring(inputRanges.length() - 2).contains(SPACE)) {
			inputRanges = inputRanges + SPACE;
		}
		return inputRanges == null || PATTERN.matcher(inputRanges).matches();
	}

	/**
	 * Converts a string representing a list of zip code ranges to a list of Range
	 * Objects, dropping bad ranges.
	 * 
	 * @param inputRanges
	 *            a list of Zip Code Ranges in the form of a String
	 * @return a List of Range Objects based on inputRanges
	 */
	private List<Range> createRanges(String inputRanges) {
		List<Range> output = new ArrayList<Range>();
		
		if(inputRanges != null) {
			StringTokenizer tokenizer = new StringTokenizer(inputRanges, SPACE);
			while(tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				Range input = this.stringToRange(token);
				if (input != null) {
					output.add(input);
				}
			}
		}
		return output;
	}
	
	/**
	 * Converts a String representing a Range to a Range Object
	 * @param range a Zip Code Range represented as a String
	 * @return a Range Object
	 */
	private Range stringToRange(String range) {
		Range output = null;
		if (range != null && !range.isEmpty()) {
			output = new Range();
			output.setLowerBound(range.substring(1, 6));
			output.setUpperBound(range.substring(7,12));
		}
		return output;
	}

}
