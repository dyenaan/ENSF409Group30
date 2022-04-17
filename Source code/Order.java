package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.3
 * @since 1.0
 */

/*
 * The order class creates a family class for each hamper and stores them into a family array.
 * The order class is also responsible for deleting all the items after each best hamper is found
 */

public class Order {

    private final Family[] families;
    private ArrayList<String> usedItemIDs = new ArrayList<>();
    private boolean orderCompleted = false;

    public Order(ArrayList<Map<String, String>> orderList) throws HamperAlreadyFoundException, StockNotAvailableException {
        if (!validateOrderList(orderList)) throw new IllegalArgumentException();

        families = new Family[orderList.size()];
        try {
            for (int i = 0; i < orderList.size(); i++) {
                int maleCount = Integer.parseInt(orderList.get(i).get(ClientTypes.MALE.asString()));
                int femaleCount = Integer.parseInt(orderList.get(i).get(ClientTypes.FEMALE.asString()));
                int childUECount = Integer.parseInt(orderList.get(i).get(ClientTypes.CHILDUE.asString()));
                int childOECount = Integer.parseInt(orderList.get(i).get(ClientTypes.CHILDOE.asString()));
                families[i] = new Family(maleCount, femaleCount, childUECount, childOECount, usedItemIDs);
                families[i].findBestHamper();
            }
            deleteOrderFromDatabase();
            orderCompleted = true;
        } catch (StockNotAvailableException e) {
            usedItemIDs = new ArrayList<>();
        }
    }

    // The getFamilies method returns the families array

    public Family[] getFamilies() {
        return families;
    }

    // The getOrderCompleted method returns the boolean orderCompleted

    public boolean getOrderCompleted() {
        return orderCompleted;
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

    // The validateOrderList method returns true if the orderList is valid and false if the orderList is not valid

    private boolean validateOrderList(ArrayList<Map<String, String>> orderList) {
        if (orderList.isEmpty()) return false;
        else {
            for (Map<String, String> clients : orderList) {
                if (!clients.containsKey(ClientTypes.MALE.asString())) return false;
                if (!clients.containsKey(ClientTypes.FEMALE.asString())) return false;
                if (!clients.containsKey(ClientTypes.CHILDOE.asString())) return false;
                if (!clients.containsKey(ClientTypes.CHILDUE.asString())) return false;
                if (Integer.parseInt(clients.get(ClientTypes.MALE.asString())) < 0) return false;
                if (Integer.parseInt(clients.get(ClientTypes.FEMALE.asString())) < 0) return false;
                if (Integer.parseInt(clients.get(ClientTypes.CHILDUE.asString())) < 0) return false;
                if (Integer.parseInt(clients.get(ClientTypes.CHILDOE.asString())) < 0) return false;
            }
        }
        return true;
    }
}

