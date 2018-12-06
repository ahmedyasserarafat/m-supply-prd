/**
    COPYRIGHT NOTICE

    Copyright 2009 Polaris Software Lab Limited. All rights reserved.

    These materials are confidential and proprietary to 
    Polaris Software Lab Limited and no part of these materials should
    be reproduced, published, transmitted or distributed in any form or
    by any means, electronic, mechanical, photocopying, recording or 
    otherwise, or stored in any information storage or retrieval system
    of any nature nor should the materials be disclosed to third parties
    or used in any other manner for which this is not authorized, without
    the prior express written authorization of Polaris Software Lab Limited.
*/
package com.polaris.servlets;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.polaris.canvas.properties.reader.PropertyReader;
/**
 * This servlet performs the necessary initialization of Log 4J based on the log file that needs to be configured. 
 */
public class LogInitializationServlet extends HttpServlet
{

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	private final PropertyReader mReader = new PropertyReader("config");
	@Override
	public void init() throws ServletException
	{
		// Property Configurator for logging 
		String logfilePath = mReader.retrieveProperty(LOGFILE_PATH);
		String timeInterval = mReader.retrieveProperty(TIME_INTERVAL);
		long timeIntervalConfig  = 0;
	
		try
		{
			timeIntervalConfig = Long.parseLong(timeInterval);
		}
		catch(NumberFormatException numEx)
		{
			/*System.out.println("Number Format Exception ocurred while configuring logs. Check the configuration for " + TIME_INTERVAL + "='" + timeInterval + "'");*/
			timeIntervalConfig = DEFAULT_TIME_INTERVAL;
		}
		//First get the path to the log4j.properties.
		URL aURL = Loader.getResource(logfilePath);
		//Configure the Log4j properties.
		if (aURL == null)
		{
			PropertyConfigurator.configureAndWatch(logfilePath);
			//Create a Logger and log the path information from where it has been initialized.
			Logger logger = Logger.getLogger("LogInitializationServlet"); 
			logger.info("Configured Logger with path = '" + logfilePath + "'");
		}
		else
		{
			PropertyConfigurator.configureAndWatch(aURL.getPath(), timeIntervalConfig);
			//Create a Logger and log the path information from where it has been initialized.
			Logger logger = Logger.getLogger("LogInitializationServlet"); 
			logger.info("Configured Logger with path = '" + aURL.getPath() + "'");
		}
	}

	/**
	 * Internal constant for serialization purposes
	 */
	private static final long serialVersionUID = 8487435489350499337L;
	
	public static final String LOGFILE_PATH = "LOGFILE_PATH";
	public static final String TIME_INTERVAL = "LOG4J_PROPERTY_FILE_CHANGE_POLLING_TIME_INTERVAL";
	public static final long DEFAULT_TIME_INTERVAL = 2000;

}
