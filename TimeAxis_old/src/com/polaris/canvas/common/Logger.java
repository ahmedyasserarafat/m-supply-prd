/*******************************************************************************
 * These materials are confidential and proprietary to Polaris Software Lab
 * Limited and no part of these materials should be reproduced, published,
 * transmitted or distributed in any form or by any means, electronic,
 * mechanical, photocopying, recording or otherwise, or stored in any
 * information storage or retrieval system of any nature nor should the
 * materials be disclosed to third parties or used in any other manner for which
 * this is not authorized, without the prior express written authorization of
 * Polaris Software Lab Limited.
 * 
 * Copyright 2007. Polaris Software Lab Limited. All rights reserved.
 ******************************************************************************/
package com.polaris.canvas.common;



import java.text.SimpleDateFormat;

import org.apache.log4j.MDC;



/**
 * This class is used to write the messages into log file. This class has been modified to route all the logging message
 * to log4j. Refer log4j.properties file for configurational information.
 */
public class Logger
{

	/**
	 * This default constructor does nothing
	 */
	public Logger()
	{
	}

	/**
	 * Retrieve a logger named according to the value of the name parameter. If the named logger already exists, then
	 * the existing instance will be returned. Otherwise, a new instance is created.
	 * 
	 * @param logName - Name of the logger
	 */
	public Logger(String logName)
	{
		logger = org.apache.log4j.Logger.getLogger(logName);
	}

	/*
	 * Method to log ENTRY level messages to the log file @param cmName - class and method name @param logMsg - Message
	 * to be logged
	 */
	public void logEntry(String cmName, String logMsg)
	{
		logger.log(ENTRYLevel.ENTRY, "[" + cmName + "]" + "[" + logMsg + "]");

	}

	/*
	 * Method to log INFO level messages to the log file @param cmName - class and method name @param logMsg - Message
	 * to be logged
	 */
	public void logInfo(String cmName, String logMsg)
	{
		logger.info("[" + cmName + "]" + "[" + logMsg + "]");

	}

	/*
	 * Method to log DEBUG level messages to the log file @param cmName - class and method name @param logMsg - Message
	 * to be logged
	 */
	public void logDebug(String cmName, String logMsg)
	{
		logger.debug("[" + cmName + "]" + "[" + logMsg + "]");
	}

	/*
	 * Method to log ERROR level messages to the log file @param cmName - class and method name @param logMsg - Message
	 * to be logged
	 */
	public void logError(String cmName, String errMsg)
	{
		logger.error("[" + cmName + "]" + "[" + errMsg + "]");
	}

	/*
	 * Method to log ERROR level messages to the log file for exceptions @param cmName - class and method name @param
	 * logMsg - Message to be logged
	 */
	public void logError(String cmName, Throwable pex)
	{
		logger.error("[" + cmName + "]" + "Exception logged by:[" + pex.getClass().getName() + "]Message:["
				+ pex.getMessage() + "]ExceptionStackTrace:[" + Utility.getPrintStackTrace(pex) + "]");
	}

	/*
	 * Method to log EXIT level messages to the log file @param cmName - class and method name @param logMsg - Message
	 * to be logged
	 */
	public void logExit(String cmName, String logMsg)
	{
		logger.log(EXITLevel.EXIT, "[" + cmName + "]" + "[" + logMsg + "]");
	}

	/**
	 * This method parses cmName(class method name), logMsg, and type values then calls the this.write(), where that
	 * parsed message actually getting log
	 * 
	 * @param cmName - class and method name
	 * @param logMsg - the messge which has to log
	 * @param type - type of log
	 */
	public void log(String cmName, String logMsg, String type)
	{
		StringBuffer buf = new StringBuffer();
		buf.append('[');
		buf.append(type);
		buf.append(']');
		buf.append('[');
		buf.append(cmName);
		buf.append(']');
		buf.append('[');
		buf.append(logMsg);
		buf.append(']');
		log(buf.toString());

	}

	/**
	 * This methos will calles write(String log) by passing logMsg, where logMsg actually logs into logger
	 * 
	 * @param logMsg - the message which has to get logs
	 */
	public void log(String logMsg)
	{
		logger.info(logMsg);
	}

	/**
	 * This method will created the given file name in the form of logName and then calls logDebug
	 * 
	 * @param logName - name of log file name, where log message will get log
	 * @param msg - log message
	 * @param cmName - name of class and mehthod
	 */
	public static void write(String logName, String msg, String cmName)
	{
		Logger logger = LogWriterFactory.getLogWriter(logName);
		logger.logDebug(cmName, msg);
	}

	/**
	 * This method will created the given file name in the form of logName and then calls logDebug
	 * 
	 * @param logName - name of log file name, where log message will get log
	 * @param msg - log message
	 * @param cmName - name of class and method
	 * @param sId -
	 */
	public static void write(String logName, String msg, String cmName, String sId)
	{
		Logger logger = LogWriterFactory.getLogWriter(logName);
		logger.logDebug(cmName, msg);
	}

	/**
	 * This method will calls this.logInfo(String cmName,String msg)
	 * 
	 * @param logName - name of the log file
	 * @param cmName - class and method name
	 * @param msg - the actual message which will get log
	 */
	public void logInfo(String logName, String cmName, String msg)
	{
		logInfo(cmName, msg);
	}

	/**
	 * This method will calls this.logDebug(String cmName,String msg)
	 * 
	 * @param logName - name of the log file
	 * @param cmName - class and method name
	 * @param msg - the actual message which will get log
	 */

	public void logDebug(String logName, String cmName, String msg)
	{
		logDebug(cmName, msg);
	}

	/**
	 * This method will calls this.logError(String cmName,String msg)
	 * 
	 * @param logName - name of the log file
	 * @param cmName - class and method name
	 * @param msg - the actual message which will get log
	 */
	public void logError(String logName, String cmName, String msg)
	{
		logError(cmName, msg);
	}

	/**
	 * This method will calls this.logError(String cmName,Exception pNExp)
	 * 
	 * @param logName - name of the log file
	 * @param cmName - class and method name
	 * @param pNExp - the exception which has to get log
	 */
	public void logError(String logName, String cmName, Exception pNExp)
	{
		logError(cmName, pNExp);
	}

	/**
	 * This method is used by AuditLog class for logging
	 * 
	 * @param logMsg - the message to be logged
	 */
	public void auditLog(String logMsg)
	{
	}

	/**
	 * Inserts the data passed into the key USER_ID in Mapped Diagnostic Context
	 * 
	 * @param userId - String value that is to mapped to the key USER_ID
	 */
	public void logUserId(String userId)
	{
		MDC.put(USER_ID, userId);
	}

	/**
	 * Inserts the data passed into the key HOST_IP in Mapped Diagnostic Context
	 * 
	 * @param hostIp - String value that is to mapped to the key HOST_IP
	 */
	public void logHostIp(String hostIp)
	{
		MDC.put(HOST_IP, hostIp);
	}

	/**
	 * Inserts the data passed into the key HOST_IP in Mapped Diagnostic Context
	 * 
	 * @param hostIp - String value that is to mapped to the key HOST_IP
	 */
	public void logRequestId(String requestId)
	{
		MDC.put(REQUEST_ID, requestId);
	}

	/**
	 * Removes data from user Id and host ip from the mapped diagnostic context
	 * 
	 */
	public void removeFromMDC()
	{
		MDC.remove(USER_ID);
		MDC.remove(HOST_IP);
		MDC.remove(REQUEST_ID);
	}

	public SimpleDateFormat formatter = new SimpleDateFormat("[dd-MM-yyyy] [HH:mm:ss]");
	private org.apache.log4j.Logger logger = null;
	public static final String USER_ID = "USER_ID";
	public static final String HOST_IP = "HOST_IP";
	public static final String REQUEST_ID = "REQUEST_ID";
}
