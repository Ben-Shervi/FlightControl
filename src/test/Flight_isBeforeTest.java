package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import flightControl.Flight;

public class Flight_isBeforeTest {
	
	@Test
	void TestForIsBeforeFun() {
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalDate d2 = LocalDate.of(2020, 06, 10);
		LocalTime t1 = LocalTime.of(10, 10);
		LocalTime t2 = LocalTime.of(00, 45);
		Flight f1 = new Flight("England", "London", "Heathrow", "El-Al", d1, t1, "LY315", 3, false);
		Flight f2 = new Flight("USA", "New-York", "JFK", "El-Al", d1, t2, "LY001", 3, false);
		Flight f3 = new Flight("France", "Paris", "CDG", "Air_france", d2, t2, "AF 1321", 3, true);
		boolean check=f1.isBefore(f2);
		boolean check2=f1.isBefore(f3);
		assertEquals(false, check);
		assertEquals(true, check2);
	}
}
