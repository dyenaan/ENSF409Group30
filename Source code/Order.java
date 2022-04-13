package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Map;

public class Order {

    private final Family[] families;
    private ArrayList<String> usedItemIDs = new ArrayList<>();
    private boolean orderCompleted = false;

    public Order(ArrayList<Map<String, String>> orderList) throws HamperAlreadyFoundException, StockNotAvailableException {
        families = new Family[orderList.size()];
        try {
            for (int i = 0; i < orderList.size(); i++) {
                int maleCount = Integer.parseInt(orderList.get(i).get("Male"));
                int femaleCount = Integer.parseInt(orderList.get(i).get("Female"));
                int childUECount = Integer.parseInt(orderList.get(i).get("ChildUE"));
                int childOECount = Integer.parseInt(orderList.get(i).get("ChildOE"));
                families[i] = new Family(maleCount, femaleCount, childUECount, childOECount, usedItemIDs);
            }
            deleteOrderFromDatabase();
            orderCompleted = true;
        } catch (StockNotAvailableException e) {
            usedItemIDs = new ArrayList<>();
        }
    }

    public Family[] getFamilies() {
        return families;
    }

    private void deleteOrderFromDatabase() {
        Database db = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        db.initializeConnection();
        for (String itemID : usedItemIDs) {
            db.deleteFoodItem(itemID);
        }
        db.close();
    }

    public boolean getOrderCompleted() {
        return orderCompleted;
    }
}

