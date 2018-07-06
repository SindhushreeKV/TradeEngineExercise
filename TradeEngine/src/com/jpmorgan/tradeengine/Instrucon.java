package com.jpmorgan.tradeengine;

import java.util.Date;

public class Instrucon {
	private String enty = "";
	private String buyOrSell = "";
	private double agreedFX = 0;
	private String currency = "";
	private Date instruconDate;
	private Date selementDate;
	private int units = 0;
	private double pricePerUnit = 0;
	private double tradeAmt;

	public Instrucon(){
		
	}
	
	public Instrucon(String enty, String buyOrSell, String agreedFX, String currency, String instruconDate, String selementDate,
			String units, String pricePerUnit) {
		this.enty = enty;
		this.buyOrSell = buyOrSell;
		this.agreedFX = Double.parseDouble(agreedFX);
		this.currency = currency;
		this.instruconDate = new Date(instruconDate);
		this.selementDate = new Date(selementDate);
		this.units = Integer.parseInt(units);
		this.pricePerUnit = Double.parseDouble(pricePerUnit);
	}

	public String getEnty() {
		return enty;
	}

	public void setEnty(String enty) {
		this.enty = enty;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public double getAgreedFX() {
		return agreedFX;
	}

	public void setAgreedFX(double agreedFX) {
		this.agreedFX = agreedFX;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstruconDate() {
		return instruconDate;
	}

	public void setInstruconDate(Date instruconDate) {
		this.instruconDate = instruconDate;
	}

	public Date getSelementDate() {
		return selementDate;
	}

	public void setSelementDate(Date selementDate) {
		this.selementDate = selementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public void setTradeAmt(double amt) {
		this.tradeAmt = amt;
	}
	
	public double getTradeAmt(){
		return tradeAmt;
	}
	
}
