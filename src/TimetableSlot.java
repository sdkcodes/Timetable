import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;


public class TimetableSlot {
	
	private int id;
	private String name;
	private DayOfWeek day;
	private LocalTime start;
	private Duration duration;
	
	public TimetableSlot(int id, String name, DayOfWeek day, LocalTime start, Duration duration) {
		this.id = id;
		this.name = name;
		this.setDay(day);
		this.start = start;
		this.setDuration(duration);
	}

	public String toString() {
		String str = name + " (" + id + "): ";
		str += day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ";
		str += start.toString() + "-" + getFinish();
		return str;
	}
	
	/**
	 * @return the finish time, as calculated from start and duration
	 */
	public LocalTime getFinish() {
		return start.plus(duration);
	}

	/**
	 * @return the id number
	 */
	public int getID() {
		return id;
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
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}

	/**
	 * @param start the start to set
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
	 * @param duration the duration to set
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
	 * @param day the day to set
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
}
