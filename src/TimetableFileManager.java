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
import java.util.Scanner;

public class TimetableFileManager {

	public static String NEWLINE = "\n";

	public static void write(Timetable tt, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName + ".csv");
			BufferedWriter writer = new BufferedWriter(fw);
			String output = escape(tt.getName()) + NEWLINE;
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
			output += "SLOTS" + NEWLINE;
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
			output += "EOF";
			writer.write(output);
			writer.close();
		} catch (IOException e) {
			System.out.println("Problem writing file " + fileName + ".csv.");
		}
	}

	public static Timetable read(String fileName) {
		try {
			FileReader fr = new FileReader(fileName + ".csv");
			BufferedReader reader = new BufferedReader(fr);
			Timetable tt = new Timetable();
			Scanner s = new Scanner(reader);
			tt.setName(unescape(s.nextLine()));
			String l;
			ArrayList<String> al = new ArrayList<>();
			while (s.hasNextLine()) {
				l = s.nextLine();
				if (l.startsWith("SLOTS")) {
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
				if (l.startsWith("EOF")) {
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
				TimetableSlot slot = new TimetableSlot(name, type, day, start, dur, loc, mod);
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
	
	private static String escape(String s) {
		s = s.trim();
		s = s.replace("\"", "\\\"");
		return "\"" + s + "\"";
	}
	
	private static String unescape(String s) {
		s = s.trim();
		if (s.length() == 0) {
			return "";
		}
		s = s.substring(1, s.length() - 1);
		s = s.replace("\\\"", "\"");
		return s;
	}
	
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
