/**
     * @Author: Pansilu Wickramasinghe
     * @Version: 1.0
     * Group: 30
     */




package edu.ucalgary.ensf409;


public class Order {
    
	private Families[] families;
    private TotalHampers[] totalhampers;
	private int maleCount;
	private int femaleCount;
	private int childUEount;
	private int childOECount;
	
	public Order(int maleCount, int femaleCount, int childUECount, int childOECount){
		
		this.maleCount = maleCount;
		this.femaleCount = femaleCount;
		this.childUECount = childUECount;
		this.childOECount = childOECount;
		
		
	}
	
	
	public Families[] getFamilies(){ 
		
		return families; 
		
	}
	
    public TotalHampers[] getTotalHampers(){   //I think this is wrong because it should be an arraylist
	
		return totalhampers; 
		
	}
}

