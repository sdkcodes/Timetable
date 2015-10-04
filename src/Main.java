import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;


public class Main {

	public static void main(String[] args) {
		/*
		Timetable t = new Timetable("myTimetable");
		
		t.addModule("CS210", "Computer Systems and Architecture", "Duncan Smeed");
		t.addModule("CS207", "Advanced Programming", "Isla Ross");
		
		t.addSlot(new TimetableSlot("Slot 1", TimetableSlot.LECTURE, DayOfWeek.FRIDAY, LocalTime.of(10, 30), Duration.ofHours(1), "JA317", t.getModules().get(0)));
		t.addSlot(new TimetableSlot("", TimetableSlot.TUTORIAL, DayOfWeek.TUESDAY, LocalTime.of(12, 0), Duration.ofMinutes(150), "", t.getModules().get(1)));
		t.addSlot(new TimetableSlot("One-off class", TimetableSlot.UNSPECIFIED, DayOfWeek.MONDAY, LocalTime.of(18, 0), Duration.ofMinutes(55), "RC667", null));
		
		t.print();
		
		TimetableFileManager.write(t, "myTimetable");
		*/
		
		Timetable t = TimetableFileManager.read("myTimetable");
		t.print();

	}

}
