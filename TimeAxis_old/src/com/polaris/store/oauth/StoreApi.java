package com.polaris.store.oauth;

import org.apache.log4j.Logger;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.oauth.OAuthService;


public class StoreApi extends DefaultApi10a {


	 private static final String AUTHORIZATION_URL = "http://localhost:9080/store/storeoauth/getuserInfo?oauth_token=%s";	
	 private static final String BASE_URL = "http://localhost:9080/store/storeoauth/";
	 static final Logger logger = Logger.getLogger(StoreApi.class);


	  public String getRequestTokenEndpoint() 
	  {
	    return BASE_URL + "request_token";
	  }


	  
	  public String getAccessTokenEndpoint() 
	  {
	    return BASE_URL + "access_token";
	  }
	  	
	  
	  public String getAuthorizationUrl(Token requestToken) 
	  {
		  logger.debug("AUTHORIZATION_URL "+String.format(AUTHORIZATION_URL, requestToken.getToken()));  
		  return String.format(AUTHORIZATION_URL, requestToken.getToken());
	  }
	  
	  
	  public OAuthService createService(OAuthConfig config)
	  {
	    return new OAuth10aServiceImpl(this, config);
	  }


	
	public String getAuthorizationUrl1(Token arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}
}