/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gaurav Divecha
 */
public class FlightTest{
    
    public FlightTest(){}
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    @Before
    public void setUp(){
    }
    
    @After
    public void tearDown(){
    }
    
    @Test
    public void testConstructor(){
        Flight lufthansa = new Flight(747, "Toronto", "Kolkata", "03/02/99 7:50 pm", 150, 1000.0);
        assertEquals(lufthansa.getFlightNumber(), 747);
        assertTrue(lufthansa.getOrigin().equals("Toronto"));
        assertTrue(lufthansa.getDestination().equals("Kolkata"));
        assertTrue(lufthansa.getDepartureTime().equals("03/02/99 7:50 pm"));
        assertEquals(lufthansa.getCapacity(), 150);
        assertEquals(lufthansa.getNumberOfSeatsLeft(), 150);
        assertTrue(lufthansa.getOriginalPrice()==1000.0);
    }
    
    @Test
    public void testInvalidConstructor(){
        Flight airCanada = null;
        boolean error = false;
        try{
            airCanada = new Flight(398, "Munich", "Munich", "09/01/22 7:30 am", 150, 750.0);
        }
        catch(IllegalArgumentException i){
            error = true;
        }
        assertEquals(error, true);
    }

    @Test
    public void testBookandString(){
        Flight britishAirways = new Flight(123, "London", "Liverpool", "31/02/19 3:30 pm", 300, 1200.0);
        //Checks bookASeat() method
        boolean bookedSeat = britishAirways.bookASeat();
        assertFalse(britishAirways.getNumberOfSeatsLeft()==300);
        assertTrue(britishAirways.getNumberOfSeatsLeft()==299);
        //Checks toString() method
        String str = "Flight 123, London to Liverpool, 31/02/19 3:30 pm, original price: 1200.0$";
        assertTrue((britishAirways.toString()).equals(str));
    }
}
