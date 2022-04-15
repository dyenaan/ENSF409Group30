package edu.ucalgary.ensf409;


import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class FamilyTest{
	
	ArrayList<Map<String, String>> BEST_HAMPER = new ArrayList<Map<String, String>>();
	
	Map<String, String> hamp1= new HashMap<String, String>();
	Map<String, String> hamp2= new HashMap<String, String>();
	Map<String, String> hamp3= new HashMap<String, String>();
	
	ArrayList<String> Hamp_List = new ArrayList<String>();
	
	int mCount = 2;
	int invMCount = -2;
	int fCount = 2;
	int invFCount = -2;
	int uECount = 1; 
	int invUECount = -1;
	int oECount = 3;
	int invOECount = -3;
	
	@Test
	public void testFamilyConstructorInvalidInput() throws HamperAlreadyFoundException, StockNotAvailableException{
		
		boolean correctException = false;

        try{
			Family family = new Family(invMCount,fCount,uECount,oECount, Hamp_List);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }
		
		assertTrue("Person constructor did not throw an IllegalArgumentException when given a count less than 0: ", correctException);
    }
	
	@Test
	public void testFamilyConstructorValidInput() throws HamperAlreadyFoundException, StockNotAvailableException{
		
        Family family = new Family(mCount,fCount,uECount,oECount, Hamp_List);
        assertNotNull("Family constructor did not create an object when given a valid count:", family);
    }
	
	
	@Test
    public void testFamilyGetPeople() throws HamperAlreadyFoundException, StockNotAvailableException{
		
		Family nfamily = new Family(mCount,fCount,uECount,oECount, Hamp_List);
		Person[] people = nfamily.getPeople();
		
		assertNotNull("Method getPeople did not return an object of type person: " , people);
    }
	
	@Test
	public void testFamilyGetHamper() throws HamperAlreadyFoundException, StockNotAvailableException{
		
		hamp1.put("Tries", "Breaks");
		hamp1.put("Tries", "Breaks");
		hamp1.put("Tries", "Breaks");
		hamp1.put("Tries", "Breaks");
		hamp2.put("Tries", "Breaks");
		hamp2.put("Tries", "Breaks");
		hamp2.put("Tries", "Breaks");
		hamp3.put("Tries", "Breaks");
		hamp3.put("Tries", "Breaks");
		
		BEST_HAMPER.add(hamp1);
		BEST_HAMPER.add(hamp2);
		BEST_HAMPER.add(hamp3);
		
		for (Map<String, String> foodItem : BEST_HAMPER) {
            Hamp_List.add(foodItem.get("Tries"));
        }
		
		Family nfamily = new Family(mCount,fCount,uECount,oECount, Hamp_List);
		
		
		ArrayList<Map<String, String>> hmap;
		
		hmap = nfamily.getHamper();
		
		assertEquals("Method getHamper did not return the expected result: " ,BEST_HAMPER, hmap);
		
		
	}


}
	