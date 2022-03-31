package edu.ucalgary.ensf409;


import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class FamilyTest{
	
	
	int mCount = 2;
	int invMCount = -2;
	int fCount = 2;
	int invFCount = -2;
	int uECount = 1; 
	int invUECount = -1; 
	int oECount = 3;
	int invOECount = -3;
	
	@Test
	public void testFamilyConstructorInvalidInput() {
		
		boolean correctException = false;

        try{
			Family family = new Family(invMCount,invFCount,invUECount,invOECount);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }
		
		assertTrue("Person constructor did not throw an IllegalArgumentException when given a count less than 0: ", correctException);
    }
	
	@Test
	public void testFamilyConstructorValidInput() {
		
        Family family = new Family(mCount,fCount,uECount,oECount);
        assertNotNull("Family constructor did not create an object when given a valid count:", family);
    }
	
	
	@Test
    public void testFamilyGetPeople() {
		
		Family nfamily = new Family(mCount,fCount,uECount,oECount);
		Person[] people = nfamily.getPeople();
		
		assertNotNull("Method getPeople did not return an object of type person: " , people);
    }
	
	@Test
	public void testFamilyGetHamper() {
		
		Family nfamily = new Family(mCount,fCount,uECount,oECount);
		List<HashMap<String, Integer>> hmap = new ArrayList<HashMap<String, Integer>>();
		
		
		hmap = nfamily.getHamper();
		
		assertNotNull("Method getHamper did not return an object of type HashMap: " ,hmap);
		
		
	}


}
	