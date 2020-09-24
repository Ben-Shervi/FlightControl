package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import flightControl.Control;
import flightControl.Flight;

class Control_getArrivalsAndGetDeparturesTest {

	
	public Control setup() {
		Control c = Flight_sortTest.testControl() ;
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalTime t3 = LocalTime.of(05,48);
		LocalTime t4 = LocalTime.of(02, 14);
		Flight f3 = new Flight("France", "Paris", "paris-charles-de-gaulle", "El-Al", d1, t3, "LY789", 3, true);
		Flight f4 = new Flight("USA", "California", "LAX", "El-Al", d1, t4, "LY159", 3, true);
		c.addFlight(f3);
		c.addFlight(f4);
		return c;
		
	}
	
	@Test
	void TestForGetArrivalsAndDepartures() {
		Control c1 = setup();
		StringBuffer expectedArrivals = new StringBuffer("Arrivals: \n");
		expectedArrivals.append("Airline: El-Al, Coming from: USA, California, LAX, Date: 20/05/2020, Arrive at: 02:14, Flight: LY159, Terminal: 3\n" );
		expectedArrivals.append("Airline: El-Al, Coming from: France, Paris, paris-charles-de-gaulle, Date: 20/05/2020, Arrive at: 05:48, Flight: LY789, Terminal: 3\n" );
		StringBuffer expectedDepartures = new StringBuffer("Departures: \n");
		expectedDepartures.append(
				"Airline: El-Al, Departing to: USA, New-York, JFK, Date: 20/05/2020, Depart at: 00:45, Flight: LY001, Terminal: 3\n");
		expectedDepartures.append(
				"Airline: El-Al, Departing to: England, London, Heathrow, Date: 20/05/2020, Depart at: 10:10, Flight: LY315, Terminal: 3\n");
		assertEquals(expectedArrivals.toString(), c1.getArrivals());
		assertEquals(expectedDepartures.toString(),c1.getDepartures());
	}
	

}
