package com.polaris.store.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.itextpdf.xmp.impl.Base64;
import com.polaris.canvas.common.Utility;
import com.polaris.canvas.properties.reader.PropertyReader;

public class ConsumerKeys
{
	private final static PropertyReader mReader = new PropertyReader("config");

	private final static String  algorithm = "RSA";

	private final static String  encryptionAlgorithm = "AES";
	
	static final Logger logger = Logger.getLogger(ConsumerKeys.class);
	
	
	private static String strHexVal = "0123456789abcdef";

	public  PrivateKey getSymmetrickey(String consumerIdentity) {
		PrivateKey loadKey=null;
		String keyPath=mReader.retrieveProperty("DER_PATH");
		logger.debug("PrivatekeyPath "+keyPath);
		if(keyPath!=null && !"".equals(keyPath)){

			loadKey = getPrivateKey(keyPath);
			

		}
		return loadKey;
	}


	public SecretKeySpec getEncryptionSecretKey(String consumerIdentity) {
		SecretKeySpec keySpec=null;
		String keyPath=mReader.retrieveProperty("DER_PATH");
		logger.debug("SecretKeySpec "+keyPath);
		if(keyPath!=null && !"".equals(keyPath)){

			keySpec=  LoadSecretKey(keyPath);
		

		}	
		return keySpec;
	}
	private SecretKeySpec LoadSecretKey(String path) {
		FileInputStream fis=null;
		File key=null;
		logger.debug("Entered to LoadSecretKey Method");
		try{
			key = new File(path +"/key.der");
			fis = new FileInputStream(path + "/key.der");
			byte[] encodedKey = new byte[(int) key.length()];
			fis.read(encodedKey);

			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
					encodedKey);

			byte keyBytes[]=keySpec.getEncoded();
			return new SecretKeySpec(keyBytes, 0, keyBytes.length, encryptionAlgorithm);
		}
		catch (Exception e)
		{
			logger.error("Caught Exception in SecretKey Fetching--"+e.getMessage(), e);
			Utility.getPrintStackTrace(e);
			return null;
		} 
		finally
		{
			try
			{

				if(fis!=null)fis.close();
			} catch (IOException e)
			{
				// Ignore this excetion
			}

		}

	}

	private PrivateKey getPrivateKey(String path) {
		FileInputStream fisPrivate=null;
		File filePrivateKey=null;
		PrivateKey privateKey=null;
		logger.debug("Entered to retreive Privatekey");

		try{
			// Read Public Key.
			filePrivateKey = new File(path + "private.der");
			fisPrivate = new FileInputStream(path +"private.der");
			byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
			fisPrivate.read(encodedPrivateKey);



			// Generate KeyPair.
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
					encodedPrivateKey);
			privateKey = keyFactory.generatePrivate(privateKeySpec);

			

		}
		catch (Exception e)
		{
			logger.error("Caught Exception in " +privateKey+"--"+e.getMessage(), e);
			Utility.getPrintStackTrace(e);
			return extracted();
		} 

		finally
		{
			
			try
			{
				if(fisPrivate!=null)fisPrivate.close();

			} catch (IOException e)
			{
				// Ignore this excetion
			}

		}
		return privateKey;

	}

	private PrivateKey extracted()
	{
		return null;
	}
	
	public byte[] encryptData(byte[] byteDataToEncrypt, Key secretKey, String Algorithm) {
		byte[] byteCipherText = new byte[200];
		logger.debug("Entered to encryptData method");
		try {
		Cipher aesCipher = Cipher.getInstance(Algorithm);
	
	/**
	 *  Step 3. Initialize the Cipher for Encryption 
	 */
		if(Algorithm.equals("AES")){
			aesCipher.init(Cipher.ENCRYPT_MODE,secretKey,aesCipher.getParameters());
			}
			else if(Algorithm.equals("RSA/ECB/PKCS1Padding")){
			aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
			} 
			
	/**
	 *  Step 4. Encrypt the Data
	 *  		1. Declare / Initialize the Data. Here the data is of type String
	 *  		2. Convert the Input Text to Bytes
	 *  		3. Encrypt the bytes using doFinal method 
	 */
	byteCipherText = aesCipher.doFinal(byteDataToEncrypt); 
	//strCipherText = new BASE64Encoder().encode(byteCipherText);

		}
		
		
				catch (Exception e)
				{
					logger.error("Caught Exception in encryptData--"+e.getMessage(), e);
					Utility.getPrintStackTrace(e);
				}
				
	return byteCipherText;
	}
	/**
	 *  Step 5. Decrypt the Data
	 *  		1. Initialize the Cipher for Decryption 
	 *  		2. Decrypt the cipher bytes using doFinal method 
	 */
	
	public byte[] decryptData(byte[] byteCipherText, Key secretKey, String Algorithm) {
		byte[] byteDecryptedText = new byte[200];
		logger.debug("Entered to decryptData method");			
		try{	
	Cipher aesCipher = Cipher.getInstance(Algorithm);
	if(Algorithm.equals("AES")){
	aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());
	}
	else if(Algorithm.equals("RSA/ECB/PKCS1Padding")){
	aesCipher.init(Cipher.DECRYPT_MODE,secretKey);
	} 
	
	byteDecryptedText = aesCipher.doFinal(byteCipherText);
	//strDecryptedText = new String(byteDecryptedText);
		}
	
	catch (NoSuchAlgorithmException noSuchAlgo)
	{
		System.out.println(" No Such Algorithm exists " + noSuchAlgo);
	}
	
		
			
			catch (Exception e)
			{
				logger.error("Caught Exception in encryptData--"+e.getMessage(), e);
				Utility.getPrintStackTrace(e);
			}

	return byteDecryptedText;
	}
	
	
	public static byte[] convertStringToByteArray(String strInput) {
		strInput = strInput.toLowerCase();
		byte[] byteConverted = new byte[(strInput.length() + 1) / 2];
		int j = 0;
		int interimVal;
		int nibble = -1;

		for (int i = 0; i < strInput.length(); ++i) {
			interimVal = strHexVal.indexOf(strInput.charAt(i));
			if (interimVal >= 0) {
				if (nibble < 0) {
					nibble = interimVal;
				} else {
					byteConverted[j++] = (byte) ((nibble << 4) + interimVal);
					nibble = -1;
				}
			}
		}

		if (nibble >= 0) {
			byteConverted[j++] = (byte) (nibble << 4);
		}

		if (j < byteConverted.length) {
			byte[] byteTemp = new byte[j];
			System.arraycopy(byteConverted, 0, byteTemp, 0, j);
			byteConverted = byteTemp;
		}

		return byteConverted;
	}
	
	public static String convertByteArrayToString(byte[] block) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < block.length; ++i) {
			buf.append(strHexVal.charAt((block[i] >>> 4) & 0xf));
			buf.append(strHexVal.charAt(block[i] & 0xf));
		}

		return buf.toString();
	}
}
