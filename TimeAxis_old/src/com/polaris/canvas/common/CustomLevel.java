package com.polaris.canvas.common;


import org.apache.log4j.Level;
/**
 * CustomLevel class is used for adding the user defined level for performance by extending
 * Level class.This level will be attached to the ERROR level so all the performance log  
 * will be generate even ERROR mode is set in log4j.propertis.
 * @author ranjitkumar.sahoo
 *
 */
public class CustomLevel extends Level {

	private static final long serialVersionUID = 7288304330257085144L;
	private static String PERF_STR = "PERF";

	public static final int PERF_INT = Level.ERROR_INT + 1;
	public static final CustomLevel PERF = new CustomLevel(PERF_INT, PERF_STR, 3);

	
	/**
	 * Constructor which will take these parametere and call the super class
	 * constructor .
	 * @param levleInt
	 * @param levelString
	 * @param syslogEquiv
	 */
	protected CustomLevel(int levleInt, String levelString, int syslogEquiv) {
		super(levleInt, levelString, syslogEquiv);
	}

	/***
	 Convert the string passed as argument to a level. If the
	 conversion fails, then this method returns {@link #PERF}. 
	 */
	public static Level toLevel(String sArg) {
		return (Level) toLevel(sArg, CustomLevel.PERF);
	}
	/**
	 *  toLevel is a hiding method of class Level which will be used while using custom
	 *  level. This method inturn will call toLevel fo Level no custom level is being set. 
	 * @param sArg
	 * @param defaultValue
	 * @return
	 */
	public static Level toLevel(String sArg, Level defaultValue) {

		if (sArg == null) {
			return defaultValue;
		}
		String stringVal = sArg.toUpperCase();

		if (stringVal.equals(PERF_STR)) {
			return CustomLevel.PERF;
		}

		return Level.toLevel(sArg, (Level) defaultValue);
	}

	/**
	 * toLevel is a hiding method of class Level which will be used while using custom
	 *  level. This method inturn will call toLevel fo Level no custom level is being set. 
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Level toLevel(int i) throws IllegalArgumentException {
		switch (i) {
		case PERF_INT:
			return CustomLevel.PERF;
		}
		return Level.toLevel(i);
	}

}
