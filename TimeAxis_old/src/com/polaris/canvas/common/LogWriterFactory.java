/**
 * Copyright ©  2004. Polaris Software Lab Limited. All rights reserved.
 * These materials are confidential and proprietary to Polaris Software
 * Lab Limited and no part of these materials should be reproduced,
 * published, transmitted or distributed  in any form or by any means,
 * electronic, mechanical, photocopying, recording or otherwise, or
 * stored in any information storage or retrieval system of any nature
 * nor should the materials be disclosed to third parties or used in any
 * other manner for which this is not authorized, without the prior express
 * written authorization of Polaris Software Lab Limited.
 */

package com.polaris.canvas.common;




import java.util.HashMap;


/**
 * This class is used to create Logger object for writing messages into log file.
 *
 */
public class LogWriterFactory
{
                  
/*
 *	This method is for getting the Logger instance of synchronous logger
 * @param    logName - Name of the logger
 */
    public static Logger getLogWriter(String logName)
    {
        Logger writer= null;
        String key = logName+SYN_LOG;
        synchronized( mLock )
        {
           writer= (Logger)mLogObject.get(key);
            if(writer == null)
            {
				writer= new Logger(logName );
                mLogObject.put(key,writer);
            }
        }
        return writer;
    }
    


	private static final String SYN_LOG  = "_syn";
    private static final String ASYN_LOG = "_asyn";
    private static HashMap mLogObject = new HashMap();
    private static Object mLock = new Object();  
}
