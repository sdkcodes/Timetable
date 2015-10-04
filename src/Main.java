
public class Main {

	public static void main(String[] args) {
		
		Timetable t = TimetableFileManager.read("myTimetable");
		t.print();
		t.setMeta("DATE", "4/10/15");
		TimetableFileManager.write(t, "myTimetable");
		
	}

}
