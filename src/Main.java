import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;


public class Main {

	public static void main(String[] args) {

		Timetable t = new Timetable("myTimetable");
		t.addSlot(new TimetableSlot(0, "slot1", DayOfWeek.FRIDAY, LocalTime.of(10, 30), Duration.ofHours(1)));
		t.addSlot(new TimetableSlot(1, "slot2", DayOfWeek.TUESDAY, LocalTime.of(12, 0), Duration.ofMinutes(150)));
		System.out.println(t.toString());

	}

}
