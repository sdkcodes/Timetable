import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Timetable {
	
	private Map<String, String> metadata;
	private List<TimetableModule> modules;
	private List<TimetableSlot> slots;
	
	public Timetable() {
		this.metadata = new HashMap<>();
		this.modules = new ArrayList<>();
		this.slots = new ArrayList<>();
	}
	
	/**
	 * @param key the key to look up
	 * @return the value
	 */
	public String getMeta(String key) {
		return metadata.get(key);
	}
	
	public Map<String, String> getAllMeta() {
		return metadata;
	}

	/**
	 * @param key the key to set
	 * @param value the value to set
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
	
	public TimetableModule getModule(int m) {
		if (m == 0) {
			return null;
		}
		Iterator<TimetableModule> it = modules.iterator();
		while (it.hasNext()) {
			TimetableModule mod = it.next();
			if (mod.getID() == m) {
				return mod;
			}
		}
		return null;
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
	 * @param m the module to add
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
		System.out.println("###### " + getMeta("NAME") + " by " + getMeta("AUTHOR") + " ######");
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
