package com.jpmorgan.tradeengine;

import java.util.Comparator;

public class TradeAmount implements Comparator<Instrucon>{

	public double calculate(Instrucon instrucon){
		return instrucon.getAgreedFX() * instrucon.getPricePerUnit() * instrucon.getUnits();
	}

	@Override
	 public int compare(Instrucon m1, Instrucon m2)
	    {
	        if (m1.getTradeAmt() < m2.getTradeAmt()) return 1;
	        if (m1.getTradeAmt() > m2.getTradeAmt()) return -1;
	        else return 0;
	    }
}
