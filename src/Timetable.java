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
	
	/**
	 * Print details of this timetable.
	 */
	public void print() {
		System.out.println("##########################");
		System.out.println("###### " + name + " ######");
		System.out.println("###### List of Slots ######");
		for (TimetableSlot s : slots) {
			s.print();
		}
		System.out.println("##########################");
		System.out.println();
	}

}
