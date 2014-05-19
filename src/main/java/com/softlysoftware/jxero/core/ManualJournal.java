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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
 

/**
* Xero's <a href="http://developer.xero.com/documentation/api/manual-journals/">Manual Journal record</a>.
*/
@XmlRootElement(name = "ManualJournal")
@XmlAccessorType(XmlAccessType.NONE)
public class ManualJournal {
	
	@XmlElement(name = "ManualJournalID")
	private String id;
	public String getId(){return id;}
	public void setId(String id){this.id = id;}	

	@XmlElement(name = "Narration")
	private String narration;
	public String getNarration(){return narration;}
	public void setNarration(String narration){this.narration = narration;}

	
	@XmlElement(name = "LineAmountTypes")
	private String lineAmountTypes;
	public String getLineAmountTypes(){return lineAmountTypes;}
	public void setLineAmountTypes(String lineAmountTypes){this.lineAmountTypes = lineAmountTypes;}	
	
	@XmlElement(name = "Date")
	private String date;
	public Date getDate(){return Formats.parseDate(date);}
	public void setDate(Date date){this.date = Formats.formatDate(date);}

	
	@XmlElementWrapper(name = "JournalLines")
	@XmlElement(name = "JournalLine")
	private List<JournalLine> journalLines = new LinkedList<JournalLine>();
	public List<JournalLine> getJournalLines(){return journalLines;}
	public void setJournalLines(List<JournalLine> journalLines){this.journalLines = journalLines;}	
	
	public enum Status {DRAFT, POSTED, DELETED, VOIDED};
	
	@XmlElement(name = "Status")
	private String status;
	public Status getStatus(){
		if (status == null) return null;
		if (status.equals("DRAFT")) return Status.DRAFT;
		if (status.equals("POSTED")) return Status.POSTED;
		if (status.equals("DELETED")) return Status.DELETED;		
		if (status.equals("VOIDED")) return Status.VOIDED;
		throw new RuntimeException("Bad status : " + status);
	}
	public void setStatus(Status status){this.status = status.toString();}
	

	@XmlElement(name = "Url")
	private String url;
	public String getUrl(){return url;}
	public void setUrl(String url){this.url = url;}

	@XmlElement(name = "ShowOnCashBasisReports")
	private String showOnCashBasisReports = "true";
	public boolean isShowOnCashBasisReports(){return showOnCashBasisReports != null && showOnCashBasisReports.equals("true");}
	public void setShowOnCashBasisReports(boolean showOnCashBasisReports){this.showOnCashBasisReports = showOnCashBasisReports+"";}
	
	


}