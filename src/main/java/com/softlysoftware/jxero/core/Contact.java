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
package com.softlysoftware.jxero.core;

import java.util.List;
import java.util.LinkedList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
* Xero's <a href="http://developer.xero.com/documentation/api/contacts/">Contact record</a>.
*/
@XmlRootElement(name = "Contact")
@XmlAccessorType(XmlAccessType.NONE)
public class Contact {

	@XmlElement(name = "ContactID")
	private String contactID;
	public String getContactID(){return contactID;}
	public void setContactID(String contactID){this.contactID = contactID;}

	@XmlElement(name = "ContactNumber")
	private String contactNumber;
	public String getContactNumber(){return contactNumber;}
	public void setContactNumber(String contactNumber){this.contactNumber = contactNumber;}

	public enum Status {ACTIVE, DELETED};

	@XmlElement(name = "ContactStatus")
	private String status;
	public Status getStatus(){
		if (status == null) return null;
		if (status.equals("ACTIVE")) return Status.ACTIVE;
		if (status.equals("DELETED")) return Status.DELETED;
		throw new RuntimeException("Bad status : " + status);
	}
	public void setStatus(Status status){this.status = status.toString();}

	@XmlElement(name = "Name")
	private String name;
	public String getName(){return name;}
	public void setName(String name){this.name = name;}

	@XmlElement(name = "FirstName")
	private String firstName;
	public String getFirstName(){return firstName;}
	public void setFirstName(String firstName){this.firstName = firstName;}

	@XmlElement(name = "LastName")
	private String lastName;
	public String getLastName(){return lastName;}
	public void setLastName(String lastName){this.lastName = lastName;}

	@XmlElement(name = "EmailAddress")
	private String emailAddress;
	public String getEmailAddress(){return emailAddress;}
	public void setEmailAddress(String emailAddress){this.emailAddress = emailAddress;}

	@XmlElement(name = "SkypeUserName")
	private String skypeUserName;
	public String getSkypeUserName(){return skypeUserName;}
	public void setSkypeUserName(String skypeUserName){this.skypeUserName = skypeUserName;}

	@XmlElement(name = "BankAccountDetails")
	private String bankAccountDetails;
	public String getBankAccountDetails(){return bankAccountDetails;}
	public void setBankAccountDetails(String bankAccountDetails){this.bankAccountDetails = bankAccountDetails;}

	@XmlElement(name = "TaxNumber")
	private String taxNumber;
	public String getTaxNumber(){return taxNumber;}
	public void setTaxNumber(String taxNumber){this.taxNumber = taxNumber;}

	@XmlElement(name = "AccountsReceivableTaxType")
	private String accountsReceivableTypeType;
	public String getAccountsReceivableTaxType(){return accountsReceivableTypeType;}
	public void setAccountsReceivableTaxType(String accountsReceivableTypeType){this.accountsReceivableTypeType = accountsReceivableTypeType;}

	@XmlElement(name = "AccountsPayableTaxType")
	private String accountsPayableTaxType;
	public String getAccountsPayableTaxType(){return accountsPayableTaxType;}
	public void setAccountsPayableTaxType(String accountsPayableTaxType){this.accountsPayableTaxType = accountsPayableTaxType;}
	
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	private List<Address> addresses = new LinkedList<Address>();
	public List<Address> getAddresses() {return addresses;}
	public void setAddresses(List<Address> addresses) {this.addresses = addresses;}

	@XmlElementWrapper(name = "Phones")
	@XmlElement(name = "Phone")
	private List<Phone> phones = new LinkedList<Phone>();
	public List<Phone> getPhones(){return phones;}
	public void setPhones(List<Phone> phones){this.phones = phones;}

	@XmlElement(name = "UpdatedDateUTC")
	private String updatedDateUTC;
	public String getUpdatedDateUTC(){return updatedDateUTC;}
	public void setUpdatedDateUTC(String updatedDateUTC){this.updatedDateUTC = updatedDateUTC;}

	@XmlElement(name = "IsSupplier")
	private String isSupplier;
	public boolean getIsSupplier(){return isSupplier != null && isSupplier.equals("true");}
	public void setIsSupplier(boolean isSupplier){this.isSupplier = isSupplier+"";}

	@XmlElement(name = "IsCustomer")
	private String isCustomer;
	public boolean getIsCustomer(){return isCustomer != null && isCustomer.equals("true");}
	public void setIsCustomer(boolean isCustomer){this.isCustomer = isCustomer+"";}

	@XmlElement(name = "DefaultCurrency")
	private String defaultCurrency;
	public String getDefaultCurrency(){return defaultCurrency;}
	public void setDefaultCurrency(String defaultCurrency){this.defaultCurrency = defaultCurrency;}

}