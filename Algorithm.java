package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
*   The purpose of the following algorithm is to find the best hamper possible given a database of food items.
*
*   The algorithm requires an array of person objects so that it could calculate the total amount of nutritional contents
*   needed.
*
*   The method findBestHamper needs to be called so that the program finds the best hamper. The findBestHamper
*   method creates every food combination possible. The method then looks for the best food combination possible
*   and saves it to the bestHamper variable. The method uses multiple helper methods to find the best hamper, including
*   findFoodCombinations, checkCombinationValidity, and checkCurrentHamper.
*
*   Descriptions of said helper methods:
*
*   checkCombinationValidity: Checks if the combination at least satisfies the nutritional contents needed.
*
*   findFoodCombinations: Generates all possible combination of foods of a given length.
*
*   checkCurrentCombination: Checks if the current combination creates less waste than the current best hamper or not.
*   If the current combination creates less weight, then it becomes the best hamper.
*/

class Algorithm {
    private final double familyCalories;
    private final double familyWGCalories;
    private final double familyFVCalories;
    private final double familyProCalories;
    private final double familyOtherCalories;

    private ArrayList<Map<String, String>> bestHamper = new ArrayList<>();

    // @TODO Change it so that the constructor accepts an array of person objects.
    public Algorithm(int calories, int wholeGrains, int fruitVeggies, int protein, int other) {
        this.familyCalories = calories;
        this.familyWGCalories = wholeGrains * calories * 0.01;
        this.familyFVCalories = fruitVeggies * calories * 0.01;
        this.familyProCalories = protein * calories * 0.01;
        this.familyOtherCalories = other * calories * 0.01;
    }

    public ArrayList<Map<String, String>> getBestHamper() {
        return this.bestHamper;
    }

    // @TODO Use enumeration to implement the getter properly!
    public double getFamilyNutritionCalories(String nutrition) {
        switch (nutrition) {
            case "Calories":
                return familyCalories;
            case "WholeGrains":
                return familyWGCalories;
            case "FruitVeggies":
                return familyFVCalories;
            case "Protein":
                return familyProCalories;
            case "Other":
                return familyOtherCalories;
            default:
                throw new IllegalArgumentException("Did not recognize input");
        }
    }

    // The findBestHamper method calls the appropriate methods to generate the best hamper possible. It also checks
    // if there is enough stock and deletes the items from the database if a hamper is possible.

    public ArrayList<Map<String, String>> findBestHamper(Database db) throws StockNotAvailableException {
        ArrayList<Map<String, String>> combination = new ArrayList<>();

        for (int combinationLength = 1; combinationLength <= db.getItemLength(); combinationLength++) {
            findFoodCombinations(db, combination, 0, db.getItemLength() - 1, 0, combinationLength);
        }
        if (this.bestHamper.isEmpty())
            throw new StockNotAvailableException("The available stuck isn't sufficient enough to create a hamper!");

        deleteHamperFromDatabase(db, this.bestHamper);
        return this.bestHamper;
    }

    // The checkCurrentHamper method checks if the waste of an inputted combination of food items is less than the waste
    // of the existing bestHamper. If it is, then the food combination becomes the best hamper.

    private void checkCurrentCombination(ArrayList<Map<String, String>> combination) {
        if (this.bestHamper.isEmpty()) this.bestHamper = new ArrayList<>(combination);
        else {
            Map<String, String> combinedBestHamper = combineHamperNutrition(this.bestHamper);
            Map<String, String> combinedCombination = combineHamperNutrition(combination);

            double bestHamperDiff = Double.parseDouble(combinedBestHamper.get("Calories")) - this.familyCalories;
            double combinationDiff = Double.parseDouble(combinedCombination.get("Calories")) - this.familyCalories;
            if (combinationDiff < bestHamperDiff) this.bestHamper = new ArrayList<>(combination);
        }
    }

    // The checkCombinationValidity method checks if the nutritional values are at least equal to the nutritional values
    // of the inputted family.

    private boolean checkCombinationValidity(ArrayList<Map<String, String>> combination) {
        Map<String, String> combinedHamper = combineHamperNutrition(combination);

        boolean wgValidity = Double.parseDouble(combinedHamper.get("GrainContent")) >= this.familyWGCalories;
        boolean fvValidity = Double.parseDouble(combinedHamper.get("FVContent")) >= this.familyFVCalories;
        boolean proValidity = Double.parseDouble(combinedHamper.get("ProContent")) >= this.familyProCalories;
        boolean otherValidity = Double.parseDouble(combinedHamper.get("Other")) >= this.familyOtherCalories;

        return wgValidity && fvValidity && proValidity && otherValidity;
    }

    // The findFoodCombinations method generates all possible combinations of the food items in a given database.

    private void findFoodCombinations(Database db, ArrayList<Map<String, String>> combination, int start, int end, int index, int combinationLength) {

        if (index == combinationLength) {
            if (checkCombinationValidity(combination)) checkCurrentCombination(combination);
            return;
        }

        String[] itemIDs = db.getItemIDs();
        for (int i = start; i <= end && end - i + 1 >= combinationLength - index; i++) {
            try {
                combination.set(index, db.selectFoodItem(itemIDs[i]));
            } catch (IndexOutOfBoundsException e) {
                combination.add(db.selectFoodItem(itemIDs[i]));
            } catch (IllegalArgumentException ignore) {
            }
            findFoodCombinations(db, combination, i + 1, end, index + 1, combinationLength);
        }
    }

    // The percentageToCalories method calculates the calories of a given nutrition.

    private double percentageToCalories(Map<String, String> foodItem, String nutrition) {
        return 0.01 * Integer.parseInt(foodItem.get(nutrition)) * Integer.parseInt(foodItem.get("Calories"));
    }

    // The combineHamperNutrition method combines an ArrayList of foodItems into one HashMap

    private Map<String, String> combineHamperNutrition(ArrayList<Map<String, String>> combination) {
        Map<String, String> combinedHamper = new HashMap<>();

        double calories = 0;
        double grainContent = 0;
        double fvContent = 0;
        double proContent = 0;
        double other = 0;

        for (Map<String, String> foodItem : combination) {
            calories += Double.parseDouble(foodItem.get("Calories"));
            grainContent += percentageToCalories(foodItem, "GrainContent");
            proContent += percentageToCalories(foodItem, "ProContent");
            fvContent += percentageToCalories(foodItem, "FVContent");
            other += percentageToCalories(foodItem, "Other");
        }

        combinedHamper.put("Calories", String.valueOf(calories));
        combinedHamper.put("GrainContent", String.valueOf(grainContent));
        combinedHamper.put("ProContent", String.valueOf(proContent));
        combinedHamper.put("FVContent", String.valueOf(fvContent));
        combinedHamper.put("Other", String.valueOf(other));

        return combinedHamper;
    }

    // The deleteHamperFromDatabase method deletes a given hamper from the database

    private void deleteHamperFromDatabase(Database db, ArrayList<Map<String, String>> hamper) {
        for (Map<String, String> foodItem : hamper) {
            db.deleteFoodItem(foodItem.get("ItemID"));
        }
    }

    // The printBestHamper method prints the best hamper

    public void printBestHamper() {
        System.out.println(combineHamperNutrition(this.bestHamper));
        for (Map<String, String> foodItem : this.bestHamper) {
            System.out.println(foodItem.get("Name"));
        }
    }
}