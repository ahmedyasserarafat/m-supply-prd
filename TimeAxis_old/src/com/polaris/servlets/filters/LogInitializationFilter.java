/*
    COPYRIGHT NOTICE
    Copyright 2010 Polaris Software Lab Limited. All rights reserved.
    These materials are confidential and proprietary to 
    Polaris Software Lab Limited and no part of these materials should
    be reproduced, published, transmitted or distributed in any form or
    by any means, electronic, mechanical, photocopying, recording or 
    otherwise, or stored in any information storage or retrieval system
    of any nature nor should the materials be disclosed to third parties
    or used in any other manner for which this is not authorized, without
    the prior express written authorization of Polaris Software Lab Limited.
 */

package com.polaris.servlets.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.polaris.canvas.common.Log4jMDCInitializer;
import com.polaris.canvas.common.PerformanceTimer;


/**
 * Purpose: Filter for initializing current user,host and reqId information for logging purposes. The following should be added as
 * appropriate in the web.xml
 * 
 * ------------------------------------------------------------- <filter> <filter-name>LogInitializationFilter</filter-name>
 * <display-name>LogInitializationFilter</display-name> <filter-class>LogInitializationFilter</filter-class> </filter> <filter-mapping>
 * <filter-name>LogInitializationFilter</filter-name> <url-pattern>[Web components to be protected]</url-pattern> </filter-mapping>
 * ----------------------------------------------------------------
 */

public class LogInitializationFilter implements Filter
{
	private FilterConfig filterConfig;
	private Log4jMDCInitializer mInitializer;
	private String mode = "DENY";
	

	public void init(FilterConfig config) throws ServletException
	{
		this.filterConfig = config;
		//PropertyConfigurator.configure("log4j.properties");
	}

	/*
	 *  (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * This method get the UserNo,Host & ReqID .
	 * This value(UserNo,Host & ReqID) will be setting Log4j(Log4jMDCInitializer.java).
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		PerformanceTimer logInit = new PerformanceTimer();
		try
		{
			String reqId = java.util.UUID.randomUUID().toString();
			HttpServletRequest req = (HttpServletRequest) request;
			String Host = request.getRemoteAddr();
			String userNo = "";
			mInitializer = new Log4jMDCInitializer();
			String userName = request.getParameter("authorizationKey");
			if (userName == null)
			{
				HttpSession session = req.getSession(false);
				userName=(String) session.getAttribute("authorizationKey");
			}
			if (userName != null)
			{
				userNo = userName;
			}

			logger.debug("Setting in Log4MDC---> " + userNo + "   " + Host + "   " + reqId);
			mInitializer.initLog4jMDC(userNo, Host, reqId);
			logInit.startTimer("Request ID:" + reqId + " Request URI:" + ((HttpServletRequest) request).getRequestURI());
			logger.info("User Id set as - " + userNo);
			((HttpServletResponse) response).addHeader("X-FRAME-OPTIONS", mode);  
			chain.doFilter(request, response);
			logger.debug("out of  doFilter method");

		} finally
		{
			logInit.endTimer();
			mInitializer.removeFromMDC();
		}
	}

	/**
	 * @param config the FilterConfig to set
	 */
	public void setFilterConfig(FilterConfig config)
	{
		logger.debug("inside setFilterConfig method : Filter Config Set");
		filterConfig = config;
	}

	/**
	 * 
	 * @return filterConfig
	 */
	public FilterConfig getFilterConfig()
	{
		return filterConfig;
	}

	public void destroy()
	{
	}

	private Logger logger = Logger.getLogger(LogInitializationFilter.class);

	
}
