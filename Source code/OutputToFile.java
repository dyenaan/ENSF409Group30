package edu.ucalgary.ensf409;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OutputToFile {
    private final String FILENAME;
    private final String ORDER_ID;

    OutputToFile(String orderID) {
        this.FILENAME = "orderform-" + orderID + ".txt";
        this.ORDER_ID = orderID;
    }

    public void createOrderForm(Family[] families) throws IOException {
        FileWriter writer = new FileWriter(FILENAME);
        writer.write("Example Food Bank" + "\n");
        writer.write("Hamper Order Form " + ORDER_ID + "\n\n");
        writer.write("Name: " + "\n");
        writer.write("Date: " + "\n\n");
        for (int i = 0; i < families.length; i++) {
            writer.write("Hamper " + (i + 1) + ": " + formatFamilyString(families[i]) + "\n");
        }

        writer.write("\n");
        for (int i = 0; i < families.length; i++) {
            if (i > 0) writer.write("\n\n");
            writer.write("Hamper " + (i + 1) + " items:\n");
            ArrayList<Map<String,String>> bestHamper = families[i].getHamper();
            for (Map<String, String> foodItem : bestHamper) {
                String name = foodItem.get("Name");
                String itemID = foodItem.get("ItemID");
                writer.write(itemID + "\t\t" + name + "\n");
            }
        }
        writer.close();
    }
    public String getFilename() {
        return this.FILENAME;
    }

    private String formatFamilyString(Family family) {
        StringBuilder formattedString = new StringBuilder();
        if (family.getClientCount(ClientTypes.MALE) > 0) {
            formattedString.append("Adult male: ").append(family.getClientCount(ClientTypes.MALE)).append(", ");
        }
        if (family.getClientCount(ClientTypes.FEMALE) > 0) {
            formattedString.append("Adult female: ").append(family.getClientCount(ClientTypes.FEMALE)).append(", ");
        }
        if (family.getClientCount(ClientTypes.CHILDUE) > 0) {
            formattedString.append("Child under eight: ").append(family.getClientCount(ClientTypes.CHILDUE)).append(", ");
        }
        if (family.getClientCount(ClientTypes.CHILDOE) > 0) {
            formattedString.append("Child over eight: ").append(family.getClientCount(ClientTypes.CHILDOE)).append(", ");
        }
        formattedString.deleteCharAt(formattedString.length() - 2);
        return formattedString.toString();
    }
}
