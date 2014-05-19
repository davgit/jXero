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

import java.io.UnsupportedEncodingException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* Corresponds to the root element on the XML repsonses to API calls.
*/
@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.NONE)
public class Response extends Wrapper {

	private static Log log = LogFactory.getLog("jxero.response");

	@XmlElement(name = "Id")
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	private String id;

	@XmlElement(name = "Statis")
	private String status;
	public String getStatis(){return status;}
	public void setStatis(String status){this.status = status;}

	@XmlElement(name = "ProviderName")
	private String providerName;
	public String getProviderName(){return providerName;}
	public void setProviderName(String providerName){this.providerName = providerName;}

	@XmlElement(name = "DateTimeUTC")
	private String dateTimeUTC;
	public String getDateTimeUTC(){return dateTimeUTC;}
	public void setDateTimeUTC(String dateTimeUTC){this.dateTimeUTC = dateTimeUTC;}

	@XmlElement(name = "Contacts")
	public ContactsEndpoint getContactsEndpoint() {return contactsEndpoint;}
	public void setContactsEndpoint(ContactsEndpoint contactsEndpoint) {this.contactsEndpoint = contactsEndpoint;}
	private ContactsEndpoint contactsEndpoint;
	
	
	@XmlElement(name = "ManualJournals")	
	public ManualJournalsEndpoint getManualJournalsEndpoint() {return manualJournalsEndpoint;}
	public void setManualJournalsEndpoint(ManualJournalsEndpoint manualJournalsEndpoint) {this.manualJournalsEndpoint = manualJournalsEndpoint;}
	private ManualJournalsEndpoint manualJournalsEndpoint;
	
	@XmlElement(name = "Invoices")
	public InvoicesEndpoint getInvoicesEndpoint(){return invoicesEndpoint;}
	public void setInvoicesEndpoint(InvoicesEndpoint invoicesEndpoint){this.invoicesEndpoint = invoicesEndpoint;}
	private InvoicesEndpoint invoicesEndpoint;

	@XmlElement(name = "Payments")
	public PaymentsEndpoint getPaymentsEndpoint(){return paymentsEndpoint;}
	public void setPaymentsEndpoint(PaymentsEndpoint paymentsEndpoint){this.paymentsEndpoint = paymentsEndpoint;}
	private PaymentsEndpoint paymentsEndpoint;

	public static Response getResponse(byte[] message) {
		try {
			String xml = new String(message, "UTF-8");
			log.trace("Parsing XML : \n" + xml);
			return (Response)Xml.fromXml(xml, Response.class);
		}
		catch (UnsupportedEncodingException uee) {throw new RuntimeException(uee);}
	}

}