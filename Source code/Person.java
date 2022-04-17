package edu.ucalgary.ensf409;

import java.util.Map;
import java.util.Objects;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.3
 * @since 1.0
 */

/*
 * The purpose of the Person class is to save the data of a client of a specific client ID.
 * All client data can be accessed via getters.
 */

public class Person {
    private final int CLIENT_ID;
    private final int CALORIES;
    private final int WHOLE_GRAINS;
    private final int FRUIT_VEGGIES;
    private final int PROTEIN;
    private final int OTHER;

    Person(int clientID) {
        if (!validateClientID(clientID)) throw new IllegalArgumentException();
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

    // Given a specific nutrition type, the getNutrition method returns the value of the nutrition for the client.

    public double getNutrition(String type) {
        if (Objects.equals(type, NutritionTypes.CALORIES.asString())) return this.CALORIES;
        else if (Objects.equals(type, NutritionTypes.WHOLE_GRAINS.asString())) return this.WHOLE_GRAINS;
        else if (Objects.equals(type, NutritionTypes.FRUIT_VEGGIES.asString())) return this.FRUIT_VEGGIES;
        else if (Objects.equals(type, NutritionTypes.PROTEIN.asString())) return this.PROTEIN;
        else if (Objects.equals(type, NutritionTypes.OTHER.asString())) return this.OTHER;
        else throw new IllegalArgumentException("Did not recognize input!");
    }

    // The validateClientID method returns true if the client ID exists within the ClientTypes enumeration. else returns false

    private boolean validateClientID(int clientID) {
        if (clientID == ClientTypes.MALE.clientID()) return true;
        else if (clientID == ClientTypes.FEMALE.clientID()) return true;
        else if (clientID == ClientTypes.CHILDOE.clientID()) return true;
        else if (clientID == ClientTypes.CHILDUE.clientID()) return true;
        else return false;
    }

    // The getClientID returns the client ID.

    public int getClientID() {
        return this.CLIENT_ID;
    }
}
