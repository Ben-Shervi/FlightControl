package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import flightControl.Flight;

public class Flight_toStringTest {
	@Test
	public void toStringTest() {
		LocalDate d1 = LocalDate.of(2019, 03, 05);
		LocalTime t1 = LocalTime.of(10, 10);
		Flight f1 = new Flight("Russia", "Moscow", "Sheremetyevo", "Aeroflot", d1, t1, "MO557", 3, false);
		String str= "Airline: Aeroflot, Departing to: Russia, Moscow, Sheremetyevo, Date: 05/03/2019, Depart at: 10:10, Flight: MO557, Terminal: 3";
		assertEquals(str, f1.toString());
	}
}
