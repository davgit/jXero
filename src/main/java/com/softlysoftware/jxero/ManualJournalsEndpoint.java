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

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.softlysoftware.jxero.core.ManualJournal;

/**
* A simple wrapper for the list of ManualJournal objects, to give the correct structure to the XML files.
*/
@XmlRootElement(name = "ManualJournals")
@XmlAccessorType(XmlAccessType.NONE)
public class ManualJournalsEndpoint extends Endpoint {

	private ManualJournalsEndpoint() { }

	public ManualJournalsEndpoint(XeroClient xeroClient) {this(); this.xeroClient = xeroClient;}

	public String getRootElementName() {
		return "ManualJournals";
	}

	/**
	* When working with this wrapper directly, add the subordiate objects to this list.
	*/
	@XmlElement(name = "ManualJournal")
	public List<ManualJournal> getList(){return list;}
	public void setList(List<ManualJournal> list){this.list = list;}
	private List<ManualJournal> list = new LinkedList<ManualJournal>();

	/**
	* The Xero maintained unique identifier. Will throw an exception if no match is found.
	*/
	public ManualJournal getById(String id) {
		Response response = get(id, null);
		return response.getManualJournalsEndpoint().list.get(0);
	}

	/**
	* Grab manual journals using any "where" filter. 
	* See <a href="http://developer.xero.com/documentation/getting-started/http-requests-and-responses/">the Xero documentation</a> for full details.
	*/
	public List<ManualJournal> getManualJournalsWhere(String where) {
		Response response = getWhere(where);
		if (response.getManualJournalsEndpoint() == null) return new LinkedList<ManualJournal>();
		return response.getManualJournalsEndpoint().getList();
	}

	/**
	* Like the generalised getManualJournalsWhere, but for convenience when you expect just one object to be returned in the set.
	* Throws an exception when there's more than one match.
	* @return If no match is found, returns null.
	*/
	public ManualJournal getManualJournalWhere(String where) {
		List<ManualJournal> manualJournals = getManualJournalsWhere(where);
		if (manualJournals.size() == 0) return null;
		if (manualJournals.size() > 1) throw new RuntimeException("Multiple (" + manualJournals.size() + ") manual journals matched : " + where);
		return manualJournals.get(0);
	}


	/**
	* Either get a collection of ManualJournal objects, or build from scratch. Then call this method to update/add them to your Xero data.
	*/
	public List<ManualJournal> post(List<ManualJournal> manualJournals) {
		list = manualJournals;
		return post().getManualJournalsEndpoint().getList();
	}

	/**
	* Grab a manualJournal via a get method, or build one from scratch to use this method to update/add it.
	*/
	public ManualJournal post(ManualJournal manualJournal) {
		list = new LinkedList<ManualJournal>();
		list.add(manualJournal);
		return post().getManualJournalsEndpoint().getList().get(0);
	}

}