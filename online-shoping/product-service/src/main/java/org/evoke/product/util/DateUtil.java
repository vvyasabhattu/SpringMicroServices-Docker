package org.evoke.product.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
	public static String getDDMMYYDate() {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
	    String strDate= formatter.format(date);  
	    return strDate;
	}
	
	/*public static void main(String[] args) {
		
		System.out.println(getDDMMYYDate());
	}*/
}
