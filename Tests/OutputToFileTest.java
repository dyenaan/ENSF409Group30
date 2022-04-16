package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.0
 * @since 1.0
 */

/*
 *   The purpose of the OutputToFileTest is to test out the functionality of the OutputToFile class
 */

public class OutputToFileTest {
    private String invalidOrderID = null;
    private String validOrderID = "11111111";
    private String validFilename = "orderform-11111111.txt";

    // Tests if the constructor works or not given a valid order ID

    @Test
    public void testOutputToFileConstructor() {
        OutputToFile otf = new OutputToFile(validOrderID);
        assertNotNull("OutputToFile constructor did not create an object when given a order ID:", otf);
    }

    // Tests if the constructor throws an IllegalArgumentException or not if the Order ID is invalid

    @Test
    public void testOutputToFileNullOrderIDConstructor() {
        boolean correctException = false;
        try{
            OutputToFile otf = new OutputToFile(invalidOrderID);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("OutputToFile constructor did not throw an IllegalArgumentException when an invalid order ID: ", correctException);
    }

    // Tests if the getFilename getter returns the filename

    @Test
    public void testOutputToFileGetFilename() {
        OutputToFile otf = new OutputToFile(validOrderID);
        String filename = otf.getFilename();
        assertEquals("The filename does not equal the expected file name!", filename, this.validFilename);
    }

    // Tests if the getOrderID getter returns the order ID

    @Test
    public void testOutputToFileGetOrderID() {
        OutputToFile otf = new OutputToFile(validOrderID);
        String orderID = otf.getOrderID();
        assertEquals("The filename does not equal the expected file name!", orderID, this.validOrderID);
    }
}
