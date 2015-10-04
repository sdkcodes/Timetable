import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


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
			writer.write(output);
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Problem writing file.");
		}
	}
}
