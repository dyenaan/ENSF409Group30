package edu.ucalgary.ensf409;

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
