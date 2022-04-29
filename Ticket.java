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
public class Ticket {
    
    private Passenger passenger;
    private Flight flight;
    private double price;
    private static int currentNum = 1;
    private int number;
    
    public Ticket(Passenger p, Flight flight, double price){
        this.passenger = p;
        this.flight = flight;
        this.price = price;
        this.number = currentNum;
        currentNum++;
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public Flight getFlight(){
        return flight;
    }

    public double getPrice(){
        return price;
    }

    public static int getCurrentNum(){
        return currentNum;
    }

    public int getNumber(){
        return number;
    }

    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public static void setCurrentNum(int currentNum){
        Ticket.currentNum = currentNum;
    }

    public void setNumber(int number){
        this.number = number;
    }
    
    @Override
    public String toString(){
        String str = getPassenger().getName() + ", " + getFlight().toString() + 
                ", ticket proce: $" + getPrice();
        return str;
    }
}
