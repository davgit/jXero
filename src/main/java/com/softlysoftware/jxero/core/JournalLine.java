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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JournalLine")
@XmlAccessorType(XmlAccessType.NONE)
public class JournalLine {

	
	@XmlElement(name = "LineAmount")
	private String lineAmount;
	public double getLineAmount(){return Formats.parseMoney(lineAmount);}
	public void setLineAmount(double lineAmount){this.lineAmount = Formats.formatMoney(lineAmount);}
	
	
	@XmlElement(name = "AccountCode")
	private String accountCode;
	public String getAccountCode(){return accountCode;}
	public void setAccountCode(String accountCode){this.accountCode = accountCode;}
	
	
	@XmlElement(name = "Description")
	private String description;
	public String getDescription(){return description;}
	public void setDescription(String description){this.description = description;}
	
	@XmlElement(name = "TaxType")
	private String taxType;
	public String getTaxType(){return taxType;}
	public void setTaxType(String taxType){this.taxType = taxType;}
	
	
	@XmlElement(name = "Tracking")
	private String tracking;
	public String getTracking() {return tracking;}
	public void setTracking(String tracking) {this.tracking = tracking;}
	

}