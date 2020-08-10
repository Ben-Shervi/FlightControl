package flightsList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Control {
	private Flight[] departures = new Flight[2];
	private Flight[] arrivals = new Flight[2];
	private int inFlight;
	private int outFlight;

	public boolean addFlight(Flight flight) {
		Flight[] tempArr = flight.isIncoming() ? arrivals : departures;
		boolean ok = false;
		if (tempArr[0] != null && tempArr[tempArr.length - 1] == null) {
			for (int i = tempArr.length / 2; i < tempArr.length; i++) {
				if (tempArr[i] == null) {
					tempArr[i] = new Flight(flight);
					ok = true;
				}
			}
		} else if (tempArr[tempArr.length - 1] != null) {
			tempArr = Arrays.copyOf(tempArr, tempArr.length * 2);
			tempArr[tempArr.length / 2] = new Flight(flight);
			ok = true;
		} else {
			tempArr[0] = new Flight(flight);
			ok = true;
		}

		if (flight.isIncoming()) {
			arrivals = tempArr;
			inFlight++;
		} else {
			departures = tempArr;
			outFlight++;
		}
		sort(flight.isIncoming());
		return ok;
	}

	public String getArrivals() {
		StringBuffer str = new StringBuffer("Arrivals: \n");
		for (int i = 0; i < arrivals.length; i++) {
			if (arrivals[i] != null)
				str.append(arrivals[i].toString() + "\n");
		}
		return str.toString();
	}

	public String getDepartures() {
		StringBuffer str = new StringBuffer("Departures: \n");
		for (int i = 0; i < departures.length; i++) {
			if (departures[i] != null)
				str.append(departures[i].toString() + "\n");
		}
		return str.toString();
	}

	private void sort(boolean isIn) {
		Flight[] tempArr = isIn ? arrivals : departures;
		for (int j = 0, k = isIn ? inFlight : outFlight; j <= k; j++) {
			for (int i = 0; i < tempArr.length; i++) {
				if (tempArr[i] != null && i < (tempArr.length - 1) && tempArr[i + 1] != null
						&& tempArr[i].isAfter(tempArr[i + 1])) {
					Flight temp = new Flight(tempArr[i]);
					tempArr[i] = tempArr[i + 1];
					tempArr[i + 1] = temp;
				}
			}
		}
		if (isIn)
			arrivals = tempArr;
		else
			departures = tempArr;
	}

	public String search(boolean incoming, String airline, String country, String city, String airport, boolean[] days,
			LocalDate startDate, LocalDate endDate) {
		Flight[] flights = incoming ? Arrays.copyOf(arrivals, arrivals.length)
				: Arrays.copyOf(departures, departures.length);
		for (int i = 0; i < flights.length; i++) {
			if (flights[i] != null) {
				boolean ok = true;
				if (airline != null && ok) {
					if (!(flights[i].getAirline().equalsIgnoreCase(airline))) {
						flights[i] = null;
						ok = false;
					}
				}
				if (country != null && ok) {
					if (!(flights[i].getCountry().equalsIgnoreCase(country))) {
						flights[i] = null;
						ok = false;
					}
				}
				if (city != null && ok) {
					if (!(flights[i].getCity().equalsIgnoreCase(city))) {
						flights[i] = null;
						ok = false;
					}
				}
				if (airport != null && ok) {
					if (!(flights[i].getAirport().equalsIgnoreCase(airport))) {
						flights[i] = null;
						ok = false;
					}
				}
				for (int j = 0; j < 7; j++) {
					if (days[j] && ok) {
						if (flights[i].getDate().getDayOfWeek().getValue() == j) {
							flights[i] = null;
							ok = false;
						}
					}
				}
				if (ok) {
					if (flights[i].getDate().isBefore(startDate) && !(flights[i].getDate().isEqual(startDate))) {
						flights[i] = null;
						ok = false;
					}
				}
				if (endDate != null && ok) {
					if (flights[i].getDate().isAfter(endDate) && !(flights[i].getDate().isEqual(startDate))) {
						flights[i] = null;
						ok = false;
					}
				}

			}
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < flights.length; i++) {
			if (flights[i] != null)
				str.append(flights[i].toString() + "\n");
		}
		return str.toString();
	}

	public void save(File file) throws FileNotFoundException {
		PrintWriter print = new PrintWriter(file);
		print.println(departures.length + " " + arrivals.length + " " + outFlight + " " + inFlight);
		for (int i = 0; i < outFlight; i++) {
			departures[i].save(print);
		}
		for (int i = 0; i < inFlight; i++) {
			arrivals[i].save(print);
		}
		print.close();
	}

	public void load(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		departures = new Flight[scan.nextInt()];
		arrivals = new Flight[scan.nextInt()];
		inFlight = scan.nextInt();
		outFlight = scan.nextInt();
		for (int i = 0; i < outFlight; i++) {
			Flight f = new Flight(scan);
			departures[i] = new Flight(f);
		}
		for (int i = 0; i < inFlight; i++) {
			Flight f = new Flight(scan);
			arrivals[i] = new Flight(f);
		}
		scan.close();
	}

	@Override
	public String toString() {
		return getArrivals() + getDepartures();
	}

}
