package edu.ucalgary.ensf409;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.0
 * @since 1.0
 */

/*
 * NutritionTypes is an enumeration that is used to have a consistent way of getting the names of each nutrition type
 * that exists in the mysql database.
 */

public enum NutritionTypes {
    CALORIES {
        public String asString() {
            return "Calories";
        }
    }, WHOLE_GRAINS {
        public String asString() {
            return "WholeGrains";
        }
    }, FRUIT_VEGGIES {
        public String asString() {
            return "FruitVeggies";
        }
    }, PROTEIN {
        public String asString() {
            return "Protein";
        }
    }, OTHER {
        public String asString () {
            return "Other";
        }
    };
    public abstract String asString();
}
