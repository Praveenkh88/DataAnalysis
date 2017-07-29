package com.manthan;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WeekAnalyzer implements Runnable {

	private List<DataSet> dataSet;	
	Map<String, List<DataSet>> storeMap = new HashMap<>();
	
	public WeekAnalyzer(List<DataSet> dataSet) {
		this.dataSet = dataSet;
	} 
	
	@Override
	public void run() {
		
		for (DataSet data : dataSet) {
			
			if (storeMap.containsKey(data.getStore())) {
				List<DataSet> list = storeMap.get(data.getStore());
				list.add(data);
				storeMap.put(data.getStore(), list);
			}
			else {
				List<DataSet> list = new ArrayList<>();
				list.add(data);
				storeMap.put(data.getStore(), list);
			}
			
		}
		
		String result = "";
		Set<Entry<String, List<DataSet>>> entrySet = storeMap.entrySet();
		for (Entry<String, List<DataSet>> entry : entrySet) {
			List<DataSet> value = entry.getValue();
			double sales = 0;
			double purchasedUnits = 0;
			Set<Long> memberCount = new HashSet<>();
			Set<Long> transactionCount = new HashSet<>();
			int discount = 0;
			for (DataSet data : value) {
				sales += Double.parseDouble(data.getSales());
				purchasedUnits += Double.parseDouble(data.getUnits());
				memberCount.add(Long.parseLong(data.getCustomerId()));
				transactionCount.add(Long.parseLong(data.getInvoice()));
				discount += Double.parseDouble(data.getDiscount());
			}
			double avgBasketVal = sales/transactionCount.size();
			double avgSales = sales/memberCount.size();
			double discountPer = (sales/discount)*100;
			result += "Store: " + entry.getKey() + " Sales: " + sales + " purchasedUnits: " + purchasedUnits +
					" MemCount: " + memberCount.size() + " Avg BasketValue: " + avgBasketVal + " tranCount: " 
					+ transactionCount.size() + " AvgSales: " + avgSales + " Disc%" + discountPer + "\n";
			
		}
		
		Path path = Paths.get(Thread.currentThread().getName() + ".txt");
		try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
		writer.write(result);
		}
		catch(IOException ex){
		ex.printStackTrace();
		}	
			
		}
		

	}


