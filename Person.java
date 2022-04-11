
import java.util.*;

public class Person{
	
	private final int CLIENTID;
	private final int CALORIES;
	private final int FRUITVEGGIES;
	private final int PROTEIN;
	private final int WHOLEGRAINS;
	private final int OTHER;
	
	
	
	public Person(Database db, int clientID)throws IllegalArgumentException{
		
		if(!validClientID(clientID)){
			throw new IllegalArgumentException();
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map = db.selectClientNeeds(clientID.toString());
		
		this.CALORIES = Integer.parseInt(map.get("Calories"));
		this.FRUITVEGGIES = Integer.parseInt(map.get("FruitVeggies"));
		this.PROTEIN = Integer.parseInt(map.get("Protein"));
		this.WHOLEGRAINS = Integer.parseInt(map.get("WholeGrains"));
		this.OTHER = Integer.parseInt(map.get("Other"));
		
	}
	
	public int getNutrition(String type){
		
		type = type.toUpperCase();
		
		return this.Nutrition.type;//if we need to use nutrition
		// or return this.type;
		
	}
	
	public int getClientID(){
		
		return this.CLIENTID;
	}
	
	public boolean validClientID(int id){
		
		if(id != 1 || id != 2 || id != 3 || id != 4){
			return false
		}
		return true;
	}
	
}