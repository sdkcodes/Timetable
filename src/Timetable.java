import java.util.ArrayList;
import java.util.List;


public class Timetable {
	
	private String name;
	private List<TimetableSlot> slots;
	
	public Timetable(String name) {
		this.setName(name);
		this.slots = new ArrayList<>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param s the slot to add
	 */
	public void addSlot(TimetableSlot s) {
		this.slots.add(s);
	}
	
	public String toString() {
		String str = name + "\n";
		for (TimetableSlot s : slots) {
			str += s.toString() + "\n";
		}
		return str;
	}

}
