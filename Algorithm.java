package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Algorithm {
    private final int familyCalories;
    private final int familyWGCalories;
    private final int familyFVCalories;
    private final int familyProCalories;
    private final int familyOtherCalories;

    private ArrayList<Map<String, String>> bestHamper = new ArrayList<Map<String, String>>();

    public Algorithm(int calories, int wholeGrains, int fruitVeggies, int protein, int other) {
        this.familyCalories = calories;
        this.familyWGCalories = (int) (wholeGrains * calories * 0.01);
        this.familyFVCalories = (int) (fruitVeggies * calories * 0.01);
        this.familyProCalories = (int) (protein * calories * 0.01);
        this.familyOtherCalories = (int) (other * calories * 0.01);
    }

    // The checkCurrentHamper method checks if the waste of an inputted combination of food items is less than the waste
    // of the existing bestHamper. If it is, then the food combination becomes the best hamper.

    private void checkCurrentHamper(ArrayList<Map<String, String>> combination) {
        if (this.bestHamper.isEmpty()) this.bestHamper = new ArrayList<>(combination);
        else {
            Map<String, String> combinedBestHamper = combineHamperNutrition(this.bestHamper);
            Map<String, String> combinedCombination = combineHamperNutrition(combination);

            int bestHamperDiff = Integer.parseInt(combinedBestHamper.get("Calories")) - this.familyCalories;
            int combinationDiff = Integer.parseInt(combinedCombination.get("Calories")) - this.familyCalories;
            if (combinationDiff < bestHamperDiff) this.bestHamper = new ArrayList<>(combination);
        }
    }

    // The checkCombinationValidity method checks if the nutritional values are at least equal to the nutritional values
    // of the inputted family.

    private boolean checkCombinationValidity(ArrayList<Map<String, String>> combination) {
        Map<String, String> combinedHamper = combineHamperNutrition(combination);

        boolean wgValidity = Integer.parseInt(combinedHamper.get("GrainContent")) >= this.familyWGCalories;
        boolean fvValidity = Integer.parseInt(combinedHamper.get("FVContent")) >= this.familyFVCalories;
        boolean proValidity = Integer.parseInt(combinedHamper.get("ProContent")) >= this.familyProCalories;
        boolean otherValidity = Integer.parseInt(combinedHamper.get("Other")) >= this.familyOtherCalories;

        return wgValidity && fvValidity && proValidity && otherValidity;
    }

    // The findFoodCombinations method generates all possible combinations of the food items in a given database.

    private void findFoodCombinations(Database db, ArrayList<Map<String, String>> combination, int start, int end, int index, int combinationLength) {

        if (index == combinationLength) {
            if (checkCombinationValidity(combination)) checkCurrentHamper(combination);
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= combinationLength - index; i++) {
            try {
                combination.set(index, db.selectFoodItem(i));
            } catch (IndexOutOfBoundsException e) {
                combination.add(db.selectFoodItem(i));
            }
            findFoodCombinations(db, combination, i + 1, end, index + 1, combinationLength);
        }
    }

    public void findBestHamper(Database db) {
        ArrayList<Map<String, String>> combination = new ArrayList<>();
        for (int combinationLength = 1; combinationLength <= db.getItemLength(); combinationLength++) {
            findFoodCombinations(db, combination, 1, db.getItemLength(), 0, combinationLength);
        }
    }

    // The percentageToCalories method calculates the calories of a given nutrition.

    private int percentageToCalories(Map<String, String> foodItem, String nutrition) {
        return (int) (0.01 * Integer.parseInt(foodItem.get(nutrition)) * Integer.parseInt(foodItem.get("Calories"))); // Check validity of it later!
    }

    // The combineHamperNutrition method combines an ArrayList of foodItems into one HashMap

    private Map<String, String> combineHamperNutrition(ArrayList<Map<String, String>> combination) {
        Map<String, String> combinedHamper = new HashMap<>();

        int calories = 0;
        int grainContent = 0;
        int fvContent = 0;
        int proContent = 0;
        int other = 0;

        for (Map<String, String> foodItem : combination) {
            calories += Integer.parseInt(foodItem.get("Calories"));
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

    public ArrayList<Map<String, String>> getBestHamper() {
        return this.bestHamper;
    }

    public void printBestHamper() {
        System.out.println(combineHamperNutrition(this.bestHamper));
        for (Map<String, String> foodItem : this.bestHamper) {
            System.out.println(foodItem.get("Name"));
        }
    }

    public int getFamilyNutritionCalories(String nutrition) {
        switch (nutrition) {
            case "Calories" : return familyCalories;
            case "WholeGrains" : return familyWGCalories;
            case "FruitVeggies" : return familyFVCalories;
            case "Protein" : return familyProCalories;
            case "Other" : return familyOtherCalories;
            default: throw new IllegalArgumentException("Did not recognize input");
        }
    }
}