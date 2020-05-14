package flightsList;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Program {

	// Hard Coded
	public static void hardCoding(Control c) {
		LocalDate d1 = LocalDate.of(2020, 10, 24);
		LocalDate d2 = LocalDate.of(2020, 10, 24);
		LocalDate d3 = LocalDate.of(2020, 9, 27);
		LocalDate d4 = LocalDate.of(2020, 11, 2);

		LocalTime t1 = LocalTime.of(22, 20);
		LocalTime t2 = LocalTime.of(20, 50);
		LocalTime t3 = LocalTime.of(10, 05);
		LocalTime t4 = LocalTime.of(13, 40);

		Flight f1 = new Flight("country1", "city1", "airport1", "airline1", d1, t1, "flightNumber1", 1, true);
		Flight f2 = new Flight("country2", "city2", "airport2", "airline2", d2, t2, "flightNumber2", 2, true);
		Flight f3 = new Flight("country3", "city3", "airport3", "airline3", d3, t3, "flightNumber3", 3, true);
		Flight f4 = new Flight("country4", "city4", "airport4", "airline4", d4, t4, "flightNumber4", 4, true);
		Flight f5 = new Flight("country5", "city1", "airport1", "airline1", d1, t1, "flightNumber1", 1, false);
		Flight f6 = new Flight("country6", "city2", "airport2", "airline2", d2, t2, "flightNumber2", 2, false);
		Flight f7 = new Flight("country7", "city3", "airport3", "airline3", d3, t3, "flightNumber3", 3, false);
		Flight f8 = new Flight("country8", "city4", "airport4", "airline4", d4, t4, "flightNumber4", 4, false);

		c.addFlight(f1);
		c.addFlight(f2);
		c.addFlight(f3);
		c.addFlight(f4);
		c.addFlight(f5);
		c.addFlight(f6);
		c.addFlight(f7);
		c.addFlight(f8);

	}

	// Set flights to add them in method addFlight (control class)
	public static Flight setFlight(Scanner scan, boolean incoming) {
		if (incoming)
			System.out.println(
					"To add an incoming flight please enter the following:\nAirline, Coming from (county city airport), Date (day/month/year), Landing time (hr:min), Flight Number, Terminal");
		else
			System.out.println(
					"To add an outgoing flight please enter the following:\nAirline, Departing to (county city airport), Date (day/month/year), Departing time (hr:min), Flight Number, Terminal");
		String airline = scan.next();
		String county = scan.next();
		String city = scan.next();
		String airport = scan.next();
		String str = scan.next();
		String[] strArr = str.split("/");
		LocalDate d = LocalDate.of(Integer.parseInt(strArr[2]), Integer.parseInt(strArr[1]),
				Integer.parseInt(strArr[0]));
		str = scan.next();
		strArr = str.split(":");
		LocalTime t = LocalTime.of(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
		String FlightNum = scan.next();
		int Terminal = scan.nextInt();
		Flight temp = new Flight(county, city, airport, airline, d, t, FlightNum, Terminal, incoming);
		return temp;
	}

	// Set a file to the methods save / load (control class)
	public static File getFile(Scanner scan) {
		System.out.println("Please enter the file location (Example: 'C:\\Users\\Name\\File.txt')");
		File f = new File(scan.next());
		return f;
	}

	public static void searchFlights(Scanner scan, Control control) {
		boolean exit2 = false;
		boolean incoming = false;
		String airline = null, country = null, city = null, airport = null;
		final int eight = 8;
		boolean[] days = new boolean[eight];
		LocalDate endDate = null, startDate = LocalDate.now();
		while (!exit2) {
			System.out.println("Please select by which parameters to search:");
			System.out.println("1 - Incoming / Outgoing");
			System.out.println("2 - Airline");
			System.out.println("3 - Country");
			System.out.println("4 - City");
			System.out.println("5 - Airport");
			System.out.println("6 - Dates");
			System.out.println("7 - Days");
			System.out.println("8 - Search");
			System.out.println("9 - Reset All Parameters");
			System.out.println("0 - Exit");
			int choice2 = scan.nextInt();
			switch (choice2) {
			case 1:
				System.out.println("Please select which kind of flights to search:");
				System.out.println("1 - Incoming");
				System.out.println("2 - Outgoing");
				int choice3 = scan.nextInt();
				switch (choice3) {
				case 1:
					incoming = true;
					break;
				case 2:
					incoming = false;
					break;
				default:
					break;
				}
				break;
			case 2:
				System.out.println("Please enter a Airline:");
				airline = scan.next();
				break;
			case 3:
				System.out.println("Please enter a Country:");
				country = scan.next();
				break;
			case 4:
				System.out.println("Please enter a City:");
				city = scan.next();
				break;
			case 5:
				System.out.println("Please enter a Airport:");
				airport = scan.next();
				break;
			case 6:
				boolean exit3 = false;
				while (exit3) {
					boolean start = false;
					System.out.println("Please select the range of dates to search in:");
					System.out.println("1 - Enter starting date");
					System.out.println("2 - Enter ending date");
					System.out.println("3 - Exit");
					int choice4 = scan.nextInt();
					switch (choice4) {
					case 1:
						System.out.println("Please enter starting date (day/month/year):");
						start = true;
						break;
					case 2:
						System.out.println("Please enter ending date (day/month/year):");
						break;
					case 3:
						exit3 = true;
						break;
					default:
						break;
					}
					if (choice4 == 1 || choice4 == 2) {
						String str = scan.next();
						String[] strArr = str.split("/");
						if (start)
							startDate = LocalDate.of(Integer.parseInt(strArr[2]), Integer.parseInt(strArr[1]),
									Integer.parseInt(strArr[0]));
						else
							endDate = LocalDate.of(Integer.parseInt(strArr[2]), Integer.parseInt(strArr[1]),
									Integer.parseInt(strArr[0]));
					}
				}
				break;
			case 7:
				boolean exit4 = false;
				while (!exit4) {
					System.out.println("Please select the range of days to search in:");
					System.out.println("1 - Sunday: " + !days[7]);
					System.out.println("2 - Monday: " + !days[1]);
					System.out.println("3 - Tuesday: " + !days[2]);
					System.out.println("4 - Wednesday: " + !days[3]);
					System.out.println("5 - Thursday: " + !days[4]);
					System.out.println("6 - Friday: " + !days[5]);
					System.out.println("7 - Saturday: " + !days[6]);
					System.out.println("8 - Reset all days");
					System.out.println("0 - Exit");
					int choice5 = scan.nextInt();
					switch (choice5) {
					case 1:
						days[7] = !days[7];
						break;
					case 2:
						days[1] = !days[1];
						break;
					case 3:
						days[2] = !days[2];
						break;
					case 4:
						days[3] = !days[3];
						break;
					case 5:
						days[4] = !days[4];
						break;
					case 6:
						days[5] = !days[5];
						break;
					case 7:
						days[6] = !days[6];
						break;
					case 8:
						days = new boolean[eight];
						break;
					case 0:
						exit4 = true;
						break;
					default:
						break;
					}
				}
				break;
			case 8:
				System.out.println(control.search(incoming, airline, country, city, airport, days, startDate, endDate));
				break;
			case 9:
				incoming = false;
				airline = null;
				country = null;
				city = null;
				airport = null;
				days = new boolean[eight];
				startDate = LocalDate.now();
				endDate = null;
				break;
			case 0:
				exit2 = true;
				break;
			default:
				break;
			}
		}

	}

	public static void main(String[] arg) throws FileNotFoundException {
		Control control = new Control();
		hardCoding(control);
		Scanner scan = new Scanner(System.in);
		boolean exit1 = false;
		System.out.println("Welcome");
		while (!exit1) {
			System.out.println("Please select one of the following options:");
			System.out.println("1 - Add incoming flight");
			System.out.println("2 - Add outgoing flight");
			System.out.println("3 - Show Departures");
			System.out.println("4 - Show Arrivals");
			System.out.println("5 - Search flights");
			System.out.println("6 - Save");
			System.out.println("7 - Load");
			System.out.println("0 - Exit");
			int choice1 = scan.nextInt();
			switch (choice1) {
			case 1:
				control.addFlight(setFlight(scan, true));
				System.out.println("Flight added successfully");
				break;
			case 2:
				control.addFlight(setFlight(scan, false));
				System.out.println("Flight added successfully");
				break;
			case 3:
				System.out.println(control.getDepartures());
				break;
			case 4:
				System.out.println(control.getArrivals());
				break;
			case 5:
				searchFlights(scan, control);
				break;
			case 6:
				control.save(getFile(scan));
				System.out.println("Saved successfully");
				break;
			case 7:
				control.load(getFile(scan));
				System.out.println("Loaded successfully");
				break;
			case 0:
				System.out.println("Goodbye");
				exit1 = true;
				break;
			default:
				break;
			}
		}
		scan.close();
	}

}
