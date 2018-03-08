package com.tcs.ziprange;

/**
 * A POJO representing a lower bound and an upper bound for a given range.
 * 
 * @author David King
 *
 */
public class Range {

	/**
	 * Represents the lower bound of the range.
	 */
	private String lowerBound;
	/**
	 * Represetns the upper bound of the range.
	 */
	private String upperBound;

	/**
	 * @return the lowerBound
	 */
	public String getLowerBound() {
		return this.lowerBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public String getUpperBound() {
		return this.upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

}
