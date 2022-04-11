package edu.ucalgary.ensf409;

import java.io.*;

public class StockNotAvailableException extends Exception{
	public InvalidRewardsNumException(){
		super("Stock is not available.");
	}
}
