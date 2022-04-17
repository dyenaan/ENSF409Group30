package edu.ucalgary.ensf409;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.2
 * @since 1.0
 */

/*
 * The purpose of the OutputToFile is to output a given order as a formatted text file.
 */

public class OutputToFile {
    private final String FILENAME;
    private final String ORDER_ID;

    OutputToFile(String orderID) {
        if (orderID == null) throw new IllegalArgumentException();

        this.FILENAME = "orderform-" + orderID + ".txt";
        this.ORDER_ID = orderID;
    }

    // The getFilename method returns the filename

    public String getFilename() {
        return this.FILENAME;
    }

    // The getOrderID method returns the order ID

    public String getOrderID() {
        return this.ORDER_ID;
    }

    // The createOrderForm method creates a formatted text file and inputs in it the best hampers.

    public void createOrderForm(Order order) throws IOException {
        if (order.getFamilies() == null) throw new IllegalArgumentException();

        FileWriter writer = new FileWriter(FILENAME);
        writer.write("Example Food Bank" + "\n");
        writer.write("Hamper Order Form " + ORDER_ID + "\n\n");
        writer.write("Name: " + "\n");
        writer.write("Date: " + "\n\n");
        for (int i = 0; i < order.getFamilies().length; i++) {
            writer.write("Hamper " + (i + 1) + ": " + formatFamilyString(order.getFamilies()[i]) + "\n");
        }

        writer.write("\n");
        for (int i = 0; i < order.getFamilies().length; i++) {
            if (i > 0) writer.write("\n\n");
            writer.write("Hamper " + (i + 1) + " items:\n");
            ArrayList<Map<String,String>> bestHamper = order.getFamilies()[i].getHamper();
            for (Map<String, String> foodItem : bestHamper) {
                String name = foodItem.get("Name");
                String itemID = foodItem.get("ItemID");
                writer.write(itemID + "\t\t" + name + "\n");
            }
        }
        writer.close();
    }

    // The formatFamilyString method creates a string of the client counts.

    private String formatFamilyString(Family family) {
        if (family.getClientCount(ClientTypes.MALE.asString()) < 1) throw new IllegalArgumentException();
        StringBuilder formattedString = new StringBuilder();
        if (family.getClientCount(ClientTypes.MALE.asString()) > 0) {
            formattedString.append("Adult male: ").append(family.getClientCount(ClientTypes.MALE.asString())).append(", ");
        }
        if (family.getClientCount(ClientTypes.FEMALE.asString()) > 0) {
            formattedString.append("Adult female: ").append(family.getClientCount(ClientTypes.FEMALE.asString())).append(", ");
        }
        if (family.getClientCount(ClientTypes.CHILDUE.asString()) > 0) {
            formattedString.append("Child under eight: ").append(family.getClientCount(ClientTypes.CHILDUE.asString())).append(", ");
        }
        if (family.getClientCount(ClientTypes.CHILDOE.asString()) > 0) {
            formattedString.append("Child over eight: ").append(family.getClientCount(ClientTypes.CHILDOE.asString())).append(", ");
        }
        formattedString.deleteCharAt(formattedString.length() - 2);
        return formattedString.toString();
    }
}
