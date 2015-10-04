import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TimetableFileManager {

	public static String NEWLINE = "\n";

	public static void write(Timetable tt, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName + ".csv");
			BufferedWriter writer = new BufferedWriter(fw);
			String output = tt.getName() + NEWLINE;
			List<TimetableModule> modules = tt.getModules();
			Iterator<TimetableModule> itm = modules.iterator();
			while (itm.hasNext()) {
				TimetableModule m = itm.next();
				output += m.getID() + ",";
				output += m.getCode() + ",";
				output += m.getName() + ",";
				output += m.getContact() + NEWLINE;
			}
			output += "SLOTS" + NEWLINE;
			List<TimetableSlot> slots = tt.getSlots();
			Iterator<TimetableSlot> its = slots.iterator();
			while (its.hasNext()) {
				TimetableSlot s = its.next();
				output += s.getName() + ",";
				output += s.getType() + ",";
				output += s.getDay().toString() + ",";
				output += s.getStart().toString() + ",";
				output += s.getDuration().toString() + ",";
				output += s.getLocation() + ",";
				if (s.getModule() != null) {
					output += s.getModule().getID();
				} else {
					output += 0;
				}
				if (its.hasNext()) {
					output += NEWLINE;
				}
			}
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
			tt.setName(s.nextLine());
			String l;
			String[] a;
			while (s.hasNextLine()) {
				l = s.nextLine();
				if (l.equals("SLOTS")) {
					break;
				}
				a = l.split(",");
				tt.addModule(Integer.parseInt(a[0]), a[1], a[2], a[3]);
			}
			while (s.hasNextLine()) {
				l = s.nextLine();
				a = l.split(",");
				System.out.println("l: " + l);
				TimetableSlot slot = new TimetableSlot(a[0],
						Integer.parseInt(a[1]), DayOfWeek.valueOf(a[2]),
						LocalTime.parse(a[3]), Duration.parse(a[4]), a[5],
						tt.getModule(Integer.parseInt(a[6])));
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
}
