
public class TimetableModule {
	
	private int id;
	private String code;
	private String name;
	private String contact;
	
	public TimetableModule(int id, String code, String name, String contact) {
		this.setID(id);
		this.setCode(code);
		this.setName(name);
		this.setContact(contact);
	}

	/**
	 * Print details of this module.
	 */
	public void print() {
		String str = id + ": " + code + " " + name + ", " + contact;
		System.out.println(str);
	}
	
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

}
