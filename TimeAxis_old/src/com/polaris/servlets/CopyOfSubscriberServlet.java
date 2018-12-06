package com.polaris.servlets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.polaris.store.oauth.RedirectException;
import com.polaris.store.oauth.StoreConsumerApi;

/**
 * Servlet implementation class SubscriberServlet
 */

public class CopyOfSubscriberServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String HELP_USERINFO = "http://localhost:9080/store/storeoauth/get_user_Info";
	private Map<String, String> tokens = new HashMap<String, String>();
	// Content types for Return
	private static final String HTML = "html";
	private static final String XML = "xml";
	private static final String JSON = "json";
	CipherOutputStream cstream = null;
	private byte[] keyBytes = "1ATY34".getBytes(); // example

	private OAuthService service;

	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// OAuth Service, using Scribe
		HttpSession session = req.getSession();
		if (session == null)
		{
			session = req.getSession();
		}
		String clientKey = null;
		String clientSecret = null;
		if (session.getAttribute("clientKey") != null && session.getAttribute("clientSecret") != null)
		{
			clientKey = (String) session.getAttribute("clientKey");
			clientSecret = (String) session.getAttribute("clientSecret");
		} else if (req.getParameter("clientKey") != null && req.getParameter("clientSecret") != null)
		{
			clientKey = (String) req.getParameter("clientKey");
			clientSecret = (String) req.getParameter("clientSecret");
		} else
		{
			clientKey = "E5yCcZlqEh9qTBZAqQcAg";
			clientSecret = "A3P1FdX6mwSY1VF6FXoKq1p9ISpKgYBTb7BiViPeU48";
		}
		// session.setAttribute("clientKey", clientKey);
		// session.setAttribute("clientSecret", clientSecret);
		service = new ServiceBuilder().provider(StoreConsumerApi.class).apiKey(clientKey).apiSecret(clientSecret)
				.callback("http://localhost:9080/store/").debug().build();
		// Fetch stored tokens, if any, from session

		if (session.getAttribute("tokens") != null)
		{
			tokens = (Map<String, String>) session.getAttribute("tokens");
		}
		// Was this called with a page request?
		String requestURL = req.getParameter("help_user");
		if (requestURL == null)
		{
			requestURL = HELP_USERINFO;
		}
		// Let's try things...
		try
		{
			// Get stored tokens if any
			String oAuthToken = tokens.get("oauth_token");
			String oAuthSecret = tokens.get(oAuthToken);

			// ============== GET ACCESS TOKEN =================
			Token accessToken = getAccessToken(oAuthToken, oAuthSecret, session);
			session.setAttribute("clientKey", clientKey);
			session.setAttribute("clientSecret", clientSecret);

			// ============== WE'RE AUTHORIZED. GET CONTENT ===============
			Response response = getContent(requestURL + "?oauth_token=" + tokens.get("oauth_token"), accessToken);

			SecretKey secretkey = new SecretKeySpec(keyBytes, "AES");
			Cipher decipher = Cipher.getInstance("AES");
			decipher.init(Cipher.DECRYPT_MODE, secretkey);
			CipherInputStream cipherInputStream = new CipherInputStream(response.getStream(), decipher);
			ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
			SealedObject sealedObject = (SealedObject) inputStream.readObject();
			Object userdetails = sealedObject.getObject(decipher);
			if (userdetails instanceof store.oauth.AuthenticatedUserDetails)
			{
				//System.out.println(((store.oauth.AuthenticatedUserDetails) userdetails).getUsername());
				//System.out.println(((AuthenticatedUserDetails) userdetails).getUserid());
			}

			// ============== WE HAVE RESPONSE, RETURN TO BROWSER =============
			// createHTMLResponse(resp,response, XML);

		} catch (Exception e)
		{
			if (e instanceof RedirectException)
			{
				RedirectException redirect = (RedirectException) e;
				String targetURL = redirect.url();
				if (targetURL != null)
				{
					resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					resp.setHeader("Location", targetURL);
				}
			} else if (e instanceof IOException)
			{
				throw (IOException) e;
			} else if (e instanceof IllegalArgumentException)
			{
				throw (IllegalArgumentException) e;
			}
		}
	}

	// ============== END MAIN SERVICE CALL ==================

	/*
	 * Helper function to quickly get DOM document from XML string
	 */
	public static Document loadXMLFromString(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	/*
	 * Get the Access Token for signing requests. If we don't yet have it, go and get it from the OAuth Server, else
	 * from the session
	 */
	private Token getAccessToken(String oAuthToken, String oAuthSecret, HttpSession session)
	{
		Token accessToken;
		// ========= NOT AUTHORIZED YET, DO SO =============
		if ((oAuthToken == null || oAuthToken.isEmpty()) || (oAuthSecret == null || oAuthSecret.isEmpty()))
		{
			System.out.println("Not Authorized, go and get those tokens");
			/*
			 * Consumer accesses protected resources by submitting OAuth signed requests for resources using its
			 * consumer key, an empty access token, and signs the request with the consumer secret and an empty access
			 * token secret.
			 */
			// Get request Token (Pre authorized)
			Token requestToken = service.getRequestToken();
			// Use empty verifier
			Verifier verifier = new Verifier("");

			// Get access token using our pre authorized request token
			accessToken = service.getAccessToken(requestToken, verifier);

			// We have tokens now, so store them.
			tokens.put("oauth_token", requestToken.getToken());
			tokens.put(accessToken.getToken(), accessToken.getSecret());

			// Store the tokens back in the session
			session.setAttribute("tokens", tokens);
		} else
		{ // ============ HAVE ACCESS TOKEN ALREADY ==========
			System.out.println("Am Authorized: Using existing token");
			accessToken = new Token(oAuthToken, oAuthSecret);
		}
		return accessToken;
	}

	/*
	 * Provided with a url and an access token, this will go and get the content you're after.
	 */
	private Response getContent(String url, Token accessToken)
	{
		OAuthRequest request = new OAuthRequest(Verb.POST, url);
		service.signRequest(accessToken, request);
		Response response = request.send();
		return response;
	}

	public static <T> T parseObjectFromString(String s, Class<T> clazz) throws Exception
	{
		return clazz.getConstructor(new Class[]
		{ String.class }).newInstance(s);
	}
}
