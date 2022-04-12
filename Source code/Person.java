package edu.ucalgary.ensf409;

import java.util.Map;

public class Person {
    private final int CLIENT_ID;
    private final int CALORIES;
    private final int WHOLE_GRAINS;
    private final int FRUIT_VEGGIES;
    private final int PROTEIN;
    private final int OTHER;

    Person(int clientID) {
        Database db = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        db.initializeConnection();
        Map<String, String> clientNeeds = db.selectClientNeeds(String.valueOf(clientID));
        db.close();

        this.CLIENT_ID = clientID;
        this.CALORIES = Integer.parseInt(clientNeeds.get("Calories"));
        this.WHOLE_GRAINS = Integer.parseInt(clientNeeds.get("WholeGrains"));
        this.FRUIT_VEGGIES = Integer.parseInt(clientNeeds.get("FruitVeggies"));
        this.PROTEIN = Integer.parseInt(clientNeeds.get("Protein"));
        this.OTHER = Integer.parseInt(clientNeeds.get("Other"));
    }

    public double getNutrition(NutritionTypes type) {
        switch (type) {
            case CALORIES:
                return this.CALORIES;
            case WHOLE_GRAINS:
                return this.WHOLE_GRAINS;
            case FRUIT_VEGGIES:
                return this.FRUIT_VEGGIES;
            case PROTEIN:
                return this.PROTEIN;
            case OTHER:
                return this.OTHER;
            default:
                throw new IllegalArgumentException("Did not recognize input");
        }
    }
    public int getClientID() {
        return this.CLIENT_ID;
    }
}
