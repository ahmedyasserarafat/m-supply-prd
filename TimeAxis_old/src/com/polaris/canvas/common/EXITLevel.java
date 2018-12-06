/***************************************************************
 * These materials are confidential and proprietary to Polaris Software 
 * Lab Limited and no part of these materials should be reproduced, 
 * published, transmitted or distributed  in any form or by any means, 
 * electronic, mechanical, photocopying, recording or otherwise, or 
 * stored in any information storage or retrieval system of any nature 
 * nor should the materials be disclosed to third parties or used in any 
 * other manner for which this is not authorized, without the prior express 
 * written authorization of Polaris Software Lab Limited.
 *
 * Copyright 2007. Polaris Software Lab Limited. All rights reserved.
 *****************************************************************/
package com.polaris.canvas.common;

import org.apache.log4j.Level;

/**
 * The EXITLevel class is subclass of Level to define a EXIT level set.
 * @see org.apache.log4j.Level
 */
public final class EXITLevel extends Level
{
	/**
	 * Default value of serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* Reteieves an EXITLEVEL object with the specified level referenced by the strLevel string
	 * @param level - level
	 * @param strLevel - String used to reperesent the level
	 * @param syslogEquiv - systemlogEquivalent
	 */
	protected EXITLevel(int level, String strLevel, int syslogEquiv)
	{
		super(level, strLevel, syslogEquiv);
	}

	/**
	 * Convert the String argument to a level. If the conversion
	 * fails then this method returns {@link #EXIT}.
	 * @param sArg - String repesenting the Level for conversion
	 * @return Returns Level after conversion
	 */
	public static Level toLevel(String sArg)
	{
		return (Level) toLevel(sArg, EXITLevel.EXIT);
	}

	/**
	 * Convert the String argument to a level. If the conversion
	 * fails, return the level specified by the second argument,
	 * i.e. defaultValue.
	 * @param sArg - String repesenting the Level for conversion
	 * @return Returns Level after conversion
	 */
	public static Level toLevel(String sArg, Level defaultValue)
	{
		if (sArg == null)
		{
			return defaultValue;
		}
		String stringVal = sArg.toUpperCase();
		if (stringVal.equals(EXIT_STR))
		{
			return EXITLevel.EXIT;
		}
		return Level.toLevel(sArg, (Level) defaultValue);
	}

	/**
	 * Convert an integer passed as argument to a level. If the
	 * conversion fails, then this method returns {@link #INFO}.
	 * @param i -  Integer repesenting the Level for conversion
	 * @return Returns Level after conversion
	 */
	public static Level toLevel(int i) throws IllegalArgumentException
	{
		if (i == EXIT_INT)
		{
			return EXITLevel.EXIT;
		} else
		{
			return Level.toLevel(i);
		}
	}

	public static final int EXIT_INT = Level.INFO_INT;//This variable represents the integer value for the Level
	private static String EXIT_STR = "EXIT";//This variable represents the integer value for the Level
	public static final EXITLevel EXIT = new EXITLevel(EXIT_INT, EXIT_STR, 7);//This variable represents the Level
}