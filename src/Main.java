
public class Main {

	public static void main(String[] args) {
		
		Timetable t = TimetableFileManager.read("myTimetable");
		t.print();
		
		TimetableFileManager.write(t, "myTimetable");
		
	}

}
