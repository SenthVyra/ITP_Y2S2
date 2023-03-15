package supermarket.dashboard.service;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

public class Service {
	
	public static JSONObject totalSalesAndPurchasesLastSevenDays() {
		JSONObject json = new JSONObject();
		for(int i = 7;i >=0; i--) {
			String date = java.time.LocalDate.now().minusDays(i).toString();
			BigDecimal stotal = supermarket.sales.service.Service.totalSalesByDate(date);
			BigDecimal ptotal = supermarket.purchase.service.Service.totalPurchasesByDate(date);
			if(stotal == null) {
				stotal = new BigDecimal(0);
			}
			if(ptotal == null) {
				ptotal = new BigDecimal(0);
			}
			BigDecimal[] arr = {stotal,ptotal};
			try {
				json.put(date, arr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}

}
