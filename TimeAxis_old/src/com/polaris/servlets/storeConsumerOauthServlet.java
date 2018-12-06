package com.polaris.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.polaris.canvas.common.ENTRYLevel;
import com.polaris.canvas.common.EXITLevel;
import com.polaris.canvas.common.PerformanceTimer;
import com.polaris.canvas.common.StringUtils;
import com.polaris.canvas.common.Utility;
import com.polaris.canvas.properties.reader.PropertyReader;
import com.polaris.store.oauth.RedirectException;
import com.polaris.store.oauth.StoreConsumerApi;
import com.scribe.logger.LoggingOutputStream;

/**
 * Servlet implementation class storeConsumerOauthServlet
 */
 
public class storeConsumerOauthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> tokens = new HashMap<String, String>();

	private final PropertyReader mReader = new PropertyReader("config");
	private OAuthService service;
	static final Logger logger = Logger.getLogger(storeConsumerOauthServlet.class);
	private List<String> urlList=null;
	
	
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		urlList = new ArrayList<String>();
		
		String[] validReferersStr = StringUtils.convertToArray(mReader.retrieveProperty("VALID_REFERRER") ,",");
		if (validReferersStr != null)
		{
			List<String> tmpList = Arrays.asList(validReferersStr);
			for (int i = 0; i < tmpList.size(); i++)
			{
				urlList.add(tmpList.get(i).trim());
			}
		}
		
	}	
	
	//============== START MAIN SERVICE CALL ==================

	@SuppressWarnings("unchecked")
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String cmName = "ConsumerOauthService";
		logger.log(ENTRYLevel.ENTRY, "Entered into "+cmName);
		PerformanceTimer serv= new PerformanceTimer();
		try {		
		serv.startTimer(cmName);
		
		
		String referer = req.getHeader("referer");
		
		if(!urlList.contains(referer)){
			throw new IOException("Invalid referer");
		}
		
		HttpSession session = req.getSession();
		//OAuth Service, using Scribe
		service = new ServiceBuilder()
				.provider(StoreConsumerApi.class)
				.apiKey("E5yCcZlqEh9qTBZAqQcAg")
				.apiSecret("A3P1FdX6mwSY1VF6FXoKq1p9ISpKgYBTb7BiViPeU48")
				.callback(mReader.retrieveProperty("CONSUMER_CALLBACK")) 
				.debugStream(new LoggingOutputStream(Category.getInstance("org.scribe"),Priority.DEBUG,req.getParameter("authorizationKey")))
				.build();

		
		

		
			//Get stored tokens if any
			String oAuthToken = tokens.get("oauth_token");
			String oAuthSecret = tokens.get(oAuthToken);
			System.out.println("AUTH TOKEN=" + oAuthToken + " SECRET=" + oAuthSecret);
			logger.log(ENTRYLevel.INFO,"Session AUTH TOKEN=" + oAuthToken + "Session  SECRET=" + oAuthSecret);


			// ============== GET ACCESS TOKEN =================
			Token requesttoken = getRequestToken(oAuthToken, oAuthSecret, session);

			session.setAttribute("oauthService", service);
			session.setAttribute("consumersecret", "A3P1FdX6mwSY1VF6FXoKq1p9ISpKgYBTb7BiViPeU48");
			session.setAttribute("authorizationKey", req.getParameter("authorizationKey"));
			logger.log(ENTRYLevel.INFO,"AUTH authorizationKey=" +req.getParameter("authorizationKey"));
			resp.sendRedirect(service.getAuthorizationUrl(requesttoken)+"&authorizationKey="+req.getParameter("authorizationKey")); 
			logger.debug("requesttoken--"+requesttoken);


		} 
		catch (Exception e) {
			logger.error("Caught Generic Exception in " +cmName+"--"+e.getMessage(), e);
			logger.fatal("Caught Generic Exception in " +cmName+"--"+ e.getMessage(), e);
			Utility.getPrintStackTrace(e);
			if (e instanceof RedirectException) {
				RedirectException redirect = (RedirectException) e;
				String targetURL = redirect.url();
				if (targetURL != null) {
					resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					resp.setHeader("Location", targetURL);
				}
			} 
		}
		finally{
		 	serv.endTimer();
			logger.log(EXITLevel.EXIT, "Exited "+cmName);					
		}
		
	}


	//============== END MAIN SERVICE CALL ==================


	

	/*
	 * Get the Access Token for signing requests. If we don't yet have it, go
	 * and get it from the OAuth Server, else from the session
	 */
	private Token getRequestToken(String oAuthToken, String oAuthSecret,
			HttpSession session) {
	
			Token requestToken = service.getRequestToken();
		
		return requestToken;
	}





}
