package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;
    private int itemLength;

    private Connection dbConnect;
    private ResultSet results;

    public Database(String url, String user, String pw) {
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
            updateItemLength();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getDburl() {
        return this.DBURL;
    }

    String getUsername() {
        return this.USERNAME;
    }

    String getPassword() {
        return this.PASSWORD;
    }

    public Map<String, String> selectClientNeeds(String clientID) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Statement myStmt = dbConnect.createStatement();

            String query = String.format("SELECT * FROM DAILY_CLIENT_NEEDS WHERE (ClientID = %s)", clientID);
            results = myStmt.executeQuery(query);

            if (!results.next()) throw new IllegalArgumentException(clientID + " is an invalid client ID");
            else {
                String calories = results.getString(NutritionTypes.CALORIES.asString());
                String wholeGrains = results.getString(NutritionTypes.WHOLE_GRAINS.asString());
                String protein = results.getString(NutritionTypes.PROTEIN.asString());
                String fruitsVeggies = results.getString(NutritionTypes.FRUIT_VEGGIES.asString());
                String other = results.getString(NutritionTypes.OTHER.asString());

                map.put(NutritionTypes.CALORIES.asString(), calories);
                map.put(NutritionTypes.WHOLE_GRAINS.asString(), wholeGrains);
                map.put(NutritionTypes.PROTEIN.asString(), protein);
                map.put(NutritionTypes.FRUIT_VEGGIES.asString(), fruitsVeggies);
                map.put(NutritionTypes.OTHER.asString(), other);
                map.put("ClientID", clientID);
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public Map<String, String> selectFoodItem(String foodID) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Statement myStmt = dbConnect.createStatement();

            String query = String.format("SELECT * FROM AVAILABLE_FOOD WHERE (ItemID = %s)", foodID);
            results = myStmt.executeQuery(query);

            if (!results.next()) throw new IllegalArgumentException(foodID + " is an invalid food ID");
            else {
                String grainContent = results.getString("GrainContent");
                String fvContent = results.getString("FVContent");
                String proContent = results.getString("ProContent");
                String other = results.getString("Other");
                String calories = results.getString("Calories");
                String name = results.getString("Name");

                map.put(NutritionTypes.WHOLE_GRAINS.asString(), grainContent);
                map.put(NutritionTypes.FRUIT_VEGGIES.asString(), fvContent);
                map.put(NutritionTypes.PROTEIN.asString(), proContent);
                map.put(NutritionTypes.OTHER.asString(), other);
                map.put(NutritionTypes.CALORIES.asString(), calories);
                map.put("ItemID", foodID);
                map.put("Name", name);
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return map;
    }

    private void updateItemLength() {
        int length = 0;
        try {
            String query = "SELECT * FROM AVAILABLE_FOOD";

            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery(query);

            while (results.next()) {
                length++;
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.itemLength = length;
    }

    public int getItemLength() {
        return this.itemLength;
    }

    public void deleteFoodItem(String foodID) {
        try {
            String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";

            PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
            preparedStmt.setString(1, foodID);
            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> getItemIDs() {
        ArrayList<String> itemIDs = new ArrayList<>();
        try {
            String query = "SELECT * FROM AVAILABLE_FOOD";

            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery(query);

            while (results.next()) {
                itemIDs.add(results.getString("ItemID"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return itemIDs;
    }


    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
