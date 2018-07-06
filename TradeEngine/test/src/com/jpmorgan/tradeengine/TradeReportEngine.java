package com.jpmorgan.tradeengine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.jpmorgan.tradeengine.CorporateCalendar;
import com.jpmorgan.tradeengine.Instrucon;
import com.jpmorgan.tradeengine.TradeEngine;

public class TradeReportEngine {

	@Test
	public void testWeekend(){
		Instrucon instrucon = new Instrucon();
		instrucon.setCurrency("SGP");
		instrucon.setSelementDate(new Date("01 Jul 2018"));
		CorporateCalendar cal = new CorporateCalendar();
		cal.setWorkingSelementDate(instrucon);
		assertEquals("Mon Jul 02 00:00:00 BST 2018", instrucon.getSelementDate().toString());

		instrucon.setSelementDate(new Date("07 Jul 2018"));
		cal = new CorporateCalendar();
		cal.setWorkingSelementDate(instrucon);
		assertEquals("Mon Jul 09 00:00:00 BST 2018",instrucon.getSelementDate().toString());

		instrucon.setCurrency("AED");
		instrucon.setSelementDate(new Date("07 Jul 2018"));
		cal = new CorporateCalendar();
		cal.setWorkingSelementDate(instrucon);
		assertEquals("Sun Jul 08 00:00:00 BST 2018",instrucon.getSelementDate().toString());

		instrucon.setCurrency("AED");
		instrucon.setSelementDate(new Date("06 Jul 2018"));
		cal = new CorporateCalendar();
		cal.setWorkingSelementDate(instrucon);
		assertEquals("Sun Jul 08 00:00:00 BST 2018",instrucon.getSelementDate().toString());
	}

	@Test
	public void testReport(){
		ArrayList<Instrucon> i = new ArrayList<Instrucon>();

		Instrucon instrucon = new Instrucon("foo", "S", "0.50", "SGP", "01 Jul 2018", "01 Jul 2018", "200", "100.25");
		i.add(instrucon);

		instrucon = new Instrucon("goo", "B", "0.50", "SGP", "01 Jul 2018", "02 Jul 2018", "200", "100.25");
		i.add(instrucon);

		instrucon = new Instrucon("joo", "S", "0.90", "SGP", "01 Jul 2018", "08 Jul 2018", "200", "100.25");
		i.add(instrucon);

		instrucon = new Instrucon("boo", "S", "0.80", "SGP", "01 Jul 2018", "09 Jul 2018", "200", "100.25");
		i.add(instrucon);

		instrucon = new Instrucon("too", "S", "0.60", "SGP", "01 Jul 2018", "01 Jul 2018", "200", "100.25");
		i.add(instrucon);

		for(Instrucon each : i){
			CorporateCalendar c = new CorporateCalendar();
			c.setWorkingSelementDate(each);
		}

		TradeEngine.report(i);
	}

}
