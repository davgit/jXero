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

import java.util.List;
import java.util.LinkedList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import com.softlysoftware.jxero.core.Contact;
import com.softlysoftware.jxero.XeroClient;

/**
* A simple wrapper for the list of Contact objects, to give the correct structure to the XML files.
*/
@XmlRootElement(name = "Contacts")
@XmlAccessorType(XmlAccessType.NONE)
public class ManualJournalEndpoint extends Endpoint {

	private ManualJournalEndpoint() { }

	public ManualJournalEndpoint(XeroClient xeroClient) {this.xeroClient = xeroClient;}

	public String getRootElementName() {
		return "Contacts";
	}

	/**
	* When working with this wrapper directly, add the subordiate Contact objects to this list.
	*/
	@XmlElement(name = "Contact")
	public List<Contact> getList(){return list;}
	public void setList(List<Contact> list){this.list = list;}
	private List<Contact> list = new LinkedList<Contact>();

	/**
	* The Xero maintained unique identifier. Will throw an exception if no match is found.
	*/
	public Contact getById(String id) {
		Response response = get(id, null);
		return response.getContactsEndpoint().list.get(0);
	}

	/**
	* Grab contacts using any "where" filter. For example, getContactsWhere("FullyPaidOnDate >= DateTime(2011, 10, 01) AND FullyPaidOnDate <= DateTime(2011, 10, 30)");
	* See <a href="http://developer.xero.com/documentation/getting-started/http-requests-and-responses/">the Xero documentation</a> for full details.
	*/
	public List<Contact> getContactsWhere(String where) {
		Response response = getWhere(where);
		if (response.getContactsEndpoint() == null) return new LinkedList<Contact>();
		return response.getContactsEndpoint().getList();
	}

	/**
	* Like the generalised getContactsWhere, but for convenience when you expect just one object to be returned in the set.
	* Throws an exception when there's more than one match.
	* @return If no match is found, returns null.
	*/
	public Contact getContactWhere(String where) {
		List<Contact> contacts = getContactsWhere(where);
		if (contacts.size() == 0) return null;
		if (contacts.size() > 1) throw new RuntimeException("Multiple (" + contacts.size() + ") contacts matched : " + where);
		return contacts.get(0);
	}

	/**
	* This is the unqiue identifier on your side.
	*/
	public Contact getByNumber(String number) {
		return getContactWhere("ContactNumber =  \"" + number + "\"");
	}

	/**
	* I'm not sure if the email address is unique on the Xero side. If so, this should never cause a problem.
	*/
	public Contact getByEmailAddress(String email) {
		return getContactWhere("EmailAddress =  \"" + email + "\"");
	}

	/**
	* Either get a collection of Contact objects, or build from scratch. Then call this method to update/add them to your Xero data.
	*/
	public List<Contact> post(List<Contact> contacts) {
		list = contacts;
		return post().getContactsEndpoint().getList();
	}

	/**
	* Grab a contact via a get method, or build one from scratch to use this method to update/add it.
	*/
	public Contact post(Contact contact) {
		list = new LinkedList<Contact>();
		list.add(contact);
		return post().getContactsEndpoint().getList().get(0);
	}

}