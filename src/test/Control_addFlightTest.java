package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import flightControl.Control;
import flightControl.Flight;

class Control_addFlightTest {

	@Test
	public void addFlightTest() {
		Control c = Flight_sortTest.testControl();//has already 2 flights 
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalTime t3 = LocalTime.of(05,48);
		Flight f3 =new  Flight("France", "Paris", "paris-charles-de-gaulle", "El-Al", d1, t3, "LY001", 3, false);
		c.addFlight(f3);//so we add a third one and check if the function really did add the flight
		assertEquals(3,c.getFlights().size());
	}

}
