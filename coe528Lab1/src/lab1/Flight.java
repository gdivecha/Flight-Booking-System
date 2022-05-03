/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author Gaurav Divecha
 */
public class Flight{
    
    private int flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private int capacity;
    private int numberOfSeatsLeft;
    private double originalPrice;

    public Flight(int flightNumber, String origin, String destination, 
            String departureTime, int capacity, double originalPrice) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.numberOfSeatsLeft = capacity;
        this.originalPrice = originalPrice;
        if((origin.toLowerCase()).equals(destination.toLowerCase())){
            throw new IllegalArgumentException();
        }
        else{
            this.origin = origin;
            this.destination = destination;
        }
        if(capacity > numberOfSeatsLeft){
            this.capacity = capacity;
        }  
    }

    public int getFlightNumber(){
        return flightNumber;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDestination(){
        return destination;
    }

    public String getDepartureTime(){
        return departureTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfSeatsLeft(){
        return numberOfSeatsLeft;
    }

    public double getOriginalPrice(){
        return originalPrice;
    }

    public void setFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setNumberOfSeatsLeft(int numberOfSeatsLeft){
        this.numberOfSeatsLeft = numberOfSeatsLeft;
    }

    public void setOriginalPrice(double originalPrice){
        this.originalPrice = originalPrice;
    }
    
    public boolean bookASeat(){
        if((getNumberOfSeatsLeft())>0){
            setNumberOfSeatsLeft(getNumberOfSeatsLeft()-1);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String str = "Flight " + getFlightNumber() + ", " + getOrigin() + " to "
                + getDestination() + ", " + getDepartureTime() + ", original price: "
                + getOriginalPrice() + "$";
        return str;
    }
    
}
