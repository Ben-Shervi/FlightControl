package flightControl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Control {
	private ArrayList<Flight> flights = new ArrayList<Flight>();

	public boolean addFlight(Flight flight) {
		if (!flights.contains(flight)) {
			if (flights.add(flight)) {
				sort();
				return true;
			}
		}
		return false;
	}

	public String getArrivals() {
		StringBuffer str = new StringBuffer("Arrivals: \n");
		for (Flight f : flights) {
			if (f.isIncoming())
				str.append(f + "\n");
		}
		return str.toString();
	}

	public String getDepartures() {
		StringBuffer str = new StringBuffer("Departures: \n");
		for (Flight f : flights) {
			if (!f.isIncoming())
				str.append(f + "\n");
		}
		return str.toString();
	}

	private void sort() {
		for (int i = flights.size() - 1; i > 0; i--) {
			if (flights.get(i).isBefore(flights.get(i - 1))) {
				Flight temp = flights.get(i - 1);
				flights.set(i - 1, flights.get(i));
				flights.set(i, temp);
			} else if (flights.get(i).isIncoming() == flights.get(i - 1).isIncoming())
				i = 0;
		}
	}

	public ArrayList<Flight> search(boolean incoming, String airline, String country, String city, String airport,
			boolean[] days, LocalDate startDate, LocalDate endDate) {
		ArrayList<Flight> results = new ArrayList<Flight>();
		for (Flight f : flights) {
			boolean sameType = f.isIncoming() == incoming;
			boolean sameAirline = airline == null || f.getAirline().equalsIgnoreCase(airline);
			boolean sameCountry = country == null || f.getCountry().equalsIgnoreCase(country);
			boolean sameCity = city == null || f.getCity().equalsIgnoreCase(city);
			boolean sameAirport = airport == null || f.getAirport().equalsIgnoreCase(airport);
			boolean sameDays = days[f.getDate().getDayOfWeek().getValue() - 1];
			boolean sameDates1 = endDate == null || f.getDate().isBefore(endDate) || f.getDate().isEqual(endDate);
			boolean sameDates2 = startDate == null || f.getDate().isAfter(startDate) || f.getDate().isEqual(startDate);
			if (sameType && sameAirline && sameCountry && sameCity && sameAirport && sameDays && sameDates1
					&& sameDates2)
				results.add(f);
		}
		return results;
	}

	public void save(File file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		for (Flight f : flights)
			f.save(pw);
		pw.close();
	}

	public void load(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		while (scan.hasNext())
			addFlight(new Flight(scan));
		scan.close();
	}

	@Override
	public String toString() {
		return getArrivals() + getDepartures();
	}

}
