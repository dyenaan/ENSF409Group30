package edu.ucalgary.ensf409;

import java.util.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.4
 * @since 1.0
 */

/*
 * The purpose of the Family class is store a group of person objects and to create the best hamper for these person objects.
 */

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

    // The findBestHamper method uses the algorithm class to find the best hamper and stores it in the bestHamper variable

    public void findBestHamper() throws HamperAlreadyFoundException, StockNotAvailableException {
        Algorithm a = new Algorithm(PEOPLE, currentUsedItemIDs);
        this.bestHamper = a.getBestHamper();

        for (Map<String, String> foodItem : bestHamper) {
            currentUsedItemIDs.add(foodItem.get("ItemID"));
        }
    }

    // The getPeople method returns the people array

	public Person[] getPeople(){
		return this.PEOPLE;
	}

    // The getHamper method returns bestHamper

	public ArrayList<Map<String, String>> getHamper(){
		return this.bestHamper;
	}

    // given the client type, the getClientCount method returns the amount of this client in the family.

    public int getClientCount(String type) {
        if (type.equals(ClientTypes.MALE.asString())) return this.MALE_COUNT;
        else if (type.equals(ClientTypes.FEMALE.asString())) return this.FEMALE_COUNT;
        else if (type.equals(ClientTypes.CHILDUE.asString())) return this.CHILDUE_COUNT;
        else if (type.equals(ClientTypes.CHILDOE.asString())) return this.CHILDOE_COUNT;
        else throw new IllegalArgumentException("Did not recognize input");
    }

    // The getCurrentUsedItemIDs returns currentUsedItemIDs

    public ArrayList<String> getCurrentUsedItemIDs() {
        return this.currentUsedItemIDs;
    }

    // The validateCounts method returns true if all client counts are 0 or bigger, else returns false.

    private boolean validateCounts(int male, int female, int childOE, int childUE) {
        return male >= 0 && female >= 0 && childOE >= 0 && childUE >= 0;
    }
}