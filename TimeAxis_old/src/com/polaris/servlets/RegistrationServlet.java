package com.polaris.servlets;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.scribe.exceptions.OAuthException;
import org.scribe.extractors.BaseStringExtractorImpl;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.scribe.services.HMACSha1SignatureService;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

import com.polaris.canvas.common.LogWriterFactory;
import com.polaris.canvas.common.Logger;
import com.polaris.canvas.common.PerformanceTimer;
import com.polaris.canvas.common.Utility;
import com.polaris.canvas.properties.reader.PropertyReader;
import com.polaris.store.oauth.ConsumerKeys;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PropertyReader mReader = new PropertyReader("config");
	private  static Logger logger=LogWriterFactory.getLogWriter("RegistrationServlet");

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmName = "Registration Service";
		Response response=null;
		logger.logEntry(cmName, "Entered in to method");
		PerformanceTimer serv= new PerformanceTimer();
		serv.startTimer(cmName);
		OAuthRequest request=null;
		String url = mReader.retrieveProperty("USERSTATUSUPDATE_PATH");
		HttpSession sess=null;
		
		 resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	     resp.setHeader("Pragma", "no-cache");
	     resp.setDateHeader("Expires", 0);

		logger.logDebug("USERSTATUSUPDATE_PATH--",url);
		// String fn = req.getParameter("firstname");
		String userInfo = "{\"FIRST_NAME\":\""+req.getParameter("firstname")+"\",\"PANCARD_NUMBER\":\""+req.getParameter("pannumber")+"\",\"MOBILE_NUM\":\""+req.getParameter("mobile")+"\",\"EMAIL_ID\":\""+req.getParameter("email")+"\",\"CARD_NUM\":\"12345678\",\"CVV_NUM\":\"565\",\"MONTH\":\"05\",\"YEAR\":\"24\"}";
		//String userInfo = "{\"firstname\":\"Ahmed\",\"lastName\":\"Arafat\",\"dob\":\"23/06/2008\",\"address\":{\"streetAddress\":\"21 2nd street\",\"city\":\"New York\",\"state\":\"NY\",\"postCode\":10021},\"phoneNumbers\":[\"212 732-1234\",\"646 123-4567\",{\"mobileNumbers\":[\"0987654321\", \"9876543210\"]},{\"FaxNumber\":\"123 456 789\"}]}";
		//String userInfo = "{FIRST_NAME:Arafat, PANNUMBER:ACJPY7059A,CARD_NUM:{12345678,CVV_NUM:565},PANNUMBER:ACJPY7059A,CARD_NUM:{12345678,CVV_NUM:565},PANNUMBER:ACJPY7059A,CARD_NUM:{12345678,CVV_NUM:565},PANNUMBER:ACJPY7059A,CARD_NUM:{12345678,CVV_NUM:565},PANNUMBER:ACJPY7059A,CARD_NUM:{12345678,CVV_NUM:565}}";
		
		logger.logDebug("userInfo--",userInfo);
		try{
			request = new OAuthRequest(Verb.POST, url);
			sess=req.getSession();
			ConsumerKeys keys=new ConsumerKeys();
			String keyFilePath=mReader.retrieveProperty("DER_PATH");
			SecretKeySpec secretKey=keys.getEncryptionSecretKey(keyFilePath);
			byte[] byteCipherText = keys.encryptData(userInfo.getBytes(),secretKey,"AES");
			//String strCipherText = new BASE64Encoder().encode(byteCipherText);
			String strCipherText = new String(new Base64().encode(byteCipherText));

			// Specify the Keystore where the Receivers certificate has been imported
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			char [] password = "canvas".toCharArray();
			java.io.FileInputStream fis = new java.io.FileInputStream(mReader.retrieveProperty("KEYSTORE_PATH"));
			ks.load(fis, password);
			fis.close();

			X509Certificate recvcert ;
			MessageDigest md = MessageDigest.getInstance("MD5");
			recvcert = (X509Certificate)ks.getCertificate("recv");
			// Getting the Receivers public Key from the Certificate
			PublicKey pubKeyReceiver = recvcert.getPublicKey();


			//Encrypting the SecretKey with the Receivers public Key
			byte[] byteEncryptWithPublicKey = keys.encryptData(secretKey.getEncoded(),pubKeyReceiver,"RSA/ECB/PKCS1Padding");
			//String strSenbyteEncryptWithPublicKey = new BASE64Encoder().encode(byteEncryptWithPublicKey);
			String strSenbyteEncryptWithPublicKey = new String(new Base64().encode(byteEncryptWithPublicKey));

			// Create a Message Digest of the Data to be transmitted
			md.update(userInfo.getBytes());
			byte byteMDofDataToTransmit[] = md.digest();

			String strMDofDataToTransmit = new String();
			for (int i = 0; i < byteMDofDataToTransmit.length; i++){
				strMDofDataToTransmit = strMDofDataToTransmit + Integer.toHexString(byteMDofDataToTransmit[i] & 0xFF) ;
			}

			// 3.1 Message to be Signed = Encrypted Secret Key + MAC of the data to be transmitted
			String strMsgToSign = strSenbyteEncryptWithPublicKey + "|" + strMDofDataToTransmit;

			char[] keypassword = "regissend".toCharArray();
			Key myKey =  ks.getKey("sender", keypassword);
			PrivateKey myPrivateKey = (PrivateKey)myKey;

			// 4.2 Sign the message
			Signature mySign = Signature.getInstance("MD5withRSA");
			mySign.initSign(myPrivateKey);
			mySign.update(strMsgToSign.getBytes());
			byte[] byteSignedData = mySign.sign();



			Token t=(Token)sess.getAttribute("token");
			request.addQuerystringParameter("USER_INFO",strCipherText);
			request.addQuerystringParameter(OAuthConstants.TOKEN, t.getToken());
			//request.addQuerystringParameter("SIGNATURE", new BASE64Encoder().encode(byteSignedData));
			request.addQuerystringParameter("SIGNATURE",  new String(new Base64().encode(byteSignedData)));
			request.addQuerystringParameter("strMsgToSign", strMsgToSign);
			//req.adsetAttribute("sealedMap", sealedMap);
			logger.logInfo("Token", t.getToken()+" TokenSecret "+t.getSecret());
			logger.logInfo("QueryStringParams", request.getQueryStringParams().toString());

			response = request.send();
			String responseBody=response.getBody();

			Map extracted=extract(responseBody);

			if(extracted.get("SIGNATURE")!=null && extracted.get("STATUS")!=null && !"".equals(extracted.get("SIGNATURE")) && !"".equals(extracted.get("STATUS"))){
				String consumerSecret = (String)sess.getAttribute("consumersecret");
				String requestSignature=getSignature(OAuthEncoder.encode(consumerSecret),OAuthEncoder.encode(t.getSecret()));
				logger.logInfo("requestSignature", requestSignature);
				logger.logInfo("responseSignature", extracted.get("SIGNATURE")+"");
				if(requestSignature.toString().equals(extracted.get("SIGNATURE").toString()) && "true".equals(extracted.get("STATUS"))){
					sess.setAttribute("USERINFO", "");
					resp.sendRedirect(mReader.retrieveProperty("PAYMENT_URL")); 
				}
			}



		}
		catch (Exception e)
		{
			logger.logError(cmName,Utility.getPrintStackTrace(e));
			Utility.getPrintStackTrace(e);
		}
		finally{
			serv.endTimer();
			logger.logExit(cmName, "Exited from the method");
			logger.removeFromMDC();					
		}



	}

	private Map extract(String response)
	{
		Pattern SIGN_REGEX = Pattern.compile("oauth_signature=([^&]+)");
		Pattern STATUS_REGEX = Pattern.compile("oauth_status=([^&]*)");
		Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");
		String sign = extract(response, SIGN_REGEX);
		String status = extract(response, STATUS_REGEX);
		logger.logInfo("extractedResponseMapsign", sign);
		logger.logInfo("extractedResponseMapstatus", status);
		Map extractedStatus=new HashMap();
		extractedStatus.put("SIGNATURE", sign);
		extractedStatus.put("STATUS", status);
		return extractedStatus;
	}

	private String extract(String response, Pattern p)
	{
		Matcher matcher = p.matcher(response);
		if (matcher.find() && matcher.groupCount() >= 1)
		{
			return matcher.group(1);
		}
		else
		{
			throw new OAuthException("Response body is incorrect. Can't extract token and secret from this: '" + response + "'", null);
		}
	}

	private String getSignature(String consumerSecret,String tokenSecret) throws Exception
	{
		SecretKeySpec key = new SecretKeySpec(tokenSecret.getBytes("UTF-8"), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(key);
		byte[] bytes = mac.doFinal(consumerSecret.getBytes("UTF-8"));
		return new String(Base64.encodeBase64(bytes)).replace("\r\n", "");
	}

}
