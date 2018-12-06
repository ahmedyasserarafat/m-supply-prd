package com.polaris.servlets;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.polaris.canvas.common.ENTRYLevel;
import com.polaris.canvas.common.EXITLevel;
import com.polaris.canvas.common.PerformanceTimer;
import com.polaris.canvas.common.StringUtils;
import com.polaris.canvas.common.Utility;
import com.polaris.canvas.properties.reader.PropertyReader;
import com.polaris.store.oauth.ConsumerKeys;



public class OAuthCallbackServlet extends HttpServlet { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String XML = "XML";
	private static final String HTML = "HTML";
	private final PropertyReader mReader = new PropertyReader("config");
	static final Logger logger = Logger.getLogger(OAuthCallbackServlet.class);
	
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

	@Override 
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
      throws IOException, ServletException {
		String cmName = "OAuthCallbackServlet";
		logger.log(ENTRYLevel.ENTRY, "Entered into "+cmName);
		PerformanceTimer serv= new PerformanceTimer();
		try
	  	{
		serv.startTimer(cmName);
		
		String referer = req.getHeader("referer");
		
		if(!urlList.contains(referer)){
			throw new IOException("Invalid referer");
		}
		
      
      //OK the user have consented so lets find out about the user 
      HttpSession sess = req.getSession(); 
      OAuthService service = (OAuthService)sess.getAttribute("oauthService");
      //Get the all important authorization code 
      String code = req.getParameter("oauth_verifier"); 

		logger.log(ENTRYLevel.INFO,"oauth_verifier=" +code);
      //Construct the access token 
      Token authorizedToken=new Token(req.getParameter(OAuthConstants.TOKEN ),req.getParameter(OAuthConstants.TOKEN_SECRET));
      logger.debug("authorizedToken--"+authorizedToken);
      Token token = service.getAccessToken(authorizedToken, new Verifier(OAuthConstants.VERIFIER)); 
      logger.debug("AccessToken--"+token);
      //Save the token for the duration of the session 
      sess.setAttribute("token", token);
    
    HashMap JsonToMap = null;
  	OAuthRequest request = new OAuthRequest(Verb.POST, mReader.retrieveProperty("USERINFO"));
	service.signRequest(token, request);
	 logger.debug("User Servive URL--"+ mReader.retrieveProperty("USERINFO"));
	 logger.debug("Signing Request--"+token+ " request"+request);
	Response response = request.send();
	
		  
		  String jsondata = response.getBody();
		  JSONParser parser=new JSONParser();
		  Cipher cipher = Cipher.getInstance("AES");
		  String keyFilePath=mReader.retrieveProperty("DER_PATH");
		  ConsumerKeys keys=new ConsumerKeys();
		  SecretKeySpec secretKey=keys.getEncryptionSecretKey(keyFilePath);
		 
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] textdecrypted = cipher.doFinal(Base64.decodeBase64(jsondata.getBytes("UTF-8")));
		   JsonToMap = (HashMap)parser.parse(new String(textdecrypted));
		   logger.log(ENTRYLevel.INFO,"JsonToMap=" +JsonToMap);
		  sess.setAttribute("USERINFO", JsonToMap);
	  	} catch (Exception e)
		{
		
		logger.error("Caught Generic Exception in " +cmName+"--"+e.getMessage(), e);
		logger.fatal("Caught Generic Exception in " +cmName+"--"+ e.getMessage(), e);
		Utility.getPrintStackTrace(e);
		}finally{
			 	serv.endTimer();
				logger.log(EXITLevel.EXIT, "Exited "+cmName);					
		}
	  resp.sendRedirect(req.getContextPath()+mReader.retrieveProperty("REDIRECT_URL"));
	 
	
	
	//return response;
   
     
   } 
	
	
	/*
	 * Helper function to quickly get DOM document from XML string
	 */
	public static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	
	/*
	 * Create the response to the browser.
	 * Expects XML as the response format received to it.
	 * Can return to the client in XML, HTML or JSON format
	 * 
	 */
	private void createHTMLResponse(HttpServletResponse httpResp, Response oAuthResp, String responseFormat) throws IOException,Exception{		
		if(responseFormat.equals(XML)){
			httpResp.setContentType("text/xml");
			httpResp.getWriter().println(oAuthResp.getBody());		
		}		
		else if(responseFormat.equals(HTML)){
			httpResp.setContentType("text/html");		
			httpResp.getWriter().println("<html><body>");
			//Get portions of the response using XPath, the easiest way to get what we want
			Document doc = loadXMLFromString(oAuthResp.getBody()); 
			XPathFactory xpathFact = XPathFactory.newInstance(); 
			XPath xpath = xpathFact.newXPath();


			Boolean arrayResult = (Boolean)xpath.evaluate("/result/@is_array",doc,XPathConstants.BOOLEAN);
			System.out.println("RESULT IS ARRAY? "+arrayResult);
			if(arrayResult){


			}
			else{
				String htmlOut = (String)xpath.evaluate("/result/body/und/item/value/text()",doc,XPathConstants.STRING);			
				httpResp.getWriter().println(htmlOut);				
			}			
			httpResp.getWriter().println("</html></body>");
		}
		else{//Else JSON
			httpResp.setContentType("text/json");
			//Convert XML to JSON... righto... will get right on that.
			httpResp.getWriter().println(oAuthResp.getBody());	
		}
	}
	

}

