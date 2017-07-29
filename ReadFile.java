package com.manthan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ReadFile extends HttpServlet {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  static int  maleGenCount = 0;
	  static int  femaleGenCount = 0;
	  static int NAGen = 0;
	  static int UnknownGen = 0;
	  
	  static int marriedCount = 0;
	  static int singleCount = 0;
	  static int NAMS = 0;
	  static int UnknownMS = 0;
	  
	  static long totalRecord = 0;
	  
	  static long unitsMean = 0;
	  static long salesMean = 0;
	  static long disMean = 0;
	  
	  private static void calculateDiscountData(String discount) {
			
			if (discount == null) {
				Discount.nullCount++;
			}
			
			else if(discount.equals("0")) {
				Discount.zerocount++;
			}
			
			else {
				Discount.mean += Double.parseDouble(discount);
			}
			
		}

		private static void calculateSalesData(String sale) {
			
			if (sale == null) {
				SalesValue.nullCount++;
			}
			
			else if(sale.equals("0.0000")) {
				SalesValue.zerocount++;
			}
			
			else {
				SalesValue.mean += Double.parseDouble(sale);
			}
			
			
			
		}

		private static void calculateUnitsData(String unit) {
			
			if (unit == null) {
				UnitsSold.nullCount++;
			}
			
			else if(unit.equals("0.000")) {
				UnitsSold.zerocount++;
			}
			else {
				UnitsSold.mean += Double.parseDouble(unit);
			}
			
			
		}
	  
	  private static void calculateMaritalStatus(String maritalStatus) {
			 
		    if (maritalStatus.startsWith("M")) {
		    	marriedCount++;
		    }
		    else if (maritalStatus.startsWith("S")) {
		    	singleCount++;
		    }
		    
		    else if (maritalStatus.startsWith("U")) {
		    	UnknownMS++;
		    }
		    else if(maritalStatus.startsWith("N")) {
		    	NAMS++;
		    	
		    }
		    
			
		}

		private static void calculateGenderSumm(String gender) {
			 
		    if (gender.startsWith("F")) {
		    	femaleGenCount++;
		    }
		    else if (gender.startsWith("M")) {
		    	maleGenCount++;
		    }
		    
		    else if (gender.startsWith("U")) {
		    	UnknownGen++;
		    }
		    else if(gender.startsWith("N")) {
		    	NAGen++;
		    	
		    }
		    
			
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		   HashMap<String,Double> storeMap  = new HashMap<String,Double>(); 
		
		   maleGenCount = 0;
		   femaleGenCount = 0;
		   NAGen = 0;
		   UnknownGen = 0;
		  
		   marriedCount = 0;
		   singleCount = 0;
		   NAMS = 0;
		   UnknownMS = 0;
		   
		   totalRecord = 0;
			  
		   unitsMean = 0;
		   salesMean = 0;
		   disMean = 0;
		   
		   Double totalSales = 0.0;
		
		HttpSession session = request.getSession();
		
		Path path = Paths.get("sale_line_1.5M.txt");
		String weekIdentifier = "";
		try{

		  List contents = Files.readAllLines(path);
		  List<DataSet> weekDataList = new ArrayList<>();
          
		  //Read from the stream
		  for(Object content:contents){//for each line of content in contents
		    //System.out.println(content.toString());// print the line
		    String[] temp = content.toString().split("[|]");
		    totalRecord++;
		    if(totalRecord == 1) {
		    	weekIdentifier = temp[1].trim();;
		    }
		    String weekName = temp[1].trim();
if (weekName.equals(weekIdentifier)) {
		    	
		    	DataSet ds = new DataSet();
		    	
		    	ds.setTransactionDate(new Date());
		    	ds.setWeekName(temp[1]);
		    	ds.setMonthName(temp[2]);
		    	ds.setInvoice(temp[3]);
		    	ds.setCustomerId(temp[4]);
		    	ds.setGender(temp[5]);
		    	ds.setMaritalStatus(temp[6]);
		    	ds.setProduct(temp[7]);
		    	ds.setStore(temp[8]);
		    	ds.setUnits(temp[9]);
		    	ds.setSales(temp[10]);
		    	ds.setDiscount("0");
		    	
		    	weekDataList.add(ds);
		    }
		    
		    else {
		    	WeekAnalyzer anz = new WeekAnalyzer(weekDataList);
		    	Thread th = new Thread(anz, weekIdentifier);
		    	th.start();
		    	weekDataList = new ArrayList<>();
                DataSet ds = new DataSet();
		    	
		    	ds.setTransactionDate(new Date());
		    	ds.setWeekName(temp[1]);
		    	ds.setMonthName(temp[2]);
		    	ds.setInvoice(temp[3]);
		    	ds.setCustomerId(temp[4]);
		    	ds.setGender(temp[5]);
		    	ds.setMaritalStatus(temp[6]);
		    	ds.setProduct(temp[7]);
		    	ds.setStore(temp[8]);
		    	ds.setUnits(temp[9]);
		    	ds.setSales(temp[10]);
		    	ds.setDiscount("0");
		    	
		    	weekDataList.add(ds);
		    	weekIdentifier = weekName;
		    }

		    String maritalStatus = temp[6].trim();
		    String gender = temp[5].trim();
		    String unit = temp[9].trim();
		    String sale = temp[10].trim();
		    Double saleD = Double.parseDouble(sale);
		    String storeDesc = temp[8].trim();
		    totalSales += saleD;
		    
		   if(storeMap.containsKey(storeDesc)){
		        storeMap.put(storeDesc, storeMap.get(storeDesc)+saleD);
		   }
		   else
		   {
			   storeMap.put(storeDesc, saleD);
		   }
		    
		    String discount = "0";
		    calculateUnitsData(unit);
		    calculateSalesData(sale);
		    calculateDiscountData(discount);
		    calculateGenderSumm(gender);
		    calculateMaritalStatus(maritalStatus);  
		  }
		  
		  if (!weekDataList.isEmpty()) {
			  WeekAnalyzer anz = new WeekAnalyzer(weekDataList);
		    	Thread th = new Thread(anz, weekIdentifier);
		    	th.start();
		  }
		  
		  UnitsSold.mean = UnitsSold.mean/(double)totalRecord;
		  SalesValue.mean = SalesValue.mean/(double)totalRecord;
		  Discount.mean = Discount.mean/(double)totalRecord;
		  
		  //System.out.println("UnitsSold: Null: " + UnitsSold.nullCount + " , Zero: " + UnitsSold.zerocount + " ,Mean: " + UnitsSold.mean);
		  //System.out.println("SalesValue: Null: " + SalesValue.nullCount + " , Zero: " + SalesValue.zerocount + " ,Mean: " + SalesValue.mean);
		  //System.out.println("Discount: Null: " + Discount.nullCount + " , Zero: " + Discount.zerocount + " ,Mean: " + Discount.mean);
		  //System.out.println("TotalCount: " + totalRecord);

		  UnitsSold unitsSold = new UnitsSold();
		  SalesValue salesValue = new SalesValue();
		  Discount discount = new Discount();
		  
		  session.setAttribute("totalRecordCount", totalRecord);
		  
		  session.setAttribute("unitsSoldNullCount",UnitsSold.nullCount); 
		  session.setAttribute("unitsSoldZeroCount",UnitsSold.zerocount); 
		  session.setAttribute("unitsSoldMean",UnitsSold.mean); 
		  
		  session.setAttribute("salesValueNullCount",SalesValue.nullCount); 
		  session.setAttribute("salesValueZeroCount",SalesValue.zerocount); 
		  session.setAttribute("salesValueMean",SalesValue.mean); 
		  
		  session.setAttribute("discountNullCount",Discount.nullCount); 
		  session.setAttribute("discountZeroCount",Discount.zerocount); 
		  session.setAttribute("discountMean",Discount.mean); 
		  
		  session.setAttribute("salesValue",salesValue);
		  session.setAttribute("discount",discount);
		  
		  //System.out.println("F : " + femaleGenCount + "M : " + maleGenCount + "U : " + UnknownGen + "NA : " + NAGen);
		  //System.out.println("M : " + marriedCount + "S : " + singleCount + "U : " + UnknownMS + "NA : " + NAMS);
		  session.setAttribute("maleGenCount", maleGenCount);
		  session.setAttribute("femaleGenCount",femaleGenCount);
		  session.setAttribute("NAGen",NAGen); 
		  session.setAttribute("UnknownGen",UnknownGen);
		  
		  long totalGen = maleGenCount + femaleGenCount + NAGen + UnknownGen;
		  session.setAttribute("totalGen",totalGen);
		  	  
		  session.setAttribute("marriedCount",marriedCount); 
		  session.setAttribute("singleCount",singleCount); 
		  session.setAttribute("NAMS",NAMS);
		  session.setAttribute("UnknownMS",UnknownMS);
		  
		  long totalMS = marriedCount + singleCount + NAMS + UnknownMS;
		  session.setAttribute("totalMS",totalMS);
		  
		  
		  for(Map.Entry<String,Double> itr:storeMap.entrySet())
			  storeMap.put(itr.getKey(), Double.parseDouble(String.format("%.2f", itr.getValue()*100/totalSales)));

			String result = "";
			
		  for(Map.Entry<String,Double> itr:storeMap.entrySet())
			  result = result + itr.getKey() + "  :  " + itr.getValue() + "\n";
		  
		  Path path2 = Paths.get("SalesContributionByStore.txt");
			try(BufferedWriter writer = Files.newBufferedWriter(path2, Charset.forName("UTF-8"))){
			writer.write(result);
			}
			catch(IOException ex){
			ex.printStackTrace();
			}
		  
		  Gson gson = new Gson(); 
		  String jsonStoreResult = gson.toJson(storeMap);
		  
		  session.setAttribute("jsonStoreResult",jsonStoreResult);
		  
		  RequestDispatcher rd=request.getRequestDispatcher("result.jsp");
		  rd.forward(request, response);
		  }catch(IOException ex){
		  ex.printStackTrace();//handle exception here
		}

	}
}
