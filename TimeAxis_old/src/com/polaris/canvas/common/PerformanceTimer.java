package com.polaris.canvas.common;


import org.apache.log4j.Logger;

import com.polaris.canvas.properties.reader.PropertyReader;


/** The class to measure the performance of a section of a code.It is exposing 2 methods, which
 ** is to be called before and after of our code where we need to check the time taken for its completion
 ** Anything less than 1 second is not regarded as a problem, but if code elapsed for more than 3 sec it
 ** is considered as a performance issue and advice further reviewing.
 **/

public class PerformanceTimer
{
	private long startTime=0l;
	//private long endTime=0l;
	private String methodName=null;
	private static Logger logger=Logger.getLogger(PerformanceTimer.class);
	private final static PropertyReader mReader = new PropertyReader("config");
	private static final boolean loggerthreshold ;
	private static final int loggerthresholdlimit ;
	static{		
		String thresholdRequired = mReader.retrieveProperty("PERFORMANCE_TIMER_THRESHOLD"); 
		loggerthreshold = Boolean.valueOf(thresholdRequired.trim());
		//new Boolean(thresholdRequired.trim()).booleanValue();	
			
		if(loggerthreshold){
			String thresholdlimit = mReader.retrieveProperty("PERFORMANCE_TIMER_THRESHOLD_LIMIT");
			loggerthresholdlimit = new Integer(thresholdlimit.trim()).intValue();	
		}else{
			loggerthresholdlimit = 0;
		}		
	}
	/* method to set the start time for the timer
	 * @param startTime  millisecond time to be set as startTime
	 */
	private void setStartTime(long startTime)
	{
		this.startTime=startTime;
	}
	/* method to get the start time for the timer
	 * @return long the current millisecond elapsed when the timer made on
	 */
	private long getStartTime()
	{
		return this.startTime;
	}
	/* method to set the end time for the timer
	 * @param endTime  millisecond time to be set as endTime
	 */
//	private void setEndTime(long endTime)
//	{
//		this.endTime=endTime;
//	}
//	/* method to get the end time for the timer
//	 * @return long the current millisecond elapsed when the timer made off
//	 */
//	private long getEndTime()
//	{
//		return this.endTime;
//	}
	/* method to set the name of the method
	 * @param methodName: the name of the method which invoke the timer
	 */
	private void setMethodName(String methodName)
	{
		this.methodName=methodName;
	}
	/* method to set the name of the method
	 * @return String returns the method name
	 */
	private String getMethodName()
	{
		//return this.methodName.replaceAll("[$]", "DOLLAR");
		return this.methodName;
	}
	/* method to start the timer
	 * @param methodName: the name of the method which invoke the timer
	 */
	public void startTimer(String methodName) 
	{
		logger.debug("Entered into the startTimer of method ::"+methodName);
		setMethodName(methodName);
		if(startTime!=0l)
		{
			startTime=0l;
		}
		
			long setGo= System.currentTimeMillis();
			setStartTime(setGo);
		
		logger.debug("Exiting the startTimer of method ::"+methodName);
	}
	/* method to start the timer
	 * @param classname: the runtime object invoking the method.For abstract classes.
	 * @param methodName: the name of the method which invoke the timer
	 */
	public void startTimer(Class classname,String methodName) 
	{
		logger.debug("Entered into the startTimer of method ::"+methodName);
		setMethodName(getClassNameWithoutPackage(classname)+"."+methodName);
		if(startTime!=0l)
		{
			startTime=0l;
		}
		
			long setGo= System.currentTimeMillis();
			setStartTime(setGo);
		
		logger.debug("Exiting the startTimer of method ::"+methodName);
	}
	/* method to end the timer
	 */
	public void endTimer()
	{
		logger.debug("Entered into the endTimer of method ::"+getMethodName());
		long setGo=0l;
		long finish=0l;
		setGo = getStartTime();
		if(setGo!=0l)
		{
			finish= System.currentTimeMillis();
			long timeElapsed= finish-setGo;
			if(!loggerthreshold){
				//logger.info(getMethodName()+"()$"+timeElapsed+"$ms");
				logger.log(CustomLevel.PERF,getMethodName()+"()$"+timeElapsed+"$ms");
			}
			else{
				if(timeElapsed>=loggerthresholdlimit)
				{
					logger.log(CustomLevel.PERF, getMethodName()+"()$"+timeElapsed+"$ms");
				}			
			}
		}
		else
		{
			logger.warn("Error while loading the startTime::"+this.getMethodName());
			return;
		}
		setStartTime(0l);
		//setEndTime(0l);		
		logger.debug("Exiting the endTimer of method ::"+getMethodName());
		setMethodName(null);

	}
	private String getClassNameWithoutPackage(Class classname){ 
    	String clsname = "";
    	if(classname != null){
	    	clsname= classname.getName();
	    	int mid=clsname.lastIndexOf ('.') + 1;
	        String finalClsName = clsname.substring(mid);
	        return finalClsName;  
    	}else{    		
    		return "You passed null"; 
    	}
    }

}
