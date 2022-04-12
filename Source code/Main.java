package edu.ucalgary.ensf409;

import java.util.*;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) throws StockNotAvailableException, HamperAlreadyFoundException {
        ArrayList<Map<String,String>> orderList = new ArrayList<>();
        Map<String,String> family1 = new HashMap<>();
        Map<String,String> family2 = new HashMap<>();

        family1.put("Male", "2");
        family1.put("Female", "0");
        family1.put("ChildUE", "1");
        family1.put("ChildOE", "0");

        family2.put("Male", "1");
        family2.put("Female", "1");
        family2.put("ChildUE", "0");
        family2.put("ChildOE", "0");

        orderList.add(family1);
        orderList.add(family2);


        Order order = new Order(orderList);
        for (int i = 0; i < order.getFamilies().length; i++) {
            System.out.println("Hamper " + (i + 1) + ":");
            order.getFamilies()[i].printBestHamper();
            order.getFamilies()[i].getClientCount(ClientTypes.MALE);
            System.out.println("");
        }
    }
}