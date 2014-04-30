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

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "LineItem")
@XmlAccessorType(XmlAccessType.NONE)
public class LineItem {

	@XmlElement(name = "Description")
	private String description;
	public String getDescription(){return description;}
	public void setDescription(String description){this.description = description;}

	@XmlElement(name = "Quantity")
	private String quantity;
	public double getQuantity(){return Formats.parseQuantity(quantity);}
	public void setQuantity(double quantity){this.quantity = Formats.formatQuantity(quantity);}
	public void setQuantity(BigDecimal quantity){this.quantity = Formats.formatQuantity(quantity);}
	
	@XmlElement(name = "UnitAmount")
	private String unitAmount;
	public double getUnitAmount(){return Formats.parseMoney(unitAmount);}
	public void setUnitAmount(double unitAmount){this.unitAmount = Formats.formatMoney(unitAmount);}

	@XmlElement(name = "TaxType")
	private String taxType;
	public String getTaxType(){return taxType;}
	public void setTaxType(String taxType){this.taxType = taxType;}

	@XmlElement(name = "TaxAmount")
	private String taxAmount;
	public double getTaxAmount(){return Formats.parseMoney(taxAmount);}
	public void setTaxAmount(double taxAmount){this.taxAmount = Formats.formatMoney(taxAmount);}

	
	private String lineAmount;
	public double getLineAmount(){return Formats.parseMoney(lineAmount);}
	public void setLineAmount(double lineAmount){this.lineAmount = Formats.formatMoney(lineAmount);}
	
	//the follow 2 methods are only for jaxb marshalling/unmarshalling. Please never explicitly call them.
	@XmlElement(name = "LineAmount")
	public Double getLineAmountForJaxb() {return null;}
	public void setLineAmountForJaxb(Double lineAmountForJaxb) {this.setLineAmount(lineAmountForJaxb);}

	@XmlElement(name = "DiscountRate")
	private String discountRate;
	public double getDiscountRate(){return Formats.parseQuantity(discountRate);}
	public void setDiscountRate(double discountRate){this.discountRate = Formats.formatQuantity(discountRate);}

	@XmlElement(name = "AccountCode")
	private String accountCode;
	public String getAccountCode(){return accountCode;}
	public void setAccountCode(String accountCode){this.accountCode = accountCode;}

	@XmlElement(name = "ItemCode")
	private String itemCode;
	public String getItemCode(){return itemCode;}
	public void setItemCode(String itemCode){this.itemCode = itemCode;}

}