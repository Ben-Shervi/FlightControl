package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import flightControl.Flight;

public class Flight_EqualsTest {
	@Test
	public void equalsTest() {
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalTime t1 = LocalTime.of(10, 10);
		Flight f1 = new Flight("Russia", "Moscow", "Sheremetyevo", "Aeroflot", d1, t1, "MO557", 3, false);
		Flight f2 = new Flight("Russia", "Moscow", "Sheremetyevo", "Aeroflot", d1, t1, "MO557", 3, false);
		assertEquals(true, f1.equals(f2));
	}
	
}
