/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gaurav Divecha
 */
public class Manager{
    
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void createFlights(){
        System.out.println("How many flights would you like to create?");
        int numFlights = 0;
        boolean error = false;
        do{
            error = false;
            try{
                numFlights = Integer.parseInt(scanner.nextLine());;
                if(numFlights<0)
                    throw new IllegalArgumentException();
            }catch(IllegalArgumentException exception){
                System.out.print("Please only input a valid number: ");
                error = true;
            }
        }while(error == true);
        System.out.println("");
        for(int i=0; i<numFlights; i++){
            boolean error2 = false;
            do{
                error2 = false;
                System.out.println("Please create Flight " + (i+1) + ":");
                System.out.print("Flight # (Non-zero and Unique): ");
                int flightNum = 0;
                boolean error3 = false;
                do{
                    error3 = false;
                    try{
                        flightNum = Integer.parseInt(scanner.nextLine());
                        boolean flightExists = false;
                        for(Flight f : flights){
                            if(f.getFlightNumber()==flightNum){
                                flightExists = true;
                            }
                        }
                        if(flightExists||flightNum<=0){
                            throw new IllegalArgumentException();
                        }
                    }catch(IllegalArgumentException exception){
                        System.out.print("Please input a valid flight number: ");
                        error3 = true;
                    }
                }while(error3 == true);
                System.out.print("Origin: ");
                String origin = scanner.nextLine();
                System.out.print("Destination: ");
                String destination = scanner.nextLine();
                System.out.print("Departure Details (\"MM/DD/YY, hh:mm am/pm\"): ");
                String departureDetails = scanner.nextLine();
                System.out.print("Capacity: ");
                int capacity = 0;
                boolean error5 = false;
                do{
                    error5 = false;
                    try{
                        capacity = Integer.parseInt(scanner.nextLine());
                        if(capacity<=0)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException exception){
                        System.out.print("Please only input integers: ");
                        error5 = true;
                    }
                }while(error5 == true);
                System.out.print("Original Price: ");
                double originalPrice = 0.00;
                boolean error6 = false;
                do{
                    error6 = false;
                    try{
                        originalPrice = Double.parseDouble(scanner.nextLine());
                        if(originalPrice<0)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException exception){
                        System.out.print("Please only input double values: ");
                        error6 = true;
                    }
                }while(error6 == true);
                Flight flight = null;
                try{
                    flight = new Flight(flightNum, origin, destination, departureDetails,
                                capacity, originalPrice);
                }
                catch(IllegalArgumentException exception){
                    System.out.println("");
                    System.out.println("The origin and destination are the same");
                    System.out.println("Please enter the information again!");
                    error2 = true;
                }
                if(error2 != true){
                    flights.add(flight);
                }
                System.out.println("");
            }while(error2 == true);
        }
    }
    public static void displayAvaliableFlights(String origin, String destination){
        System.out.println("The Flights availabe for " + origin.toUpperCase() + " to " + destination.toUpperCase() + ":");
        boolean flightExists = false;
        for(Flight f : flights){
            if((f.getOrigin().toLowerCase().equals(origin))&&((f.getDestination().toLowerCase()).equals(destination))){
                flightExists = true;
            }
        }
        if(flightExists == false)
            System.out.println("No such flights exist");
        for(Flight f : flights){
            if((f.getNumberOfSeatsLeft()>0)&&(f.getOrigin().toLowerCase().equals(origin))&&((f.getDestination().toLowerCase()).equals(destination))){
                System.out.println(f.toString());
            }
        }
    }
    public Flight getFlight(int flightNumber){
        for(Flight f : flights){
            if(f.getFlightNumber()==flightNumber){
                return f;
            }
        }
        return null;
    }
    public void bookSeat(int flightNumber, Passenger p){
        double finalPrice = getFlight(flightNumber).getOriginalPrice();
        if(p instanceof Member){
            finalPrice = p.applyDiscount(getFlight(flightNumber).getOriginalPrice());
            System.out.println("The final price will be: " + finalPrice);
        }
        else if(p instanceof NonMember){
            finalPrice = p.applyDiscount(getFlight(flightNumber).getOriginalPrice());
            System.out.println("The final price will be: " + finalPrice);
        }
        System.out.print("Are you sure? (Y-yes, N-no): ");
        int choice = scanner.nextLine().toLowerCase().charAt(0);
        switch(choice){
            case 'y':
                Ticket ticket = new Ticket(p, getFlight(flightNumber), finalPrice);
                tickets.add(ticket);
                System.out.println("You are all set!");
                break;
            case 'n':
                System.out.println("Succesfully exited");
                break;
        }
    }
    public static void main(String[] args) {
        Manager.createFlights();
        System.out.println("How many passengers will you be entering?");
        int numPassengers = 0;
        boolean error = false;
        do{
            error = false;
            try{
                numPassengers = Integer.parseInt(scanner.nextLine());
                if(numPassengers<0)
                    throw new IllegalArgumentException();
            }catch(IllegalArgumentException exception){
                System.out.print("Please only input integers: ");
                error = true;
            }
        }while(error == true);
        System.out.println("");
        for(int i=0; i<numPassengers; i++){
            System.out.println("Please enter passenger information:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = 0;
            boolean error2 = false;
            do{
                error2 = false;
                try{
                    age = Integer.parseInt(scanner.nextLine());
                    if(age<0)
                        throw new IllegalArgumentException();
                }catch(IllegalArgumentException exception){
                    System.out.print("Please only input integers: ");
                    error2 = true;
                }
            }while(error2 == true);
            System.out.println("Please enter the origin and destination:");
            String origin = "";
            String destination = "";
            boolean errorMid = false;
            do{
                errorMid = false;
                System.out.print("Origin: ");
                origin = scanner.nextLine();
                System.out.print("Destination: ");
                destination = scanner.nextLine();
                if(origin.toLowerCase().equals(destination.toLowerCase())){
                    System.out.println("Please enter different locations");
                    errorMid = true;
                }
            }while(errorMid == true);
            System.out.println("");
            displayAvaliableFlights(origin.toLowerCase(), destination.toLowerCase());
            System.out.println("");
            int prefFlightNum = 0;
            boolean error3 = false;
            do{
                System.out.print("Please enter the flight number of your preferred flight (If no such flights exist, enter 0): ");
                error3 = false;
                try{
                    prefFlightNum = Integer.parseInt(scanner.nextLine());
                    if(prefFlightNum<0)
                        throw new IllegalArgumentException();
                }catch(IllegalArgumentException exception){
                    System.out.print("Please only input integers: ");
                    error3 = true;
                }
                if(prefFlightNum == 0){
                    error3 = false;
                }
                else{
                    for(int k=0; k<flights.size(); k++){
                        if(flights.get(k).getFlightNumber()==prefFlightNum){
                            error3 = false;
                            k = flights.size();
                        }
                        else if(k==(flights.size()-1)){
                            error3 = true;
                            System.out.println("Please enter the correct number");
                        }
                    }
                }
            }while(error3 == true);
            if(prefFlightNum == 0){
                System.out.println("Sorry for the inconvenience");
                System.out.println("");
            }
            else{
                System.out.println("Are you a member? (Y-yes, N-no): ");
                char choice = 'n';
                boolean error4 = false;
                do{
                    error4 = false;
                    try{
                        choice = scanner.nextLine().toLowerCase().charAt(0);
                    }catch(IllegalArgumentException exception){
                        System.out.println("Please enter the correct letter!");
                        error4 = true;
                    }
                }while(error4 == true);
                Passenger p = null;
                switch(choice){
                    case 'y':
                        System.out.println("How many years of membership?");
                        int yearsOfMembership = 0;
                        boolean error6 = false;
                        do{
                            error6 = false;
                            try{
                                yearsOfMembership = Integer.parseInt(scanner.nextLine());
                            }
                            catch(IllegalArgumentException exception){
                                System.out.println("Please enter the correct letter!");
                                error6 = true;
                            }
                        }while(error6 == true);
                        p = new Member(name, age, yearsOfMembership);
                        break;
                    case 'n':
                        p = new NonMember(name, age);
                        break;
                }
                Manager m = new Manager();
                m.bookSeat(prefFlightNum, p);
                System.out.println("");
            }
        }
        System.out.println("The following list shows the Tickets booked:");
        int count = 1;
        for(Ticket t : tickets){
            System.out.println("Passenger #" + count + " Information:");
            System.out.println(t.toString());
            System.out.println("");
            count++;
        }
    }
}
