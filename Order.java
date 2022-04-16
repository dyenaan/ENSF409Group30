package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Map;

public class Order {

	private final Family[] families;

	public Order(ArrayList<Map<String, String>> orderList) throws HamperAlreadyFoundException, StockNotAvailableException {
        families = new Family[orderList.size()];
        for (int i = 0; i < orderList.size(); i++) {
            int maleCount = Integer.parseInt(orderList.get(i).get("Male"));
            int femaleCount = Integer.parseInt(orderList.get(i).get("Female"));
            int childUECount = Integer.parseInt(orderList.get(i).get("ChildUE"));
            int childOECount = Integer.parseInt(orderList.get(i).get("ChildOE"));
            families[i] = new Family(maleCount, femaleCount, childUECount, childOECount);
        }
	}

	public Family[] getFamilies(){
		return families;
	}
}

