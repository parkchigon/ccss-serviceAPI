/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Preconditions;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OAuth 2.0 authorization code flow for an installed Java application that persists end-user
 * credentials.
 *
 * <p>
 * Implementation is thread-safe.
 * </p>
 *
 * @since 1.11
 * @author Yaniv Inbar
 */
public class AuthorizationCodeInstalledApp {

  /** Authorization code flow. */
  private final AuthorizationCodeFlow flow;

  /** Verification code receiver. */
  private final VerificationCodeReceiver receiver;

  //private final String CALLBACK_URL = "http://calendar.feelingk.com:9090/springMyTemplate/callback.do";
  private static Logger logger = LoggerFactory.getLogger(AuthorizationCodeInstalledApp.class);
 /* private static final Logger LOGGER =
      Logger.getLogger(AuthorizationCodeInstalledApp.class.getName());*/

  /**
   * @param flow authorization code flow
   * @param receiver verification code receiver
   */
  public AuthorizationCodeInstalledApp(
      AuthorizationCodeFlow flow, VerificationCodeReceiver receiver) {
    this.flow = Preconditions.checkNotNull(flow);
    this.receiver = Preconditions.checkNotNull(receiver);
  }

  /**
   * Authorizes the installed application to access user's protected data.
   *
   * @param userId user ID or {@code null} if not using a persisted credential store
   * @return credential
   */
  public Credential authorize(String userId) throws IOException {
    try {
      Credential credential = flow.loadCredential(userId);
      
      logger.debug("userId:" + userId + ", credential:" + credential);
      
      if( credential != null )
      {
    	  logger.debug("userId:" + userId + ", credential.getRefreshToken():" + credential.getRefreshToken() 
    	  	+ ", credential.getExpiresInSeconds():" + credential.getExpiresInSeconds());
      }
      
      if (credential != null && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() > 60)) {
    	  return credential;
      }
      
      logger.debug("userId:" + userId + "Expired Token or Token Null");
      // open in browser
      String redirectUri = receiver.getRedirectUri();
      AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);
      onAuthorization(authorizationUrl);
      // receive authorization code and exchange it for an access token
      String code = receiver.waitForCode();
      TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
      // store credential and return it
      return flow.createAndStoreCredential(response, userId);
    } finally {
      receiver.stop();
    }
  }
  
  
  /**
   * Authorizes the installed application to access user's protected data.
   *
   * @param userId user ID or {@code null} if not using a persisted credential store
   * @return credential
   */
  public Credential authorizeForService(String userId) throws IOException {
    try {
      Credential credential = flow.loadCredential(userId);
      
      logger.debug("userId:" + userId + ", credential:" + credential);
      
      if( credential != null )
      {
    	  logger.debug("userId:" + userId + ", credential.getRefreshToken():" + credential.getRefreshToken() 
    	  	+ ", credential.getExpiresInSeconds():" + credential.getExpiresInSeconds());
      }
      
      if (credential != null && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() > 60)) {
    	  return credential;
      }
      
      return null;
    } finally {
    }
  }

  /**
   * Authorizes the installed application to access user's protected data.
   *
   * @param userId user ID or {@code null} if not using a persisted credential store
   * @return credential
   */
  public String authorizeForLogin(HashMap<String, String> returnParamMap,String redirectUri) throws IOException {
	  String authorizationUrlString = null;
	  try {
	      AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);
	      authorizationUrlString = authorizationUrl.setState(hashmapToString(returnParamMap)).build();
	      
	      logger.info("returnParamMap:" + returnParamMap + ", authorizationUrlString:" + authorizationUrlString);
	  } catch (Exception ex)
	  {
		  logger.error("Exception", ex);
	  } finally {
	  }
    
    return authorizationUrlString;
  }
  
  public static final String STATE_PARAM_DELIM = "#";
  public static final String KEY_VALUE_DELIM = "=";
  
  public static String hashmapToString(HashMap<String, String> returnParamMap)
  {
	  if( returnParamMap == null || returnParamMap.size() == 0 )
		  return null;
	  
	  StringBuffer sb = new StringBuffer();
	  
	  Iterator<String> iter = returnParamMap.keySet().iterator();
	  
	  while( iter.hasNext() )
	  {
		  String key = (String)iter.next();
		  String value = (String) returnParamMap.get(key);
		  
		  sb.append(key).append(KEY_VALUE_DELIM).append(value).append(STATE_PARAM_DELIM);
	  }
	  
	  String returnString = sb.toString();
	  
	  return returnString.substring(0, returnString.length());
  }
  
  public static HashMap<String, String> stringToHashmap(String returnParam)
  {
	  if( returnParam == null || returnParam.trim().length() == 0 )
		  return null;
	  
	  String[] returnParamArray = returnParam.split(STATE_PARAM_DELIM);
	  
	  if( returnParamArray == null || returnParamArray.length == 0 )
		  return null;
	  
	  HashMap<String, String> returnParamMap = new HashMap<String, String>();
	  for( int i = 0; i < returnParamArray.length; i++ )
	  {
		  //String[] param = returnParamArray[i].split(KEY_VALUE_DELIM);
		  
		  String[] param = new String[2];
		  
		  param[0] = returnParamArray[i].substring(0, returnParamArray[i].indexOf(KEY_VALUE_DELIM));
		  param[1] = returnParamArray[i].substring(returnParamArray[i].indexOf(KEY_VALUE_DELIM)+1);
		  //returnParamArray[i].split(KEY_VALUE_DELIM);
		  returnParamMap.put(param[0], param[1]);
	  }
	  
	  return returnParamMap;
  }
  
  public static void main(String [] args) {
	  String param = "mgrappId=118172602500907017#membId=118083549191995=#sessionId=zIwNDg2MTc4NjY4632ac=";
	  
	  try {
		param = java.net.URLEncoder.encode(param, "UTF-8");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  System.out.println("enc: " + param);
	  
	  //String param = "mgrappId%3D118172602500907017%23membId%3D118083549191995%23%23sessionId%3DzIwNDg2MTc4NjY4632ac%23";
	  try {
		System.out.println("##### " + stringToHashmap(java.net.URLDecoder.decode(param, "UTF-8")));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  /**
   * Authorizes the installed application to access user's protected data.
   *
   * @param userId user ID or {@code null} if not using a persisted credential store
   * @return credential
   */
  public Credential tokenRequest(String userId,String code,String redirectUri) throws IOException {
    try {
      Credential credential = flow.loadCredential(userId);
      
      logger.debug("userId:" + userId + ", credential:" + credential);
      
      if( credential != null )
      {
    	  logger.debug("userId:" + userId + ", credential.getRefreshToken():" + credential.getRefreshToken() 
    	  	+ ", credential.getExpiresInSeconds():" + credential.getExpiresInSeconds());
      }
      
      TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
      
      logger.debug("userId:" + userId + ",  response.getAccessToken():" +  response.getAccessToken() 
      				+ ", response.getRefreshToken():" +  response.getRefreshToken() + ", response.getRefreshToken(): " + response.getTokenType());
     
      // store credential and return it
      return flow.createAndStoreCredential(response, userId);
    } finally {      
    }
  }

  /**
   * Handles user authorization by redirecting to the OAuth 2.0 authorization server.
   *
   * <p>
   * Default implementation is to call {@code browse(authorizationUrl.build())}. Subclasses may
   * override to provide optional parameters such as the recommended state parameter. Sample
   * implementation:
   * </p>
   *
   * <pre>
  &#64;Override
  protected void onAuthorization(AuthorizationCodeRequestUrl authorizationUrl) throws IOException {
    authorizationUrl.setState("xyz");
    super.onAuthorization(authorizationUrl);
  }
   * </pre>
   *
   * @param authorizationUrl authorization URL
   * @throws IOException I/O exception
   */
  protected void onAuthorization(AuthorizationCodeRequestUrl authorizationUrl) throws IOException {
    browse(authorizationUrl.build());
  }

  /**
   * Open a browser at the given URL using {@link Desktop} if available, or alternatively output the
   * URL to {@link System#out} for command-line applications.
   *
   * @param url URL to browse
   */
	public static void browse(String url) {
		Preconditions.checkNotNull(url);
		// Ask user to open in their browser using copy-paste
		logger.debug("Please open the following address in your browser:");
		logger.debug("  " + url);
		// Attempt to open it in the browser
		try {

			logger.debug("Desktop.isDesktopSupported():" + Desktop.isDesktopSupported());
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				
				if (desktop.isSupported(Action.BROWSE)) {
					logger.debug("Attempting to open that address in the default browser now...");
					desktop.browse(URI.create(url));
				}
			}
			/*else
			{
				URL authGoogleUrl = new URL(url);
				URLConnection authGoogleUrlCon = authGoogleUrl.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(authGoogleUrlCon.getInputStream()));
				String inputLine;
				try
				{
						while ((inputLine = in.readLine()) != null) 
							System.out.println(inputLine);
				} catch (Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					if( in != null )
						in.close();
				}
			}*/
		} catch (IOException e) {
			logger.error("Unable to open browser", e);
		} catch (InternalError e) {
			// A bug in a JRE can cause Desktop.isDesktopSupported() to throw an
			// InternalError rather than returning false. The error reads,
			// "Can't connect to X11 window server using ':0.0' as the value of
			// the
			// DISPLAY variable." The exact error message may vary slightly.
			logger.error("Unable to open browser", e);
		}
	}

  /** Returns the authorization code flow. */
  public final AuthorizationCodeFlow getFlow() {
    return flow;
  }

  /** Returns the verification code receiver. */
  public final VerificationCodeReceiver getReceiver() {
    return receiver;
  }
}
