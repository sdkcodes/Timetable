import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * The TimetableFileManager class contains static methods for reading and
 * writing CSV files representing Timetable objects.
 * 
 * @author R. David Dunphy
 *
 */
public class TimetableFileManager {

	private static String NEWLINE = "\n";
	private static String MOD_MARKER = "MODS";
	private static String SLOT_MARKER = "SLOTS";
	private static String EOF_MARKER = "EOF";

	/**
	 * Encode a Timetable object as a CSV file and save it to a given file name.
	 * 
	 * @param tt
	 *            the timetable object to encode
	 * @param fileName
	 *            the file name to save under (without extension)
	 */
	public static void write(Timetable tt, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName + ".csv");
			BufferedWriter writer = new BufferedWriter(fw);
			String output = "";
			Iterator<Entry<String, String>> itd = tt.getAllMeta().entrySet()
					.iterator();
			while (itd.hasNext()) {
				Map.Entry<String, String> pair = itd.next();
				ArrayList<String> values = new ArrayList<>();
				values.add(pair.getKey());
				values.add(pair.getValue());
				output += encodeCSV(values);
				output += NEWLINE;
			}
			output += MOD_MARKER + NEWLINE;
			List<TimetableModule> modules = tt.getModules();
			Iterator<TimetableModule> itm = modules.iterator();
			while (itm.hasNext()) {
				TimetableModule m = itm.next();
				ArrayList<String> values = new ArrayList<>();
				values.add(Integer.toString(m.getID()));
				values.add(m.getCode());
				values.add(m.getName());
				values.add(m.getContact());
				output += encodeCSV(values);
				output += NEWLINE;
			}
			output += SLOT_MARKER + NEWLINE;
			List<TimetableSlot> slots = tt.getSlots();
			Iterator<TimetableSlot> its = slots.iterator();
			while (its.hasNext()) {
				TimetableSlot s = its.next();
				ArrayList<String> values = new ArrayList<>();
				values.add(s.getName());
				values.add(Integer.toString(s.getType()));
				values.add(s.getDay().toString());
				values.add(s.getStart().toString());
				values.add(s.getDuration().toString());
				values.add(s.getLocation());
				if (s.getModule() != null) {
					values.add(Integer.toString(s.getModule().getID()));
				} else {
					values.add("0");
				}
				output += encodeCSV(values);
				output += NEWLINE;
			}
			output += EOF_MARKER;
			writer.write(output);
			writer.close();
		} catch (IOException e) {
			System.out.println("Problem writing file " + fileName + ".csv.");
		}
	}

	/**
	 * Read a CSV file representing a timetable and convert it to a Timetable
	 * object.
	 * 
	 * @param fileName
	 *            the name of the file to open
	 * @return the timetable
	 */
	public static Timetable read(String fileName) {
		try {
			FileReader fr = new FileReader(fileName + ".csv");
			BufferedReader reader = new BufferedReader(fr);
			Timetable tt = new Timetable();
			Scanner s = new Scanner(reader);
			String l;
			ArrayList<String> al = new ArrayList<>();
			while (s.hasNextLine()) {
				l = s.nextLine();
				if (l.startsWith(MOD_MARKER)) {
					break;
				}
				al = extractCSV(l);
				String key = al.get(0);
				String value = al.get(1);
				tt.setMeta(key, value);
			}
			while (s.hasNextLine()) {
				l = s.nextLine();
				if (l.startsWith(SLOT_MARKER)) {
					break;
				}
				al = extractCSV(l);
				int id = Integer.parseInt(al.get(0));
				String code = al.get(1);
				String name = al.get(2);
				String contact = al.get(3);
				tt.addModule(id, code, name, contact);
			}
			while (s.hasNextLine()) {
				l = s.nextLine();
				if (l.startsWith(EOF_MARKER)) {
					break;
				}
				al = extractCSV(l);
				String name = al.get(0);
				int type = Integer.parseInt(al.get(1));
				DayOfWeek day = DayOfWeek.valueOf(al.get(2));
				LocalTime start = LocalTime.parse(al.get(3));
				Duration dur = Duration.parse(al.get(4));
				String loc = al.get(5);
				TimetableModule mod = tt.getModule(Integer.parseInt(al.get(6)));
				TimetableSlot slot = new TimetableSlot(name, type, day, start,
						dur, loc, mod);
				tt.addSlot(slot);
			}
			s.close();
			reader.close();
			return tt;
		} catch (IOException e) {
			System.out.println("Problem reading file " + fileName + ".csv.");
			return null;
		}
	}

	/**
	 * A helper method to wrap Strings in double quotes and escape any double
	 * quotes contained in them.
	 * 
	 * @param s
	 *            the String to escape
	 * @return the escaped String
	 */
	private static String escape(String s) {
		s = s.trim();
		s = s.replace("\"", "\\\"");
		return "\"" + s + "\"";
	}

	/**
	 * A helper method to extract Strings from double quotes, unescaping any
	 * double quotes contained in them.
	 * 
	 * @param s
	 *            the String to unescape
	 * @return the unescaped String
	 */
	private static String unescape(String s) {
		s = s.trim();
		if (s.length() == 0) {
			return "";
		}
		s = s.substring(1, s.length() - 1);
		s = s.replace("\\\"", "\"");
		return s;
	}

	/**
	 * Takes a line from a CSV file and returns the values as an ArrayList.
	 * 
	 * @param s
	 *            the line of CSV
	 * @return the list of values
	 */
	private static ArrayList<String> extractCSV(String s) {
		ArrayList<String> al = new ArrayList<>();
		boolean inString = false;
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '"') {
				if (i == 0 || s.charAt(i - 1) != '\\') {
					inString = !inString;
				}
			}
			if (s.charAt(i) == ',' && !inString) {
				al.add(unescape(s.substring(start, i)));
				start = i + 1;
			}
		}
		al.add(unescape(s.substring(start)));
		return al;
	}

	/**
	 * Takes an ArrayList of values and encodes them as a line of CSV.
	 * 
	 * @param al
	 *            the list of values
	 * @return the line of CSV
	 */
	private static String encodeCSV(ArrayList<String> al) {
		String str = "";
		ArrayList<String> escaped = new ArrayList<>();
		for (String s : al) {
			escaped.add(escape(s));
		}
		str = String.join(",", escaped);
		return str;
	}
}
