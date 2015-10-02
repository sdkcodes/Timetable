
public class Main {

	public static void main(String[] args) {

		Timetable t = new Timetable("myTimetable");
		t.addSlot(new TimetableSlot("slot1"));
		t.addSlot(new TimetableSlot("slot2"));
		System.out.println(t.toString());

	}

}
