package edu.ucalgary.ensf409;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.0
 * @since 1.0
 */

/*
 * HamperAlreadyFoundException is a custom exception that is thrown when the hamper is already found and the method
 * findBestHamper is called.
 */

public class HamperAlreadyFoundException extends Exception{
    public HamperAlreadyFoundException(String e) {
        super(e);
    }
}