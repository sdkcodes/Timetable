import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The Timetable object holds all the information about a timetable, including
 * metadata (such as name, author, etc.), a list of modules, and a list
 * containing all of the slots currently present in the timetable.
 * 
 * @author R. David Dunphy
 *
 */
public class Timetable {

	private Map<String, String> metadata;
	private List<TimetableModule> modules;
	private List<TimetableSlot> slots;

	/**
	 * Create a timetable object, initialising the necessary data structures.
	 */
	public Timetable() {
		this.metadata = new HashMap<>();
		this.modules = new ArrayList<>();
		this.slots = new ArrayList<>();
	}

	/**
	 * @param key
	 *            the key to look up
	 * @return the corresponding value
	 */
	public String getMeta(String key) {
		return metadata.get(key);
	}

	/**
	 * 
	 * @return a map containing all the metadata key-value pairs
	 */
	public Map<String, String> getAllMeta() {
		return metadata;
	}

	/**
	 * @param key
	 *            the key to set
	 * @param value
	 *            the value to set
	 */
	public void setMeta(String key, String value) {
		metadata.put(key, value);
	}

	/**
	 * @return the list of modules
	 */
	public List<TimetableModule> getModules() {
		return modules;
	}

	/**
	 * Get a module with a specific ID number.
	 * 
	 * @param id
	 *            the module ID number
	 * @return the module
	 */
	public TimetableModule getModule(int id) {
		if (id == 0) {
			return null;
		}
		Iterator<TimetableModule> it = modules.iterator();
		while (it.hasNext()) {
			TimetableModule mod = it.next();
			if (mod.getID() == id) {
				return mod;
			}
		}
		return null;
	}

	/**
	 * Create a module with a randomly generated ID number and add it to the
	 * Timetable.
	 * 
	 * @param code
	 *            the module code
	 * @param name
	 *            the name of the module
	 * @param contact
	 *            the name(s)/contact details of the module supervisor(s)
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
	 * Create a module with a specified ID number and add it to the Timetable.
	 * (Used when loading from CSV file.)
	 * 
	 * @param id
	 *            the module's ID number
	 * @param code
	 *            the module code
	 * @param name
	 *            the name of the module
	 * @param contact
	 *            the name(s)/contact details of the module supervisor(s)
	 */
	public void addModule(int id, String code, String name, String contact) {
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
	 * @param s
	 *            the slot to add
	 */
	public void addSlot(TimetableSlot s) {
		this.slots.add(s);
	}

	/**
	 * Print details of this timetable to the terminal as a String.
	 */
	public void print() {
		System.out.println("##########################");
		System.out.println("###### " + getMeta("NAME") + " by "
				+ getMeta("AUTHOR") + " ######");
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
