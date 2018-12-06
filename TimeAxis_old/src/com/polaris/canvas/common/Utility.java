/*
 *  Copyright ©  2004. Polaris Software Lab Limited. All rights reserved.
 *  These materials are confidential and proprietary to Polaris Software Lab Limited 
 *  and no part of these materials should be reproduced, published, transmitted or distributed  
 *  in any form or by any means, electronic, mechanical, photocopying, recording or otherwise, 
 *  or stored in any information storage or retrieval system of any nature nor should the materials 
 *  be disclosed to third parties or used in any other manner for which this is not authorized, 
 *  without the prior express written authorization of Polaris Software Lab Limited.
 */

/**
 * Scope  :
 * Author : N. Rangasamy
 * Date   : 05-06-2004
 *
 *
 */
/*************************************************************************************************
 CHANGE CODE		AUTHOR					DESCRIPTION									DATE

 CHG001			Udesh.p				CHanging Date based on UserPersonlization		19/09/2005								
 CHG002			Udesh.p				CHanging Date based on UserPersonlization		08/11/2005								
 with code review comment
 CHG003			Ashok				Changed for DIB Customization					12/03/2006
 CHG004			Nagarajan           getDateTime() added for Report File Name        26/04/2006
 CHG002_29796	Srimathy			Issue Fix										02/03/2007
 CHG62679       Mahesh V            FileOutputStream ,
 									ObjectOutputStream FileReader   obj closed .	08/04/2011	
 CHG60293       Madan            	Decrypting string 								28dec2011	
 CHG005         N.Arunkumar         Reject Reason                                   10JAN2012      
  CHG60293_01      Madan	          	getting Correspondent Bank bic code				11jan2012
 FW128DATAENTL001 	Franklin J			Changes made to perform data level
 										entitlement, adding the channel 
 										in the input params .							07/12/2012
  CHG006		Geetha.S		 Beneficiary Authorization Flag Based Change	11/25/13
  NBAD_UAT_7781	Dhanasekar.V	Daily Payment Limit for File Upload				17/12/2013
*************************************************************************************************/
package com.polaris.canvas.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;



public class Utility
{
	private static String HASH_MAP_POSITION = "26";
	private static int VER_NO_POS = 16;
	private static int TXN_STATUS_POS = 15;
	private int HASH_CONN_POSITION = 1;

	public Utility()
	{
	}




	public Connection getConnection()
	{
		Connection connection = null;
		Context ctx = null;
		try
		{
			Properties map = new Properties();
			map.put(Context.INITIAL_CONTEXT_FACTORY, getConnectionString("JNDI_FACTORY"));
			map.put(Context.PROVIDER_URL, getConnectionString("PROVIDER_URL"));
			ctx = new InitialContext(map);
			DataSource ds = (DataSource) ctx.lookup(getConnectionString("DATASOURCE_NAME"));
			connection = ds.getConnection();

		} catch (Exception exception)
		{
			getPrintStackTrace(exception);
		}
		return connection;
	}

	/* for cyclic dependencies
	 public Connection getConnection(){
	 Connection connection = null;
	 try{
	 DataManager datamanager = new DataManager();
	 connection = datamanager.getConnection();
	 }catch(Exception exception){
	 }
	 return connection;
	 }
	 */
	public Connection getHashConnection(Vector inputVector)
	{
		Connection connection = null;
		try
		{
			connection = (Connection) inputVector.get(inputVector.size() - HASH_CONN_POSITION);
		} catch (Exception exception)
		{
		}
		return connection;
	}

	//This method replaces null to empty string present in the arraylist of hashmap
	//@param Arraylist Arraylist of HashMap containg null values
	//@return ArrayList HashMap null values replaced with empty string
	public static ArrayList replaceNull(ArrayList arrList)
	{
		int size = 0;
		HashMap eleMap = null;
		Iterator itr = null;
		String key = null;
		String value = null;

		if (arrList != null)
		{
			size = arrList.size();
			for (int index = 0; index < size; index++)
			{
				try
				{
					eleMap = (HashMap) arrList.get(index);
				} catch (Exception ex)
				{
					//Catching the exception..continuing with the next element
					continue;
				}

				itr = eleMap.keySet().iterator();
				while (itr.hasNext())
				{
					key = (String) itr.next();
					try
					{
						value = (String) eleMap.get(key);
						if (value == null)
							//eleMap.put(key,new String(""));
							eleMap.put(key, "");
					} catch (Exception ex)
					{
						//Catching the exception..continuing with the next element
						continue;
					}
				}
			}
		}
		return arrList;
	}

	public String getGUID(Connection conn) throws Exception
	{
		String msgId = "";
		Statement stmt = null;
		ResultSet rs = null;

		String query = "select lpad(SEQ_TRADE_TI.nextVal,6,'0') || " + "to_char(sysdate,'ddmmyyyyhh24miss') from dual";
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next())
			{
				msgId = rs.getString(1);
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (rs != null)
					rs.close();
			} catch (SQLException sqe)
			{
				sqe.printStackTrace();
			} catch (Exception exc)
			{
				exc.printStackTrace();
			}
			try
			{
				if (stmt != null)
					stmt.close();
			} catch (SQLException sq)
			{
				sq.printStackTrace();
			} catch (Exception excep)
			{
				excep.printStackTrace();
			}
		}
		return msgId;
	}

	public static String getQuotedCSV(String s) throws Exception
	{
		StringBuffer quotedCSV = null;
		StringTokenizer sr = new StringTokenizer(s, ",");
		quotedCSV = new StringBuffer();
		while (sr.hasMoreTokens())
		{
			quotedCSV.append(getQuoted(sr.nextToken()));
			quotedCSV.append(",");
		}
		String quotedStr = quotedCSV.toString();
		quotedStr = quotedStr.substring(0, quotedStr.length() - 1);
		return quotedStr;
	}

	public static String getQuoted(String s) throws Exception
	{
		if (s == null)
			return null;
		else
			s = "'" + s + "'";

		return s;
	}

	public static String[] convertStringToArray(String content, int upperLimit) throws Exception
	{
		int len = 0;
		int count = 0;
		int start = 0;
		int limit = upperLimit;
		int i = 0;
		String[] result = null;
		try
		{

			len = content.length();
			count = len / limit;
			count = (len % limit > 0) ? count + 1 : count;
			result = new String[count];
			for (i = 0; i < count - 1; i++)
			{
				if (limit > len)
				{
					result[i] = content.substring(start);
					break;
				}
				result[i] = content.substring(start, limit);
				start = limit;
				limit = limit + upperLimit;
			}
			result[i] = content.substring(start);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static HashMap formHashMap(String id1, String id2, String id3, HashMap id4)
	{
		HashMap elementHash = new HashMap();
		elementHash.put("id1", id1);
		elementHash.put("id2", id2);
		elementHash.put("id3", id3);
		elementHash.put("id4", id4);
		return elementHash;
	}

	public void writMapToFile(String fileName, HashMap queryMap)
	{
		
		FileOutputStream out = null;//CHG62679
		ObjectOutputStream s = null;//CHG62679
		try
		{
			 out = new FileOutputStream(fileName);//CHG62679
			 s = new ObjectOutputStream(out);//CHG62679
			if (queryMap != null)
			{
				s.writeObject(queryMap);
				s.flush();
			}
			s.close();
			out.close();
		} catch (FileNotFoundException fe)
		{
			fe.printStackTrace();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}finally{//CHG62679
			try{
				
				if(out!=null)
					out.close();
				if(s!=null)
					s.close();
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}//CHG62679

	}

	public HashMap getMapFromFile(String fileName)
	{
		HashMap inMap = null;
		FileInputStream in = null;//CHG62679
		ObjectInputStream s1 = null;//CHG62679
		try
		{
			 in = new FileInputStream(fileName);//CHG62679
			 s1 = new ObjectInputStream(in);//CHG62679
			inMap = (HashMap) s1.readObject();
			s1.close();
			in.close();
		} catch (FileNotFoundException fe)
		{
			
		} catch (IOException ioe)
		{
			
		}catch (ClassNotFoundException ioe)
		{
			
		}finally{//CHG62679
			try{
				
				if(in!=null)
					in.close();
				if(s1!=null)
					s1.close();
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}//CHG62679
		if (inMap == null)
			return new HashMap();
		return inMap;
	}

	/**
	 * Replace a single quote by two single quotes
	 * @param value
	 * @return String	 
	 */
	public static String replaceSingleQuote(String value)
	{
		value = replaceAll(value, "'", "''");
		return value;
	}

	private static String replaceAll(String value, String from, String to)
	{
		if (value == null || from == null || to == null || from.length() < 1 || value.indexOf(from) == -1)
			return value;
		StringBuffer replaced = new StringBuffer();
		int index = value.indexOf(from);
		while (index != -1)
		{
			replaced.append(value.substring(0, index)).append(to);
			value = value.substring(index + from.length());
			index = value.indexOf(from);
		}
		return replaced.append(value).toString();
	}

	public static java.sql.Date HostDateFormater(String strDate)
	{
		java.sql.Date dt = null;
		String date = "";
		String month = "";
		String year = "";
		String formatedDate = "";
		if ("".equals(strDate))
			return java.sql.Date.valueOf(formatedDate);
		StringTokenizer st = new StringTokenizer(strDate, "/");
		int count = 0;
		while (st.hasMoreTokens())
		{
			if (count == 0)
				date = st.nextToken();
			else if (count == 1)
				month = st.nextToken();
			else if (count == 2)
				year = st.nextToken();
			count++;
		}
		formatedDate = year + "-" + month + "-" + date;
		dt = java.sql.Date.valueOf(formatedDate);
		return dt;
	}

	public static String[] HostAddrFormater(String address)
	{
		String[] addr = new String[3];

		for (int i = 0; i < 3; i++)
			addr[i] = "";//new String();

		if ("".equals(address))
			return addr;
		StringTokenizer st = new StringTokenizer(address, "\n");
		int count = 0;
		while (st.hasMoreTokens())
		{
			addr[count] = st.nextToken();
			count++;
		}
		return addr;
	}

	public static Double HostAmtFormater(String amount)
	{
		Double amt = null;
		if (!"".equals(amount))
			amt = new Double(amount);
		else
			amt = new Double(0);
		return amt;
	}

	public String dateFormater(String strDate)
	{
		String date = "";
		String month = "";
		String year = "";
		String formatedDate = "";
		if ("".equals(strDate))
			return formatedDate;
		StringTokenizer st = new StringTokenizer(strDate, "-");
		int count = 0;
		while (st.hasMoreTokens())
		{
			if (count == 0)
				year = st.nextToken();
			else if (count == 1)
				month = st.nextToken();
			else if (count == 2)
				date = st.nextToken();
			count++;
		}
		formatedDate = date + "/" + month + "/" + year;
		return formatedDate;
	}

	public static String getPropertyString(String value)
	{
		String entity = "";
		try
		{
			//CHG003
			if (!"".equals(value))
			{
				ResourceBundle res = ResourceBundle.getBundle("hostDataMapping");
				entity = res.getString(value);
			} else
				entity = value;

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return entity;
	}
	//CHG005 START
	public static String getPropertyString(String value, String bundleName)
	{
		String entity = "";
		try
		{
			//CHG003
			if (!"".equals(value))
			{
				ResourceBundle res = ResourceBundle.getBundle(bundleName);
				entity = res.getString(value);
			} else
				entity = value;

		} catch (Exception e)
		{
			//e.printStackTrace();
			entity = value;
		}
		return entity;
	}//CHG005 END

	public static String getConnectionString(String value)
	{
		String entity = "";
		try
		{
			ResourceBundle res = ResourceBundle.getBundle("orbionedirect");
			entity = res.getString(value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return entity;
	}

	/** CHG001
	 * The <code>getSystemDate</code>
	 * @ param HashMap
	 * @ return HashMap
	 * @ desc This method is used to get The System data
	 */
	public static String getSystemDate(Vector inputVector)
	{
		HashMap headerMap =null;// new HashMap();
		Object in = null;
		String dateValue = "";
		String delimiter = "/";
		in = inputVector.get(inputVector.size() - 2);
		if (in instanceof HashMap)
		{
			headerMap = (HashMap) in;
			String action = (String) headerMap.get("dateformat");
			if (action != null && !"".equals(action))
			{
				if (action.indexOf("/") != -1)
					delimiter = "/";
				else if (action.indexOf(".") != -1)
					delimiter = ".";
				else if (action.indexOf("-") != -1)
					delimiter = "-";
				else if (action.indexOf("#") != -1)
					delimiter = "#";
				String counter[] = removeValue(delimiter, action);
				dateValue = checkDateFormat(counter, action, delimiter);
			}
		} else
		{
			Calendar cal = Calendar.getInstance();
			String calDate = String.valueOf(cal.get(Calendar.DATE));
			String calMonth = String.valueOf((cal.get(Calendar.MONTH) + 1));
			if (calDate.length() == 1)
				calDate = "0" + calDate;

			if (calMonth.length() == 1)
				calMonth = "0" + calMonth;

			dateValue = calDate + "/" + calMonth + "/" + cal.get(Calendar.YEAR);
		}
		return dateValue;
	}

	// CHG001
	public static String[] removeValue(String delimiter, String splitValue)
	{
		String array[] = new String[3];
		if (splitValue != null)
		{
			array[0] = splitValue.substring(0, splitValue.indexOf(delimiter));
			array[1] = splitValue.substring(splitValue.indexOf(delimiter) + 1, splitValue.lastIndexOf(delimiter));
			array[2] = splitValue.substring(splitValue.lastIndexOf(delimiter) + 1, splitValue.length());
		}
		return array;

	}

	// CHG001
	private static String checkDateFormat(String counter[], String format, String delimiter)
	{

		Calendar cal = Calendar.getInstance();
		String formatter[] = removeValue(delimiter, format);
		int vTemp = 0;
		int mon = 0;
		int day = 0;
		int yr = 0;
		String returnData = "";

		String calDate = String.valueOf(cal.get(Calendar.DATE));
		String calMonth = String.valueOf((cal.get(Calendar.MONTH) + 1));
		if (calDate.length() == 1)
			calDate = "0" + calDate;

		if (calMonth.length() == 1)
			calMonth = "0" + calMonth;

		for (int i = 0; i < counter.length; i++)
		{

			if (counter[i].length() == 4 && (formatter[i].equals("YYYY") || formatter[i].equals("yyyy")))
			{
				yr = 1;
			} else if (counter[i].length() == 2 && (formatter[i].equals("YY") || formatter[i].equals("yy")))
			{
				yr = 1;
			}

			if (counter[i].length() == 3 && (formatter[i].equals("MON") || formatter[i].equals("mon") || formatter[i].equals("MMM")))
			{
				mon = 1;
			} else if (counter[i].length() == 2 && (formatter[i].equals("MM") || formatter[i].equals("mm")))
			{
				mon = 1;
			}

			if (counter[i].length() == 2 && (formatter[i].equals("DD") || formatter[i].equals("dd")))
				day = 1;

			if (day == 1)
			{
				vTemp++;
				if (vTemp == 3)
					returnData = returnData + calDate;
				else
					returnData = returnData + calDate + delimiter;
				day = 0;
			} else if (mon == 1)
			{
				vTemp++;
				if (vTemp == 3)
					returnData = returnData + calMonth;
				else
					returnData = returnData + calMonth + delimiter;
				mon = 0;
			} else if (yr == 1)
			{
				vTemp++;
				if (vTemp == 3)
					returnData = returnData + cal.get(Calendar.YEAR);
				else
					returnData = returnData + cal.get(Calendar.YEAR) + delimiter;
				yr = 0;
			}

		}
		return returnData;
	}

	// CHG001 Overloaded mthod
	public static String checkDateFormat(String counter[], String format)
	{
		String returnData = "";
		String dateString = "";
		try
		{ //CHG002
			SimpleDateFormat simpFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateString = counter[0] + "/" + counter[1] + "/" + counter[2];
			Date d1 = simpFormat.parse(dateString);
			SimpleDateFormat formatter1 = new SimpleDateFormat(format);
			returnData = formatter1.format(d1);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return dateString;
		}

		return returnData;
	}

	public static String getCurrentDate()
	{
		Calendar cal = Calendar.getInstance();
		String calDate = String.valueOf(cal.get(Calendar.DATE));
		String calMonth = String.valueOf((cal.get(Calendar.MONTH) + 1));

		if (calDate.length() == 1)
			calDate = "0" + calDate;

		if (calMonth.length() == 1)
			calMonth = "0" + calMonth;

		String dateValue = calDate + "/" + calMonth + "/" + cal.get(Calendar.YEAR);
		return dateValue;
	}

	/**
	 * Gets Current System time in 24 Hour:Minute format  
	 * @return		String 			Current time
	 */
	public static String getCurrentTime()
	{
		String currTime = "";
		GregorianCalendar gc = null;
		String calHour = "";
		String calMin = "";

		gc = (GregorianCalendar) Calendar.getInstance();
		calHour = String.valueOf(gc.get(Calendar.HOUR_OF_DAY));
		calMin = String.valueOf(gc.get(Calendar.MINUTE));

		if (calHour.length() == 1)
			calHour = "0" + calHour;

		if (calMin.length() == 1)
			calMin = "0" + calMin;

		currTime = calHour + ":" + calMin;
		return currTime;
	}

	/**
	 * Compares two Time String and returns True if the first Time String is less than or equal to the 
	 * second Time String
	 * The method assumes that timeValue1 and timeValue2 are in Format "HH:mm" (24 Hour and Minute format)
	 * @param 		timeValue1 		String value of timeValue1
	 * @param 		timeValue2		String value of timeValue2
	 * @param		boolean			Indicates whether equality check is to be carried out or not 
	 * @return 		boolean			True if the first time is less than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isTimeLessThanOrEqualTo(String timeValue1, String timeValue2, boolean equalCheck)
	{
		boolean result = false;
		GregorianCalendar time1 = null;
		GregorianCalendar time2 = null;

		//If one of fields is empty or null, return false
		if (timeValue1 == null || timeValue1.equals("") || timeValue2 == null || timeValue2.equals(""))
			return result;

		time1 = (GregorianCalendar) Calendar.getInstance();
		time2 = (GregorianCalendar) Calendar.getInstance();

		time1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeValue1.substring(0, timeValue1.indexOf(":"))));
		time1.set(Calendar.MINUTE, Integer.parseInt(timeValue1.substring(timeValue1.indexOf(":") + 1)));

		time2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeValue2.substring(0, timeValue2.indexOf(":"))));
		time2.set(Calendar.MINUTE, Integer.parseInt(timeValue2.substring(timeValue2.indexOf(":") + 1)));

		if (equalCheck)
		{
			if (time1.before(time2) || time1.equals(time2))
				result = true;
		} else if (time1.before(time2))
			result = true;

		return result;
	}

	/**
	 * Compares two Time String and returns True if the first Time String is greater than or equals to the
	 * second Time String
	 * The method assumes that timeValue1 and timeValue2 are in Format "HH:mm" (24 Hour and Minute format)
	 * @param 		timeValue1 		String value of timeValue1
	 * @param 		timeValue2		String value of timeValue2
	 * @param		boolean			Indicates whether equality check is to be carried out or not
	 * @return 		boolean			True if the first time is greater than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isTimeGreaterThanOrEqualTo(String timeValue1, String timeValue2, boolean equalCheck)
	{
		boolean result = false;
		GregorianCalendar time1 = null;
		GregorianCalendar time2 = null;

		//If one of fields is empty or null, return false
		if (timeValue1 == null || timeValue1.equals("") || timeValue2 == null || timeValue2.equals(""))
			return result;

		time1 = (GregorianCalendar) Calendar.getInstance();
		time2 = (GregorianCalendar) Calendar.getInstance();

		time1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeValue1.substring(0, timeValue1.indexOf(":"))));
		time1.set(Calendar.MINUTE, Integer.parseInt(timeValue1.substring(timeValue1.indexOf(":") + 1)));

		time2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeValue2.substring(0, timeValue2.indexOf(":"))));
		time2.set(Calendar.MINUTE, Integer.parseInt(timeValue2.substring(timeValue2.indexOf(":") + 1)));

		if (equalCheck)
		{
			if (time1.after(time2) || time1.equals(time2))
				result = true;
		} else if (time1.after(time2))
			result = true;

		return result;
	}

	public static ArrayList subList(ArrayList list, int startIndex, int endIndex)
	{
		if (endIndex >= list.size())
		{
			endIndex = list.size();
		}
		ArrayList returnList = new ArrayList();
		for (int i = startIndex; i < endIndex; i++)
		{
			returnList.add(list.get(i));
		}
		return returnList;
	}

	public static void serializeTxnData(String filePath, HashMap hmOnlineData)
	{
		FileOutputStream out = null ;//CHG62679
		ObjectOutputStream s = null;//CHG62679
		try
		{
			 out = new FileOutputStream(filePath);//CHG62679
			 s = new ObjectOutputStream(out);//CHG62679
			s.writeObject(hmOnlineData);
			s.flush();
			s.close();
			out.close();
		} catch (FileNotFoundException fe)
		{
			
		} catch (IOException ioe)
		{
			
		}finally{//CHG62679
			try{
				if(out!=null)
					out.close();
				if(s!=null)
					s.close();
			}catch(Exception ee){
				  ee.printStackTrace();
			}
		}//CHG62679
	}

	public static String getPrintStackTrace(Exception ex)
	{

		StringWriter writer = new StringWriter();
		PrintWriter pWriter = null;
		try
		{
			pWriter = new PrintWriter(writer);
			ex.printStackTrace(pWriter);
		} catch (Exception ep)
		{
		}
		return (writer.getBuffer()).toString();
	}

	/**
	 * Helper method which returns the stack trace for the given throwable as a String.
	 * @param ex The throwable for which the stack trace has to be created
	 * @return The String representation of the stack trace
	 */
	public static String getPrintStackTrace(Throwable ex)
	{

		StringWriter writer = new StringWriter();
		;
		PrintWriter pWriter = null;
		try
		{
			pWriter = new PrintWriter(writer);
			ex.printStackTrace(pWriter);
		} catch (Exception ep)
		{
		}
		return (writer.getBuffer()).toString();
	}

	//CHG004 - Start
	public static String getDateTime()
	{
		String mon[] =
		{ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String day[] =
		{ "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		TimeZone t = TimeZone.getDefault();
		Calendar calendar = new GregorianCalendar(t);
		Date trialTime = new Date();
		StringBuffer sbufFilename = new StringBuffer();

		calendar.setTime(trialTime);
		sbufFilename.append(day[(calendar.get(Calendar.DATE)) - 1]);
		sbufFilename.append(mon[(calendar.get(Calendar.MONTH))].toUpperCase());
		sbufFilename.append(Integer.toString(calendar.get(Calendar.YEAR)));
		sbufFilename.append("_" + Integer.valueOf(calendar.get(Calendar.HOUR_OF_DAY)).toString());
		sbufFilename.append(Integer.valueOf(calendar.get(Calendar.MINUTE)).toString());
		sbufFilename.append(Integer.valueOf(Calendar.SECOND).toString());
		sbufFilename.append(Integer.valueOf(Calendar.MILLISECOND).toString());

//		sbufFilename.append(new Integer(calendar.get(Calendar.MINUTE)).toString());
//		sbufFilename.append(new Integer(calendar.get(Calendar.SECOND)).toString());
//		sbufFilename.append(new Integer(calendar.get(Calendar.MILLISECOND)).toString());
		return sbufFilename.toString();
	}

	//CHG004 - End

	public static String getFormatedDate(String dateValue, String format)
	{

		String delimiter = "";
		if (!"".equals(dateValue))
		{
			if (dateValue.indexOf("/") != -1)
				delimiter = "/";
			else if (dateValue.indexOf(".") != -1)
				delimiter = ".";
			else if (dateValue.indexOf("-") != -1)
				delimiter = "-";
			else if (dateValue.indexOf("#") != -1)
				delimiter = "#";

			String array[] = Utility.removeValue(delimiter, dateValue);
			String returnDate = Utility.checkDateFormat(array, format);
			return returnDate;
		}
		return dateValue;
	}

	/*
	 * Applies decimal formatting to amount value
	 * @param amtVal Input Amount Value to be converted
	 * @return String Formatted amount (eg, 3253.50)
	 */
	public static String getAmount(String amtVal)
	{//Naga
		String amountStr = null;
		if ("".equals(amtVal))
			return amtVal;
		NumberFormat formatter = new DecimalFormat("####################0.00");
		amountStr = formatter.format(Double.parseDouble(amtVal));
		return amountStr;
	}

	//Added by CHG002_29796
	public static String getNFormat(String amountParse, String language)
	{
		String parseConverter = "";
		String appender = "";
		try
		{
			if (!"".equals(amountParse) && amountParse != null)
			{
				String lang = language.substring(0, language.indexOf("_"));
				String cntry = language.substring(language.indexOf("_") + 1, language.length());
				Locale locale = new Locale(lang, cntry);
				NumberFormat nformat = DecimalFormat.getNumberInstance(locale);
				double lng = Double.parseDouble(amountParse);
				parseConverter = nformat.format(lng);

				if (parseConverter.indexOf(".") != -1)
				{
					String str = parseConverter;
					str = str.substring(str.indexOf(".") + 1, str.length());
					if (str.length() != 2)
						appender = "0";

				} else if (parseConverter.indexOf(".") == -1)
				{
					String str = parseConverter;
					str = str.substring(str.length());
					appender = ".00";
				}
				parseConverter = parseConverter + appender;
			}
		} catch (NumberFormatException ep)
		{
			ep.printStackTrace();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return parseConverter;
	}

	//Ended by CHG002_29796
	
	//PHASE-2C karthika - fileupload Starts
	public static String checknull (String value)
	{
		if(value == null)
			return "";
		else
			return value;
	}
	//PHASE-2C karthika - fileupload Ends
	/**
	 * Checks for whether any one of the fields are present.
	 * @param  String[] array of field names of which atleast one is required
	 * @return boolean returns true if any one of the fields are present 
	 * else false 		
	 */
	public static boolean isOneOfFieldsPresent(String fields[])
	{
		if (fields != null)
		{
			for (int arrIndex = 0; arrIndex < fields.length; arrIndex++)
			{
				if (fields[arrIndex] != null && !fields[arrIndex].trim().equals(""))
					return true;
			}
			return false;
		} else
			return false;
	}

	/**
	 *	Replaces value of the String with an empty String if the value is null.
	 *   @param fieldValue- String which is to be changed as an empty string if the value is null.
	 *   @return returns an empty String if the value is null ,else original String
	 */
	public static String replaceNull(String fieldValue)
	{
		if (fieldValue == null)
			return "";
		else
			return fieldValue;
	}

	/**
	 * Finds the description for the key from the vertical based properties if
	 * the code and bundle are not an empty
	 * @param code - Key for which the description has to be found from the property file
	 * @param bundle - property file name
	 * @param langId - Language Id for referring the property file
	 * @param prefix - prefix
	 * @return Returns the description for the code
	 */


	/*
	 * Gets Comma separated String out of List object sent
	 * @param		List 		List object whose values are to be converted to String
	 * 						separated by comma
	 * @return		String		Comma seperated String of list values 
	 */
	public static String getCommaSeparatedString(List list)
	{
		int loop = 0;
		int len = 0;
		String retStr = "";

		if (list != null && list.size() > 0)
		{
			len = list.size();
			for (loop = 0; loop < len; loop++)
				retStr += (String) list.get(loop) + ",";
			retStr = retStr.substring(0, retStr.length() - 1);
		}
		return retStr;
	}

	/**
	 * Gets a set of String as String array given a comma separated list - like "100,101,102" gives a array of 3 elements
	 * @param list - The delimited string that has to be divided
	 * @return String[] The array of strings.
	 */
	public static String[] getCommaSeparatedList(String list)
	{
		return getDelimitedList(list, ",");
	}

	/**
	 * Gets a set of String as String array given a delimiter separated list - like "100^101^102" gives a array of 3 elements if delimiter is given as ^
	 * @param list - The delimited string that has to be divided
	 * @return String[] The array of strings.
	 */
	public static String[] getDelimitedList(String list, String delimiter)
	{
		if (list == null || delimiter == null)
			return null;
		StringTokenizer tok = new StringTokenizer(list, delimiter);
		int count = tok.countTokens();
		String sepList[] = null;
		if (count != 0)
		{
			sepList = new String[count];
			for (int i = 0; i < count; i++)
			{
				sepList[i] = tok.nextToken();
			}
		}
		return sepList;
	}

	/*
	 * Converts date value from One TimeZone to another.
	 * As of now, an empty implementation is provided. 
	 * @param		String			Date Value that is to be converted
	 * @param		String			Timezone of the incoming date value
	 * @param		String			Timezone in which the date value is to be converted
	 * @return		String			Date value in Target Timezone 
	 */
	public static String convertToTimeZone(String dateValue, String srcTimeZone, String targetTimeZone)
	{
		return dateValue;
	}

	/**
	 * Thie API used to find the delimiter value
	 * @param dateStr - Date string to find the delimiter
	 * @return Returns delimiter value if incoming date value contains any one of / . - #
	 * else returns empty string
	 */
	private static String findDelimiter(String dateStr)
	{
		logger.debug("Inside the method");
		String delimiter = "";
		if (dateStr.indexOf("/") != -1)
		{
			delimiter = "/";
		} else if (dateStr.indexOf(".") != -1)
		{
			delimiter = ".";
		} else if (dateStr.indexOf("-") != -1)
		{
			delimiter = "-";
		} else if (dateStr.indexOf("#") != -1)
		{
			delimiter = "#";
		}
		logger.debug("Leaving from method and delimiter value is :" + delimiter);
		return delimiter;
	}

	/**
	 * This method is used to convert standard date value to GregorianCalendar object
	 * The presumption made here is incoming date value always be in standard format(dd/MM/yyyy).
	 * Delimiter could be / . - #. However order should date(dd) followed by month(mm) followed by year(yyyy)
	 * @param datevalue - date value need to be convert as GregorianCalendar
	 * @return Returns GregorianCalendar instance
	 */
	public static GregorianCalendar parseDateStringToCalendar(String datevalue)
	{
		logger.debug("Inside parseDateStringToCalendar()");
		logger.debug("Date and date format are:" + datevalue);
		String DELIMATOR = findDelimiter(datevalue);
		StringTokenizer st = new StringTokenizer(datevalue, DELIMATOR);
		String[] tokens = new String[3];
		for (int index = 0; st.hasMoreTokens(); index++)
		{
			tokens[index] = st.nextToken();// new String(st.nextToken());
		}
		// made another assumption of frist one is date, second one is month and last one is year
		int date = Integer.parseInt(tokens[0]);
		int month = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		GregorianCalendar cale = new GregorianCalendar(year, month - 1, date);
		logger.debug("Leaving parseDateStringToCalendar()");
		return cale;
	}

	/**
	 * Compares two Dates and returns True if the first Date is greater than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * @param 		dateValue1 		String value of dateValue1
	 * @param 		dateValue2		String value of dateValue2
	 * @return 		boolean			True if the first date is greater than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isDateGreaterThanOrEqualTo(String dateValue1, String dateValue2)
	{
		return isDateGreaterThanOrEqualTo(dateValue1, dateValue2, true);
	}

	/**
	 * Compares two Dates and returns True if the first Date is greater than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * Equality check will be carried out based on value sent for thrid parameter
	 * @param 		dateValue1 		String value of dateValue1
	 * @param 		dateValue2		String value of dateValue2
	 * @param		equalCheck		boolean to indicate if equality check is to be carried out or not
	 * @return 		boolean			True if the first date is greater than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isDateGreaterThanOrEqualTo(String dateValue1, String dateValue2, boolean equalCheck)
	{
		boolean result = false;
		GregorianCalendar date1 = null;
		GregorianCalendar date2 = null;

		//If one of comparison fields is null/empty, return false
		if ((dateValue1 == null || dateValue1.equals("")) || (dateValue2 == null || dateValue2.equals("")))
			return result;

		date1 = parseDateStringToCalendar(dateValue1);
		date2 = parseDateStringToCalendar(dateValue2);

		if (equalCheck)
		{
			if (date1.after(date2) || date1.equals(date2))
				result = true;
		} else if (date1.after(date2))
			result = true;

		return result;
	}

	/**
	 * Compares two Dates and returns True if the first Date is less than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * @param 		dateValue1 		String value of dateValue1
	 * @param 		dateValue2		String value of dateValue2
	 * @return 		boolean			True if the first date is less than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isDateLessThanOrEqualTo(String dateValue1, String dateValue2)
	{
		return isDateLessThanOrEqualTo(dateValue1, dateValue2, true);
	}

	/**
	 * Compares two Dates and returns True if the first Date is less than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * Equality check will be carried out based on value sent for thrid parameter
	 * @param 		dateValue1 		String value of dateValue1
	 * @param 		dateValue2		String value of dateValue2
	 * @param		equalCheck		boolean to indicate if equality check is to be carried out or not
	 * @return 		boolean			True if the first date is less than or equal to the second one. 
	 * 								False otherwise.
	 */
	public static boolean isDateLessThanOrEqualTo(String dateValue1, String dateValue2, boolean equalCheck)
	{
		boolean result = false;
		GregorianCalendar date1 = null;
		GregorianCalendar date2 = null;

		//If one of comparison fields is null/empty, return false
		if ((dateValue1 == null || dateValue1.equals("")) || (dateValue2 == null || dateValue2.equals("")))
			return result;

		date1 = parseDateStringToCalendar(dateValue1);
		date2 = parseDateStringToCalendar(dateValue2);

		if (equalCheck)
		{
			if (date1.before(date2) || date1.equals(date2))
				result = true;
		} else if (date1.before(date2))
			result = true;

		return result;
	}

	/**
	 * Checks if the given date is present in the month passed. 
	 * @param 		String			Month value used in check
	 * @param 		String			Date value to be checked for presence in the month
	 * @return 		boolean			True if the given date is present in the month 
	 * 								False otherwise.
	 */
	public static boolean isValidDateInMonth(int month, int date)
	{
		boolean result = true;
		int minDate = 1;
		int maxDate = 31;

		//If month is February, last date is 29 (considering leap year)
		if (month == 2)
			maxDate = 29;
		// If month is August ,last date is 31
		else if (month == 8)
			maxDate = 31;
		//If month is even one other than February, last date is 30
		else if (month % 2 == 0)
			maxDate = 30;

		if (date < minDate || date > maxDate)
			result = false;

		return result;
	}

	/* 
	 * move()
	 * Move the files from one folder to another folder and deletes the source file
	 * @param: String - Source file name
	 * @param: String - Destination file name
	 */
	public static void move(String srcFileName, String destFileName)
	{
		logger.log(ENTRYLevel.ENTRY, "Entered into move()");
		FileReader fileReader = null; //CHG62679
		FileWriter fileWriter = null; 
		BufferedReader br = null;
		BufferedWriter bw = null;
		try
		{
			File srcFile = new File(srcFileName);
			File destFile = new File(destFileName);

			 fileReader = new FileReader(srcFile);
			 fileWriter = new FileWriter(destFile);

			 br = new BufferedReader(fileReader);
			 bw = new BufferedWriter(fileWriter);//CHG62679
			int read = 0;
			while ((read = br.read()) != -1)
				bw.write(read);

					
			srcFile.delete();
		} catch (FileNotFoundException filenotfoundExcp)
		{
			logger.error("File Not Found Exception occured while moving the files and the error message is: "
					+ filenotfoundExcp.getMessage());
		} catch (IOException ioexcp)
		{
			logger.error("Exception occured while moving the files and the error message is: " + ioexcp.getMessage());
		}finally{
			try{//CHG62679
				if(br!=null)
					br.close();
				if(bw!=null)
					bw.close();
				if(fileReader!=null)
					fileReader.close();
				if(fileWriter!=null)
					fileWriter.close();
			}catch(Exception ee){
				logger.error("Utility.move"+Utility.getPrintStackTrace(ee));
			}
		}//CHG62679
		logger.log(EXITLevel.EXIT, "Exited from move()");
	}

	// Splitter to split the list of columns separated by ','
	public static ArrayList splitString(String str)
	{
		String[] strArray = str.split(",");
		ArrayList strList = new ArrayList(strArray.length);
		for (int i = 0; i < strArray.length; i++)
		{

			strList.add(strArray[i]);
		}

		return strList;

	}

	//Method to create a string of gcif from array of gcifs
	public static String gcifList(String[] gcif)
	{

		StringBuffer str = new StringBuffer();
		for (int i = 0; i < gcif.length; i++)
		{
			str.append("'" + gcif[i] + "'");
			if (i != (gcif.length - 1))
			{
				str.append(",");
			}
		}

		return str.toString();
	}

	/**
	 * Parses standard date string of format dd/MM/yy
	 * and returns java.util.Date object 
	 * 
	 * @param pStrStandardDate
	 * @return
	 */
	public static Date parseStandardDate(String pStrStandardDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		try
		{
			return sdf.parse(pStrStandardDate);
		} catch (ParseException e)
		{
			logger.error("Error is parsing date. Input string is " + pStrStandardDate);
			logger.error(e);
			return null;
		}
	}

	/**
	 * Returns the date in new format. 
	 * @param sDate
	 * @param sOldFormat - The old format of the date.
	 * @param sNewFormat - The new format in which it is expected
	 * @return String - The date in the new format
	 */
	public String convertDateTo(String sDate, String sOldFormat, String sNewFormat)
	{
		logger.debug("Input date : " + sDate);
		logger.debug("sOldFormat " + sOldFormat + " sNewFormat " + sNewFormat);
		SimpleDateFormat newDateFormat = new SimpleDateFormat(sNewFormat);
		SimpleDateFormat oldDateFormat = new SimpleDateFormat(sOldFormat);
		logger.debug("newDateFormat " + newDateFormat + " oldDateFormat " + oldDateFormat);
		String sOldDate = sDate;
		Date date = null;
		String sNewDate = null;
		try
		{
			date = oldDateFormat.parse(sOldDate);
		} catch (ParseException pException)
		{
			logger.error("ParseException occured", pException);
		}
		sNewDate = newDateFormat.format(date);
		logger.debug("New Date : " + sNewDate);
		return sNewDate;
	}
	


	/**
     * Method that returns Date String for the given Format and Date Object
     * @param format String
     * @param date Date
     * @return String
     * @deprecated 
      		As of LS410, parameter Date is replaced by Calendar. 
     */
    public static String getDateAndTimeString(String format, Date date) {
        
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat( format );
        String dateandtime = "";
        try {
            dateandtime = simpleDateFormat.format( date );
        } catch (Exception e) {
            logger.error(e);
        }
        return dateandtime;
    }

	/**
     * Method isDirExist. Checks if the given Directory exists or not
     * @param FileName String
     * @return boolean
     */
    public static synchronized boolean isDirExist(String FileName) {
        
		boolean bRetVal = false;
        File openFile = null;
        try {
            openFile = new File(FileName);
            if (openFile.isDirectory()) {
                bRetVal = true;
            } else {
                bRetVal = false;
            }
        } catch (Exception e) {
            logger.error(e);
			bRetVal = false;
        }
        return bRetVal;
    }

	/**
	 * A utility method to convert date/time from one timezone to another
	 * @param fromTime - The source /from date time object
	 * @param timeZoneOffset - The timezone offset to convert to.
	 * @return the converted Calendar (date/time) object 
	 */
	public static Calendar convertTimeZone(Calendar fromTime,String timeZoneOffset)
	{
		Calendar toTimezoneCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneOffset));
	    toTimezoneCal.setTimeInMillis(fromTime.getTimeInMillis());
	    return toTimezoneCal;
	}
	

		
		
		
		
	
	public static String getDateFormater(String inputDate,String inputFormat,String outputFormat){
		String cmName = "[Utility.getDateFormater()]";
	    SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);        
	    SimpleDateFormat nformatter = new SimpleDateFormat(outputFormat);
	    Date newDate;
	    String convertDate = "";
	    try {
	        newDate = formatter.parse(inputDate);
	        convertDate = nformatter.format(newDate);
	    } catch (ParseException e) {        	
	    	  e.printStackTrace();
	    }   
		return convertDate;
	}



	//NBAD_UAT_7781 Ends

	private static Logger logger = Logger.getLogger(Utility.class);
}
