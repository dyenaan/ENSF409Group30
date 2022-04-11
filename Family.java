package edu.ucalgary.ensf409;

import java.util.*;
import java.io.*;

public class Family{
	private Person[] people;
	private HashMap[] hamper;
	
	private GUIOrderID order = new GUIOrderID();
	
	private int maleCount;
	private int femaleCount;
	private int childUEount;
	private int childOECount;
	
	public Family(int order.getMaleCount, int order.getFemaleCount, int order.getChildUECount, int order.getChildOECount){
		
		this.maleCount = order.getMaleCount;
		this.femaleCount = order.getChildUECount;
		this.childUEount = order.getChildUECount;
		this.childOECount = order.getChildOECount;
		
		int totalPerson = order.getMaleCount + order.getChildUECount + order.getChildUECount + order.getChildOECount;
		people = new Person[totalPerson];
	}
	
	public Family(int maleCount, int femaleCount, int childUECount, int childOECount){
		this.maleCount = people.getClientID(maleCount);
		this.femaleCount = people.getClientID(femaleCount);
		this.childUECount = people.getClientID(childUECount);
		this.childOECount = people.getClientID(childOECount);
	}
	
	public Family(int maleCount, int femaleCount, int childUECount, int childOECount){
		this.maleCount = (maleCount);
		this.femaleCount = (femaleCount);
		this.childUECount = (childUECount);
		this.childOECount = (childOECount);
	}
	
	public Person[] getPeople(){
		return this.people;
	}
	
	public HashMap[] getHamper(){
		return this.hamper;
	}
}