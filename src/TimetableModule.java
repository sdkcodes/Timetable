/**
 * The TimetableModule class is used to create modules, which act as templates
 * for slots. I.e. if multiple slots are part of the same module, the common
 * features (module code and name, contact details) can be set/changed
 * simultaneously for all slots by assigning to them the same module.
 * 
 * @author R. David Dunphy
 *
 */
public class TimetableModule {

	private int id;
	private String code;
	private String name;
	private String contact;

	/**
	 * Create a module from given parameters.
	 * 
	 * @param id
	 *            the id number associated with the module (used to pair up
	 *            modules and slots in CSV files)
	 * @param code
	 *            the module code or abbreviated name
	 * @param name
	 *            the full name of the module
	 * @param contact
	 *            contact name(s)/details
	 */
	public TimetableModule(int id, String code, String name, String contact) {
		this.setID(id);
		this.setCode(code);
		this.setName(name);
		this.setContact(contact);
	}

	/**
	 * Print details of this module to the terminal as a String.
	 */
	public void print() {
		String str = id + ":";
		boolean hasCodeOrName = false;
		if (code != null && code.length() != 0) {
			str += " " + code;
			hasCodeOrName = true;
		}
		if (name != null && name.length() != 0) {
			str += " " + name;
			hasCodeOrName = true;
		}
		if (contact != null && contact.length() != 0) {
			if (hasCodeOrName) {
				str += ",";
			}
			str += " " + contact;
		}
		System.out.println(str);
	}

	/**
	 * @return the ID number of the module
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id
	 *            the ID number to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the module code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the module code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name of the module
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the module name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the contact name(s)/details
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact name(s)/details to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

}
