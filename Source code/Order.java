package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Map;

/*
 * The order class creates a family class for each hamper and stores them into a family array.
 * The order class is also responsible for deleting all the items after each best hamper is found
 */

public class Order {

    private final Family[] families;
    private ArrayList<String> usedItemIDs = new ArrayList<>();
    private boolean orderCompleted = false;

    public Order(ArrayList<Map<String, String>> orderList) throws HamperAlreadyFoundException, StockNotAvailableException {
        families = new Family[orderList.size()];
        try {
            for (int i = 0; i < orderList.size(); i++) {
                int maleCount = Integer.parseInt(orderList.get(i).get(ClientTypes.MALE.toString()));
                int femaleCount = Integer.parseInt(orderList.get(i).get(ClientTypes.FEMALE.toString()));
                int childUECount = Integer.parseInt(orderList.get(i).get(ClientTypes.CHILDUE.toString()));
                int childOECount = Integer.parseInt(orderList.get(i).get(ClientTypes.CHILDOE.toString()));
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

    // deletes the items of each best hamper that have been previously found.

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

