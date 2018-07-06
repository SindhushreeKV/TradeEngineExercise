package com.jpmorgan.tradeengine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeEngine {

	public static void report(ArrayList<Instrucon> instrucons){
		StringBuffer buffer = new StringBuffer();
		buffer.append("*************************************************************\n");
		buffer.append("\t\t   Daily Trading Report\n");
		buffer.append("*************************************************************\n");

		StringBuffer buf = new StringBuffer();
		
		HashMap<Date, List<Instrucon>> instruconsPerDate = new HashMap<Date, List<Instrucon>>();
		//Set instrucons as per selement date
		for(int i = 0; i < instrucons.size(); i++){
			Date selementDate = instrucons.get(i).getSelementDate();
			if(!instruconsPerDate.containsKey(selementDate)){
				List<Instrucon> list = new ArrayList<Instrucon>();
				list.add(instrucons.get(i));
				instruconsPerDate.put(selementDate, list);
			}else
				instruconsPerDate.get(selementDate).add(instrucons.get(i));
		}

		//Set trade amount as per buy or sell
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
		for (Map.Entry<Date, List<Instrucon>> entry : instruconsPerDate.entrySet()){
			String formattedDate = format.format(entry.getKey());
			List<Instrucon> listOfInstrucons = entry.getValue();
			HashMap<String, List<Double>> amtAsPerBuyOrSell = new HashMap<String, List<Double>>();
			for(Instrucon i : listOfInstrucons){
				TradeAmount tradeAmt = new TradeAmount();
				double amt = tradeAmt.calculate(i);
				i.setTradeAmt(amt);
				String buyOrSell =  i.getBuyOrSell();
				if(!amtAsPerBuyOrSell.containsKey(buyOrSell)){
					List<Double> amts = new ArrayList<Double>();
					amts.add(amt);
					amtAsPerBuyOrSell.put(buyOrSell, amts);
				}
				else
					amtAsPerBuyOrSell.get(buyOrSell).add(amt);

			}

			//Sort instrucons based on trade amount for ranking of enties
			Collections.sort(listOfInstrucons, new TradeAmount());

			HashMap<String, List<String>> sortedHashMapOfAmt = new HashMap<String, List<String>>();
			for(Instrucon i : listOfInstrucons){
				String buyOrSell =  i.getBuyOrSell();
				if(!sortedHashMapOfAmt.containsKey(buyOrSell)){
					List<String> amts = new ArrayList<String>();
					amts.add(i.getEnty());
					sortedHashMapOfAmt.put(buyOrSell, amts);
				}
				else
					sortedHashMapOfAmt.get(buyOrSell).add(i.getEnty());
			}

			buf.append("\nEnties ranking on " + formattedDate + "\n");
			for(Map.Entry<String, List<String>>  sortedEnties : sortedHashMapOfAmt.entrySet()){
				List<String> enties = sortedEnties.getValue();
				if(sortedEnties.getKey().equals("S"))
					buf.append("Based on incoming :\n");
				else
					buf.append("Based on outgoing :\n");
				for(String enty : enties )
					buf.append(enty + "\n");
			}

			for(Map.Entry<String, List<Double>>  e : amtAsPerBuyOrSell.entrySet()){
				List<Double> tradeAmts = e.getValue();
				double totalAmt = 0;
				for(Double d : tradeAmts)
					totalAmt += d;

				String buyOrSell = "";
				if(e.getKey().equals("B"))
					buyOrSell = "outgoing";
				else
					buyOrSell = "incoming";

				buffer.append("\t" + totalAmt + " USD is seled " + buyOrSell + " on " + formattedDate + "\n");
			}

		}

		buffer.append("*************************************************************");
		System.out.println(buffer);
		System.out.println(buf);
	}
}
