package edu.ucalgary.ensf409;

import java.util.*;

public class Family{
	private final Person[] PEOPLE;
	private final ArrayList<Map<String, String>> BEST_HAMPER;
	private final int MALE_COUNT;
	private final int FEMALE_COUNT;
	private final int CHILDUE_COUNT;
	private final int CHILDOE_COUNT;

    //private GUIOrderID order = new GUIOrderID();

	public Family(int maleCount, int femaleCount, int childUECount, int childOECount) throws HamperAlreadyFoundException, StockNotAvailableException {

        this.MALE_COUNT = maleCount;
        this.FEMALE_COUNT = femaleCount;
        this.CHILDUE_COUNT = childOECount;
        this.CHILDOE_COUNT = childUECount;

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

        Algorithm a = new Algorithm(PEOPLE);
        this.BEST_HAMPER = a.getBestHamper();
	}

	public Person[] getPeople(){
		return this.PEOPLE;
	}

	public ArrayList<Map<String, String>> getHamper(){
		return this.BEST_HAMPER;
	}

    public int getClientCount(ClientTypes type) {
        switch (type) {
            case MALE:
                return this.MALE_COUNT;
            case FEMALE:
                return this.FEMALE_COUNT;
            case CHILDOE:
                return this.CHILDOE_COUNT;
            case CHILDUE:
                return this.CHILDUE_COUNT;
            default:
                throw new IllegalArgumentException("Did not recognize input");
        }
    }

    public void printBestHamper() {
        for (Map<String, String> foodItem : this.BEST_HAMPER) {
            System.out.println(foodItem.get("Name"));
        }
    }
}