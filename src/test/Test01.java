package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import flightControl.Control;
import flightControl.Flight;

class Test01 {

	@Test
	public void sortTest() {
		Control c = testControl();
		StringBuffer statment = new StringBuffer("Departures: \n");
		statment.append(
				"Airline: El-Al, Departing to: USA, New-York, JFK, Date: 20/05/2020, Depart at: 00:45, Flight: LY001, Terminal: 3\n");
		statment.append(
				"Airline: El-Al, Departing to: England, London, Heathrow, Date: 20/05/2020, Depart at: 10:10, Flight: LY315, Terminal: 3\n");
		assertEquals(statment.toString(), c.getDepartures());
		;
	}

	public static Control testControl() {
		Control c = new Control();
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalTime t1 = LocalTime.of(10, 10);
		LocalTime t2 = LocalTime.of(00, 45);
		Flight f1 = new Flight("England", "London", "Heathrow", "El-Al", d1, t1, "LY315", 3, false);
		Flight f2 = new Flight("USA", "New-York", "JFK", "El-Al", d1, t2, "LY001", 3, false);
		c.addFlight(f1);
		c.addFlight(f2);
		return c;
	}

}
