import java.time.LocalDate;
import java.util.ArrayList;
import flightControl.Control;
import flightControl.Flight;
import flightControl.Program;

public class NatbagMain {

	private static String printFlights(ArrayList<Flight> flights, String nextLine) {
		StringBuffer str = new StringBuffer();
		for (Flight f : flights) {
			str.append(f + nextLine);
		}
		return str.toString();
	}

	public static void main(String[] args) {
		Control control = new Control();
		Program.hardCoding(control);
		boolean isHtml = args.length > 0 && !args[0].equals("") ? args[0].equalsIgnoreCase("html") : false;
		String nextLine = isHtml ? "<br>" : "\n";
		boolean isDepartures = args.length > 1 && !args[1].equals("") ? Boolean.valueOf(args[1]) : true;
		String airline = args.length > 2 && !args[2].equals("") ? args[2] : null;
		String country = args.length > 3 && !args[3].equals("") ? args[3] : null;
		String city = args.length > 4 && !args[4].equals("") ? args[4] : null;
		String airport = args.length > 5 && !args[5].equals("") ? args[5] : null;
		LocalDate startDate = args.length > 6 && !args[6].equals("") ? LocalDate.parse(args[6]) : LocalDate.now();
		LocalDate endDate = args.length > 7 && !args[7].equals("") ? LocalDate.parse(args[7]) : null;
		boolean sunday = args.length > 8 && !args[8].equals("") ? Boolean.valueOf(args[8]) : true;
		boolean monday = args.length > 9 && !args[9].equals("") ? Boolean.valueOf(args[9]) : true;
		boolean tuesday = args.length > 10 && !args[10].equals("") ? Boolean.valueOf(args[10]) : true;
		boolean wednesday = args.length > 11 && !args[11].equals("") ? Boolean.valueOf(args[11]) : true;
		boolean thursday = args.length > 12 && !args[12].equals("") ? Boolean.valueOf(args[12]) : true;
		boolean friday = args.length > 13 && !args[13].equals("") ? Boolean.valueOf(args[13]) : true;
		boolean saturday = args.length > 14 && !args[14].equals("") ? Boolean.valueOf(args[14]) : true;
		boolean[] days = { monday, tuesday, wednesday, thursday, friday, saturday , sunday};
		System.out.println(printFlights(
				control.search(isDepartures, airline, country, city, airport, days, startDate, endDate), nextLine));
	}

}
