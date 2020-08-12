package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import flightControl.Flight;

public class Flight_SaveTest {

	@Test
	 void test() throws FileNotFoundException {
		File f = new File("FileForTests2");
		Scanner scan = new Scanner(f);
		PrintWriter pw = new PrintWriter(f);
		LocalDate d1 = LocalDate.of(2020, 05, 20);
		LocalTime t1 = LocalTime.of(10, 10);
		Flight f1 = new Flight("England", "London", "Heathrow", "El-Al", d1, t1, "LY315", 3, false);
		f1.save(pw);
		pw.close();
		Flight f2 = new Flight(scan);
		assertEquals(f1.toString(), f2.toString());
		
	}

}
