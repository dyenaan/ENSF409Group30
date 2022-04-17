package edu.ucalgary.ensf409;


import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.3
 * @since 1.0
 */

/*
 * The purpose of the FamilyTest class is to test the functionality of the Algorithm class.
 *
 * Important note: There must be enough food items for at least one adult male for the tests to work properly!
 * This is because the family class calls the Algorithm class to find the best hamper. If there isn't enough stock,
 * then the test will error out (which is intended).
 */

public class FamilyTest{
	
	ArrayList<Map<String, String>> BEST_HAMPER = new ArrayList<Map<String, String>>();
	ArrayList<String> currentUsedItems = new ArrayList<String>();
	
	int mCount = 1;
	int invMCount = -2; // Invalid male count
	int fCount = 0;
	int uECount = 0;
	int oECount = 0;


	// The testFamilyConstructorInvalidInput tests the constructor class when given an invalid input.

	@Test
	public void testFamilyConstructorInvalidInput() throws HamperAlreadyFoundException, StockNotAvailableException{
		
		boolean correctException = false;

        try{
			Family family = new Family(invMCount,fCount,uECount,oECount, currentUsedItems);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }
		
		assertTrue("Person constructor did not throw an IllegalArgumentException when given a count less than 0: ", correctException);
    }

	// The testFamilyConstructorValidInput tests the constructor class when given a valid input.

	@Test
	public void testFamilyConstructorValidInput() throws HamperAlreadyFoundException, StockNotAvailableException{
		
        Family family = new Family(mCount,fCount,uECount,oECount, currentUsedItems);
        assertNotNull("Family constructor did not create an object when given a valid count:", family);
    }

	// The testFamilyGetPeople method tests the getPeople getter

	@Test
    public void testFamilyGetPeople() throws HamperAlreadyFoundException, StockNotAvailableException{
		
		Family nfamily = new Family(mCount,fCount,uECount,oECount, currentUsedItems);
		Person[] people = nfamily.getPeople();
		
		assertNotNull("Method getPeople did not return an object of type person: " , people);
    }

	// The testFamilyGetHamper method tests the getHamper getter

	@Test
	public void testFamilyGetHamper() throws HamperAlreadyFoundException, StockNotAvailableException{
		Family nfamily = new Family(mCount,fCount,uECount,oECount, new ArrayList<String>());
		nfamily.findBestHamper();

		ArrayList<Map<String, String>> bestHamper = nfamily.getHamper();
		assertNotNull("Method getHamper did not return the best hamper: " , bestHamper);
	}

	// The testFamilyValidGetClientCount method tests the getClientCount getter if the input is valid

	@Test
	public void testFamilyValidGetClientCount() throws HamperAlreadyFoundException, StockNotAvailableException{
		Family nfamily = new Family(mCount,fCount,uECount,oECount, new ArrayList<String>());
		int maleCount = nfamily.getClientCount(ClientTypes.MALE.asString());
		int femaleCount = nfamily.getClientCount(ClientTypes.FEMALE.asString());
		int childOECount = nfamily.getClientCount(ClientTypes.CHILDOE.asString());
		int childUECount = nfamily.getClientCount(ClientTypes.CHILDUE.asString());

		assertEquals("The male count value does not match the right value: ", maleCount, mCount);
		assertEquals("The female count value does not match the right value: ", femaleCount, fCount);
		assertEquals("The childUE count value does not match the right value: ", childUECount, uECount);
		assertEquals("The childOE count value does not match the right value: ", childOECount, oECount);
	}

	// The testFamilyValidGetClientCount method tests the getClientCount getter if the input is invalid

	@Test
	public void testFamilyInvalidGetClientCount() throws HamperAlreadyFoundException, StockNotAvailableException{
		boolean correctException = false;
		Family nfamily = new Family(mCount,fCount,uECount,oECount, new ArrayList<String>());


		try{
			int count = nfamily.getClientCount("Invalid");
		}
		catch(IllegalArgumentException e){
			correctException = true;
		}

		assertTrue("Person constructor did not throw an IllegalArgumentException when given a count less than 0: ", correctException);
	}

}
	