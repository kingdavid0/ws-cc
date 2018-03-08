package com.tcs.ziprange;

/**
 * Main class for running Zip Range Condenser. Takes arguments as a list of zip
 * code ranges. Example: [12345,67890] Each new argument is considered a new zip
 * code range.
 * 
 * @author David King
 *
 */
public class MainZipRangeCondenser {

	/**
	 * Takes the zip code ranges and prints out a list of condensed correct zip code
	 * ranges.
	 * 
	 * @param args
	 *            a list of zip code ranges
	 */
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		final String space = " ";
		for (String arg : args) {
			builder.append(arg).append(space);
		}
		final String listRanges = builder.toString();
		ZipRangeCondenser condenser = new ZipRangeCondenser();
		final String output = condenser.condense(listRanges);
		System.out.println(output);
	}

}
