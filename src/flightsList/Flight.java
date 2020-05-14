package flightsList;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Flight {
	private String airline;
	private String flightNumber;
	private LocalDate date;
	private LocalTime time;
	private String country;
	private String city;
	private String airport;
	private int terminal;
	private boolean incoming;

	public Flight(String country, String city, String airport, String airline, LocalDate date, LocalTime time,
			String flightNumber, int terminal, boolean in) {
		setLocation(country, city, airport);
		setAirline(airline);
		setDate(date);
		setTime(time);
		setFlightNumber(flightNumber);
		setTerminal(terminal);
		setIn(in);
	}

	public Flight(Flight other) {
		if (other != null) {
			setLocation(other.country, other.city, other.airport);
			setAirline(other.airline);
			setDate(other.date);
			setTime(other.time);
			setFlightNumber(other.flightNumber);
			setTerminal(other.terminal);
			setIn(other.incoming);
		}
	}

	public Flight(Scanner scan) {
		setLocation(scan.next(), scan.next(), scan.next());
		setAirline(scan.next());
		String temp = scan.next();
		String[] tempArr = temp.split("-");
		setDate(LocalDate.of(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1]), Integer.parseInt(tempArr[2])));
		temp = scan.next();
		tempArr = temp.split(":");
		setTime(LocalTime.of(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1])));
		setFlightNumber(scan.next());
		setTerminal(scan.nextInt());
		setIn(scan.nextBoolean());
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	private String Date() {
		StringBuffer str = new StringBuffer();
		if (date.getDayOfMonth() < 10)
			str.append("0" + date.getDayOfMonth() + "/");
		else
			str.append(date.getDayOfMonth() + "/");
		if (date.getMonthValue() < 10)
			str.append("0" + date.getMonthValue());
		else
			str.append(date.getMonthValue());
		str.append("/" + date.getYear());
		return str.toString();
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getAirport() {
		return airport;
	}

	public void setLocation(String country, String city, String airport) {
		this.country = country;
		this.city = city;
		this.airport = airport;
	}

	public boolean isIncoming() {
		return incoming;
	}

	public void setIn(boolean in) {
		this.incoming = in;
	}

	public boolean isAfter(Flight other) {
		if (date.isAfter(other.date) || ((date.equals(other.date) && time.isAfter(other.time))))
			return true;
		return false;
	}

	public void save(PrintWriter print) {
		print.println(country + " " + city + " " + airport + " " + airline + " " + date + " " + time + " "
				+ flightNumber + " " + terminal + " " + incoming);
	}

	@Override
	public String toString() {
		if (incoming) {
			return "Airline: " + airline + ", Coming from: " + country + ", " + city + ", " + airport + ", Date: "
					+ Date() + ", Arrive at: " + time + ", Flight: " + flightNumber + ", Terminal: " + terminal;
		} else {
			return "Airline: " + airline + ", Departing to: " + country + ", " + city + ", " + airport + ", Date: "
					+ Date() + ", Depart at: " + time + ", Flight: " + flightNumber + ", Terminal: " + terminal;
		}
	}

}
