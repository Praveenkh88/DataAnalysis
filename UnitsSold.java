package com.manthan;

public class UnitsSold {

	static long nullCount;
	static long zerocount;
	static double mean;
	public static long getNullCount() {
		return nullCount;
	}
	public static void setNullCount(long nullCount) {
		UnitsSold.nullCount = nullCount;
	}
	public static long getZerocount() {
		return zerocount;
	}
	public static void setZerocount(long zerocount) {
		UnitsSold.zerocount = zerocount;
	}
	public static double getMean() {
		return mean;
	}
	public static void setMean(double mean) {
		UnitsSold.mean = mean;
	}
	public static double getMedian() {
		return median;
	}
	public static void setMedian(double median) {
		UnitsSold.median = median;
	}
	public static double getSd() {
		return sd;
	}
	public static void setSd(double sd) {
		UnitsSold.sd = sd;
	}
	static double median;
	static double sd;
}
