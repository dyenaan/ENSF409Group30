


enum Nutrition{
	
	PROTEIN{
		public String asString() { 
            return "Protein";
        }
	},
	WHOLEGRAINS{
		public String asString() { 
            return "WholeGrains";
        }
	},
	CALORIES{
		public String asString() { 
            return "Calories";
        }
	},
	FRUITSVEGGIES{
		public String asString() { 
            return "FruitsVeggies";
        }
	},
	OTHER{
		public String asString() { 
            return "Other";
        }
	};
	
	
	public abstract String asString();
}

