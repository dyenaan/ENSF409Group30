package edu.ucalgary.ensf409;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.0
 * @since 1.0
 */

/*
 * StockNotAvailableException when there isn't enough stock to get the best hamper
 */

public class StockNotAvailableException extends Exception{
    public StockNotAvailableException(String e) {
        super(e);
    }
}
