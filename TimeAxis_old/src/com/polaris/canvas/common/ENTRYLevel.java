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
 * The ENTRYLevel class is subclass of Level to define a ENTRY level set.
 * @see org.apache.log4j.Level
 */
public final class ENTRYLevel extends Level
{

	/**
	 * Default value of serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/* Reteieves an ENTRYLEVEL object with the specified level referenced by the strLevel string
	 * @param level - level
	 * @param strLevel - String used to reperesent the level
	 * @param syslogEquiv - systemlogEquivalent
	 */
	protected ENTRYLevel(int level, String strLevel, int syslogEquiv)
	{
		super(level, strLevel, syslogEquiv);
	}
	
	/**
	 * Convert the String argument to a level. If the conversion
	 * fails then this method returns {@link #ENTRY}.
	 * @param sArg - String repesenting the Level for conversion
	 * @return Returns Level after conversion
	 */
	public static Level toLevel(String sArg)
	{
		return (Level) toLevel(sArg, ENTRYLevel.ENTRY);
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
		if (stringVal.equals(ENTRY_STR))
		{
			return ENTRYLevel.ENTRY;
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
		if (i == ENTRY_INT)
		{
			return ENTRYLevel.ENTRY;
		} else
		{
			return Level.toLevel(i);
		}
	}

	static public final int ENTRY_INT = Level.INFO_INT;//This variable represents the integer value for the Level
	private static String ENTRY_STR = "ENTRY";//This variable represents the integer value for the Level
	public static final ENTRYLevel ENTRY = new ENTRYLevel(ENTRY_INT, ENTRY_STR, 7);//This variable represents the Level
}