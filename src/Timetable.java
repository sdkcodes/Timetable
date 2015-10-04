import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Timetable {
	
	private String name;
	private List<TimetableModule> modules;
	private List<TimetableSlot> slots;
	
	public Timetable(String name) {
		this.setName(name);
		this.modules = new ArrayList<>();
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
	 * @return the list of modules
	 */
	public List<TimetableModule> getModules() {
		return modules;
	}
	
	/**
	 * @param m the module to add
	 */
	public void addModule(String code, String name, String contact) {
		Random r = new Random();
		int id = 0;
		boolean getRand = true;
		while (getRand) {
			id = r.nextInt(899999) + 100000; // Random 6-digit number
			getRand = false;
			for (TimetableModule m : modules) { // Check for uniqueness
				if (m.getID() == id) {
					getRand = true;
				}
			}
		}
		TimetableModule module = new TimetableModule(id, code, name, contact);
		this.modules.add(module);
	}

	/**
	 * @return the list of slots
	 */
	public List<TimetableSlot> getSlots() {
		return slots;
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
		System.out.println("###### List of Modules ######");
		for (TimetableModule m : modules) {
			m.print();
		}
		System.out.println("###### List of Slots ######");
		for (TimetableSlot s : slots) {
			s.print();
		}
		System.out.println("##########################");
		System.out.println();
	}

}
