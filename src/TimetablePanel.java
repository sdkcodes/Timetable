import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel with a timetable grid drawn out and all of the slots and information
 * from a given timetable included.
 * 
 * @author R. David Dunphy
 *
 */
public class TimetablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timetable timetable;
	private int width;
	private int height;

	/**
	 * Construct a timetable panel from a given timetable with given dimensions.
	 * 
	 * @param timetable
	 * @param width
	 * @param height
	 */
	public TimetablePanel(Timetable timetable, int width, int height) {
		super();
		this.setSize(width, height);
		this.timetable = timetable;

	}

	/**
	 * Draw a grid and start populating it with slots.
	 */
	private void draw() {
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(timetable.getMeta("NAME")));
	}

	/**
	 * Redraw the panel.
	 */
	public void update() {
		draw();
	}
}
