/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.softlysoftware.jxero;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;
import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import net.oauth.OAuth;
import net.oauth.OAuthException;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.signature.RSA_SHA1;
import com.softlysoftware.jxero.core.Contact;
import com.softlysoftware.jxero.core.Invoice;
import com.softlysoftware.jxero.core.Address;
import com.softlysoftware.jxero.core.Phone;
import org.apache.commons.io.FileUtils;

/**
* The main point of interaction with the package. Create one of these with your OAuth credentials and you
* will be free to use the simple get/post methods to work direct with your Xero business logic objects.
*/
public class XeroClient {

	protected OAuthAccessor oAuthAccessor = null;
	public OAuthAccessor getOAuthAccessor() {return oAuthAccessor;}

	private XeroClient() {}

	/**
	* To create a client this way, you just need the three strings that encapsulate your OAuth
	* inteactions with the Xero API.
	* See <a href="http://developer.xero.com/documentation/getting-started/private-applications/">the Xero guide to setting up a private application</a> 
	* for details on how to get your credentials.
	*/
	public XeroClient(String consumerKey, String consumerSecret, String privateKey) {
		setupOAuth(consumerKey, consumerSecret, privateKey);
	}

	/**
	* The first two parameters are simple Strings, but the privateKey is a bigger chunk of text. 
	* Use this contstuctor to grab a pem file from your filesystem somewhere.
	*/
	public XeroClient(String consumerKey, String consumerSecret, File privateKeyFile) {
		try {
			String privateKey =  FileUtils.readFileToString(privateKeyFile, "ISO-8859-1");
			setupOAuth(consumerKey, consumerSecret, privateKey);
		}
		catch (IOException e) {
			throw new RuntimeException("Problem tracking down your private key file.", e);
		}
	}

	/**
	* You can also include your credentials at runtime and use this constructor.
	* Note, if this can't find both a jxero.aouth.properties and jxero.oauth.pem on the classpath,
	* this constructor will throw an exception.
	*/
	public XeroClient(boolean iPromiseToIncludeTheFilesOnTheClasspath) {
		if (!iPromiseToIncludeTheFilesOnTheClasspath) throw new RuntimeException("No soup for you.");
		try {
			Properties properties = new Properties();
			InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream("jxero.oauth.properties");
			if (propertiesStream == null) throw new RuntimeException("Couldn't find the jxero.oauth.properties file on classpath.");
			properties.load(propertiesStream);
			propertiesStream.close();
			String consumerKey = properties.getProperty("jxero.oauth.consumer.key");
			String consumerSecret = properties.getProperty("jxero.oauth.consumer.secret");
			if (consumerKey == null || consumerKey.equals("YOUR-CONSUMER-KEY") || consumerSecret == null || consumerSecret.equals("YOUR-CONSUMER-SECRET")) {
				throw new RuntimeException("Found the jxero.oauth.properties file, but didn't spot your key and secret in it.");
			}
			String privateKey = "";
			// two ways to pass in the private key
			if (properties.getProperty("jxero.oauth.pem.file") != null) {
				// state the location in the properties file
				privateKey = FileUtils.readFileToString(new File(properties.getProperty("jxero.oauth.pem.file")), "ISO-8859-1");
			}
			else {
				// or the correctly named file on the classpath
				InputStream in = this.getClass().getClassLoader().getResourceAsStream("jxero.oauth.pem");
				if (in == null) throw new RuntimeException("Couldn't find the jxero.oauth.pem file on classpath.");
				String line = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				while ((line = reader.readLine()) != null) privateKey = privateKey + line + "\n";
				reader.close();
			}
			setupOAuth(consumerKey, consumerSecret, privateKey);
		}
		catch (IOException e) {
			throw new RuntimeException("Problem tracking down the OAuth credentials from file from classpath", e);
		}
	}

	private void setupOAuth(String consumerKey, String consumerSecret, String privateKey) {
		OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, null, null);
		consumer.setProperty(RSA_SHA1.PRIVATE_KEY, privateKey);
		consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.RSA_SHA1);
		oAuthAccessor = new OAuthAccessor(consumer);
		oAuthAccessor.accessToken = consumerKey;
		oAuthAccessor.tokenSecret = consumerSecret;
	}

	/**
	* Convenience method - just instantiates a new ContactsEndpoint using this.
	*/
	public ContactsEndpoint contacts() {return new ContactsEndpoint(this);}

	/**
	* Convenience method - just instantiates a new InvoicesEndpoint using this.
	*/
	public InvoicesEndpoint invoices() {return new InvoicesEndpoint(this);}

	/**
	* Convenience method - just instantiates a new PaymentsEndpoint using this.
	*/
	public PaymentsEndpoint payments() {return new PaymentsEndpoint(this);}
	
	
	/**
	* Convenience method - just instantiates a new ManualJournalsEndpoint using this.
	*/
	public ManualJournalsEndpoint manualJournals() {return new ManualJournalsEndpoint(this);}	

}