import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			for (TimetableModule m : modules) {
				output += m.getID() + ",";
				output += m.getCode() + ",";
				output += m.getName() + ",";
				output += m.getContact() + NEWLINE;
			}
			output += "SLOTS" + NEWLINE;
			List<TimetableSlot> slots = tt.getSlots();
			for (TimetableSlot s : slots) {
				output += s.getName() + ",";
				output += s.getType() + ",";
				output += s.getDay().toString() + ",";
				output += s.getStart().toString() + ",";
				output += s.getDuration().toString() + ",";
				output += s.getLocation() + ",";
				if (s.getModule() != null) {
					output += s.getModule().getID();
				}
				output += NEWLINE;
			}
			output += "EOF";
			writer.write(output);
			writer.close();
		}
		catch(IOException e) {
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
			while (s.hasNextLine()) {
				String l = s.nextLine();
				// add module to timetable
				if (l == "SLOTS") {
					break;
				}
			}
			while (s.hasNextLine()) {
				String l = s.nextLine();
				// add slot to timetable
				if (l == "EOF") {
					break;
				}
			}
			s.close();
			reader.close();
			return tt;
		}
		catch(IOException e) {
			System.out.println("Problem reading file " + fileName + ".csv.");
			return null;
		}
	}
}
