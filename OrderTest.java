/**
@author Ahmad Elshiltawi, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan
@version 1.4
@since 1.0
*/




package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest{

    @Test
    public void testEmptyOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> emptyOrderList = new ArrayList<>();

        try{
            Order order = new Order(emptyOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an empty order list.", correctException);
    }

    @Test
    public void testInvalidOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> invalidOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", "0");
        family.put("Female", "-1");
        family.put("ChildUE", "0");
        family.put("ChildOE", "0");
        invalidOrderList.add(family);

        try{
            Order order = new Order(invalidOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an invalid order list.", correctException);
    }

    @Test
    public void testMissingClientTypeOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> missingOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", "0");
        family.put("Female", "0");
        family.put("ChildUE", "0");
        missingOrderList.add(family);

        try{
            Order order = new Order(missingOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an order list with a missing client type.", correctException);
    }

    @Test
    public void testValidConstructor() throws HamperAlreadyFoundException, StockNotAvailableException {
        ArrayList<Map<String,String>> validOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", "1");
        family.put("Female", "1");
        family.put("ChildOE", "1");
        family.put("ChildUE", "1");
        validOrderList.add(family);
        Order order = new Order(validOrderList);
        assertNotNull("Order constructor did not create an object when given a valid count:", order);
    }

    @Test
    public void testGetFamilies() throws HamperAlreadyFoundException, StockNotAvailableException {
        ArrayList<Map<String,String>> validOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", "1");
        family.put("Female", "1");
        family.put("ChildOE", "1");
        family.put("ChildUE", "1");
        validOrderList.add(family);
        Order order = new Order(validOrderList);
        Family[] families = order.getFamilies();
        assertNotNull("Method getPeople did not return an object of type Family: " , families);
    }

    @Test
    public void testGetOrderCompleted() throws HamperAlreadyFoundException, StockNotAvailableException {
        ArrayList<Map<String,String>> validOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", "1");
        family.put("Female", "1");
        family.put("ChildOE", "1");
        family.put("ChildUE", "1");
        validOrderList.add(family);
        Order order = new Order(validOrderList);
        boolean orderCompleted = order.getOrderCompleted();
        assertNotNull("Method getPeople did not return an object of type Boolean: " , orderCompleted);
    }
}
