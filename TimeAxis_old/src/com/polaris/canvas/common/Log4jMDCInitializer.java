/*************************************************************************
* These materials are confidential and proprietary to Polaris Software 
* Lab Limited and no part of these materials should be reproduced, 
* published, transmitted or distributed  in any form or by any means, 
* electronic, mechanical, photocopying, recording or otherwise, or 
* stored in any information storage or retrieval system of any nature 
* nor should the materials be disclosed to third parties or used in any 
* other manner for which this is not authorized, without the prior express 
* written authorization of Polaris Software Lab Limited.
*
* Copyright 2009. Polaris Software Lab Limited. All rights reserved.
*************************************************************************/

package com.polaris.canvas.common;



import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
/**
 *  Perpose of Log4jMDCInitializer is to add the user no and host ip 
 *  to MDC, So that userNo and hostIp can be used for logging process.
 *
 * @author ranjitkumar.sahoo
 * @since Apr 30, 2009
 */

public class Log4jMDCInitializer {
/**
 * This api will be used for setting the userNo and hostIp in Mapped Diagnostic Context.
 * @param userNo
 * @param hostIp
 */
 public void initLog4jMDC(String userNo,String hostIp,String requestId) {
	
		MDC.put(USER_ID,userNo);
		MDC.put(HOST_IP,hostIp);
		MDC.put(REQUEST_ID,requestId);
	 }
 /**
  * This api will be used for setting the userNo and hostIp in Mapped Diagnostic Context.
  * @param userNo
  * @param hostIp
  */
  public void initLog4jMDC(String userNo,String hostIp) {
 	
 		MDC.put(USER_ID,userNo);
 		MDC.put(HOST_IP,hostIp);
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
	//FW_EXPORT_ENHANCEMENT - starts
	/**
	 * Gets the current Request Id from the MDC. If this is not present, returns
	 * a dynamic generated one
	 * 
	 * @return The Request Id from the MDC
	 */
	public String getCurrentRequestId() {
		String reqID = (String) MDC.get(REQUEST_ID);
		if (reqID == null || "".equals(reqID)) {
			reqID = "RID_" + UUID.randomUUID().toString();
			MDC.put(REQUEST_ID,reqID);//FW_EXPORT_ENHANCEMENT_UPD - Need to set it back.
		}
		return reqID;
	}
	//FW_EXPORT_ENHANCEMENT - ends
	/**
  * logUserIdAndHostIpForScheduler is used for setting the scheduler name ,hostIP 
  * as SCHEDULER .The use of the setting of these values in Maped Diagnostic Context is to 
  * keep track of incoming request i.e from Scheduler .
  * @param schedulerName
  */
 
/* public void logUserIdAndHostIpForScheduler(String schedulerName)
 {
	 	initLog4jMDC(schedulerName,"SCHEDULER");
 }*/
 
 	private static final String USER_ID="USER_ID";
 	private static final String HOST_IP="HOST_IP";
 	private static final String REQUEST_ID="REQUEST_ID";
 	
	Logger logger=Logger.getLogger(Log4jMDCInitializer.class);
}
