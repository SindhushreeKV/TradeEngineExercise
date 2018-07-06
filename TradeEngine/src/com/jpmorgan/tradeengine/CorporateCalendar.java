package com.jpmorgan.tradeengine;

import java.util.Calendar;
import java.util.Date;

public class CorporateCalendar {

	//Method to set selement date if it falls on weekend
	public void setWorkingSelementDate(Instrucon instrucon){
		String currency = instrucon.getCurrency(); 
		Date date = instrucon.getSelementDate();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		if (((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ) || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) && 
				!currency.equals("AED") && !currency.equals("SAR")) {
			if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) 
				c1.add(Calendar.DAY_OF_MONTH, 2);
			else 
				c1.add(Calendar.DAY_OF_MONTH, 1);
			
			Date newDate = c1.getTime();
			instrucon.setSelementDate(newDate);
		}
		else if (((c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ) || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) && 
				(currency.equals("AED") || currency.equals("SAR"))){
			if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) 
				c1.add(Calendar.DAY_OF_MONTH, 2);
			else 
				c1.add(Calendar.DAY_OF_MONTH, 1);
			
			Date newDate = c1.getTime();
			instrucon.setSelementDate(newDate);
		}
	}
}
