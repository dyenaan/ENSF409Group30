package edu.ucalgary.ensf409;

import java.util.*;

public class Family{
    private final Person[] PEOPLE;
	private final int MALE_COUNT;
	private final int FEMALE_COUNT;
	private final int CHILDUE_COUNT;
	private final int CHILDOE_COUNT;
    private ArrayList<String> currentUsedItemIDs;
    private ArrayList<Map<String, String>> bestHamper;

	public Family(int maleCount, int femaleCount, int childUECount, int childOECount, ArrayList<String> currentUsedItemIDs) {
        if (!validateCounts(maleCount, femaleCount, childOECount, childUECount)) throw new IllegalArgumentException();

        this.MALE_COUNT = maleCount;
        this.FEMALE_COUNT = femaleCount;
        this.CHILDUE_COUNT = childOECount;
        this.CHILDOE_COUNT = childUECount;
        this.currentUsedItemIDs = currentUsedItemIDs;

        int j = 0;
        PEOPLE = new Person[maleCount + femaleCount + childUECount + childOECount];
		for (int i = 0; i < MALE_COUNT; i++) {
            PEOPLE[j] = new Person(ClientTypes.MALE.clientID());
            j++;
        }
        for (int i = 0; i < FEMALE_COUNT; i++) {
            PEOPLE[j] = new Person(ClientTypes.FEMALE.clientID());
            j++;
        }
        for (int i = 0; i < CHILDUE_COUNT; i++) {
            PEOPLE[j] = new Person(ClientTypes.CHILDUE.clientID());
            j++;
        }
        for (int i = 0; i < CHILDOE_COUNT; i++) {
            PEOPLE[j] = new Person(ClientTypes.CHILDOE.clientID());
            j++;
        }
	}

    public void findBestHamper() throws HamperAlreadyFoundException, StockNotAvailableException {
        Algorithm a = new Algorithm(PEOPLE, currentUsedItemIDs);
        this.bestHamper = a.getBestHamper();

        for (Map<String, String> foodItem : bestHamper) {
            currentUsedItemIDs.add(foodItem.get("ItemID"));
        }
    }

	public Person[] getPeople(){
		return this.PEOPLE;
	}

	public ArrayList<Map<String, String>> getHamper(){
		return this.bestHamper;
	}

    public int getClientCount(String type) {
        if (type.equals(ClientTypes.MALE.asString())) return this.MALE_COUNT;
        else if (type.equals(ClientTypes.FEMALE.asString())) return this.FEMALE_COUNT;
        else if (type.equals(ClientTypes.CHILDUE.asString())) return this.CHILDUE_COUNT;
        else if (type.equals(ClientTypes.CHILDOE.asString())) return this.CHILDOE_COUNT;
        else throw new IllegalArgumentException("Did not recognize input");
    }

    public ArrayList<String> getCurrentUsedItemIDs() {
        return this.currentUsedItemIDs;
    }

    private boolean validateCounts(int male, int female, int childOE, int childUE) {
        return male >= 0 && female >= 0 && childOE >= 0 && childUE >= 0;
    }
}