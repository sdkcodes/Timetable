import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * The TimetableSlot class is used to create slots (or classes), consisting of a
 * time (start time, duration, and day of the week) and information about the
 * slot (name, type of class, location). Optionally this can be linked to a
 * TimetableModule object.
 * 
 * @author R. David Dunphy
 *
 */
public class TimetableSlot {

	public static final int UNSPECIFIED = 0;
	public static final int LECTURE = 1;
	public static final int TUTORIAL = 2;
	public static final int PRACTICAL = 3;

	private String name;
	private int type;
	private DayOfWeek day;
	private LocalTime start;
	private Duration duration;
	private String location;
	private TimetableModule module;

	/**
	 * Create a timetable slot from given parameters.
	 * 
	 * @param name
	 *            the name of the class (may be "")
	 * @param type
	 *            the type of class (choose from static fields. default is
	 *            UNSPECIFIED.)
	 * @param day
	 *            the day of the week this class is on
	 * @param start
	 *            the time the class starts
	 * @param duration
	 *            the duration of the class
	 * @param location
	 *            the location of the class (may be "")
	 * @param module
	 *            a module associated with this class (may be null)
	 */
	public TimetableSlot(String name, int type, DayOfWeek day, LocalTime start,
			Duration duration, String location, TimetableModule module) {
		this.setName(name);
		this.setType(type);
		this.setDay(day);
		this.setStart(start);
		this.setDuration(duration);
		this.setLocation(location);
		this.setModule(module);
	}

	/**
	 * Print details of this slot to the terminal as a String.
	 */
	public void print() {
		String str = "";
		if (module != null) {
			str += module.getCode();
		}
		if (name != null && name.length() != 0) {
			if (str.length() != 0) {
				str += " ";
			}
			str += name;
		}
		if (type != UNSPECIFIED) {
			if (str.length() != 0) {
				str += " ";
			}
			str += getTypeString();
		}
		if (str.length() != 0) {
			str += ", ";
		}
		str += day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ";
		str += start.toString() + "-" + getFinish();
		if (location != null && location.length() != 0) {
			str += ", " + location;
		}
		System.out.println(str);
	}

	/**
	 * @return the finish time, as calculated from start and duration
	 */
	public LocalTime getFinish() {
		return start.plus(duration);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(LocalTime start) {
		this.start = start;
	}

	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	/**
	 * @return the day
	 */
	public DayOfWeek getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	/**
	 * @return the module
	 */
	public TimetableModule getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set (may be null)
	 */
	public void setModule(TimetableModule module) {
		this.module = module;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the type as a String
	 */
	public String getTypeString() {
		if (this.type == LECTURE) {
			return "Lecture";
		}
		if (this.type == TUTORIAL) {
			return "Tutorial";
		}
		if (this.type == PRACTICAL) {
			return "Practical";
		}
		return "N/A";
	}

	/**
	 * @param type
	 *            the type to set (default UNSPECIFIED)
	 */
	public void setType(int type) {
		if (type != LECTURE && type != TUTORIAL && type != PRACTICAL) {
			this.type = UNSPECIFIED;
		} else {
			this.type = type;
		}
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
