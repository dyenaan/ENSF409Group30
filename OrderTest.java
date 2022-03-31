/**
     * @Author: Pansilu Wickramasinghe
     * @Version: 1.0
     * Group: 30
     */


package edu.ucalgary.ensf409;


import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest{

@Test
    public void testOrderConstructorInvalidInput() {
        boolean correctException = false;
        try{
            Order order = new Order(-1, -2, -3, -4);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Order constructor did not throw an IllegalArgumentException for invalid number of people: ", correctException);
    }
	
	
@Test
	public void testOrderConstructorValidInput() {
		
        Order order = new order(1,2,3,4);
        assertNotNull("Order constructor did not create an object when given a valid count:", order);
    }
	
@Test
    public void testOrderGetFamilies() {
		
		Order order = new order(1,2,3,4);
		Family[] family = order.getFamilies();
		
		assertNotNull("Method getFamilies did not return an object of type family: " , family);
    }
	
	@Test
	public void testOrderGetTotalHampers() {
		
		Order order = new order(1,2,3,4);
		List<HashMap<String, Integer>> hmap = new ArrayList<HashMap<String, Integer>>();
		
		
		hmap = order.getTotalHampers();
		
		assertNotNull("Method getTotalHamper did not return an object of type HashMap: " ,hmap);
		
		
	}
	
}

