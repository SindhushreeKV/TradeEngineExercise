package com.jpmorgan.tradeengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main {
	public static void main(String args[]) {
		if(args.length <= 0){
			System.out.println("Please provide filename which contains trade info");
			System.exit(0);
		}

		String fileName = args[0];
		File file = new File(fileName);

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			ArrayList<Instrucon> instrucons = new ArrayList<Instrucon>();
			while ((line = br.readLine()) != null){
				String enty[] = line.split("\\|"); 
				if(enty.length == 8){
					Instrucon instrucon = new Instrucon(enty[0], enty[1], enty[2], enty[3], enty[4], enty[5], enty[6], enty[7]);
					instrucons.add(instrucon);
				}
				else{
					System.out.println("Please provide valid enty details");
					System.exit(0);
				}
			}

			for(Instrucon each : instrucons){
				CorporateCalendar c = new CorporateCalendar();
				c.setWorkingSelementDate(each);
			}

			TradeEngine.report(instrucons);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	

}