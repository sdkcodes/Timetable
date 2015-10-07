import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 * TimetableManager is the main class of the Timetable Manager application. This
 * class opens and populates the main JFrame and interacts with the Timetable
 * objects.
 * 
 * @author R. David Dunphy
 * @version 1.0
 *
 */
public class TimetableManager {

	private static final String NAME = "Timetable Manager";
	private static final String VERSION = "1.0";
	private static final String AUTHOR = "David Dunphy";
	private static final String ICON_PATH = "src/toolbarIcons/";

	private Timetable currentTimetable;
	private JFrame frame;
	private JScrollPane timetablePane;

	/**
	 * Create and instance of TimetableManager and open the main frame.
	 */
	public TimetableManager() {
		makeFrame();
	}

	/**
	 * Create a new timetable file.
	 */
	private void newFile() {

	}

	/**
	 * Open an existing timetable file.
	 */
	private void openFile() {
		currentTimetable = TimetableFileManager.read("myTimetable");
		//currentTimetable.print();
		timetablePane.add(new TimetablePanel(currentTimetable, 300, 200));
	}

	/**
	 * Save the current timetable.
	 */
	private void saveFile() {
		TimetableFileManager.write(currentTimetable, "myTimetable");
	}

	/**
	 * Save the current timetable as a copy.
	 */
	private void saveFileAs() {

	}

	/**
	 * Close the current timetable.
	 */
	private void closeFile() {

	}

	/**
	 * Exit the application after checking for unsaved changes.
	 */
	private void quit() {
		// Check for unsaved changes!
		System.exit(0);
	}

	/**
	 * Create a new module in the current timetable.
	 */
	private void newModule() {

	}

	/**
	 * Add a new slot to the current timetable.
	 */
	private void newSlot() {

	}

	/**
	 * Show the help documentation.
	 */
	private void showHelp() {

	}

	/**
	 * Display the About window.
	 */
	private void showAbout() {
		JOptionPane.showMessageDialog(frame, NAME + " v" + VERSION
				+ "\nCreated by " + AUTHOR, "About " + NAME,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Set up the main JFrame, adding the menu and toolbar.
	 */
	private void makeFrame() {
		frame = new JFrame(NAME);
		frame.setMinimumSize(new Dimension(300, 200));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// On frame close, close entire application:
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				quit();
			}
		});

		makeMenuBar(frame);

		// Specify the layout manager and content
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(makeToolbar(), BorderLayout.PAGE_START);

		timetablePane = new JScrollPane();
		contentPane.add(timetablePane, BorderLayout.CENTER);

		// building is done - arrange the components
		// updateFrame();
		// frame.pack();

		// place the frame at the center of the screen and show
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2
				- frame.getHeight() / 2);

		frame.setVisible(true);
	}

	/**
	 * Create the main frame's menu bar.
	 * 
	 * @param frame
	 *            the frame that the menu bar should be added to.
	 */
	private void makeMenuBar(JFrame frame) {
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JMenu menu;
		JMenuItem item;

		// create the File menu
		menu = new JMenu("File");
		menu.setMnemonic('F');
		menubar.add(menu);

		item = new JMenuItem("New...");
		item.setMnemonic(KeyEvent.VK_N);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFile();
			}
		});
		menu.add(item);

		item = new JMenuItem("Open");
		item.setMnemonic(KeyEvent.VK_O);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		menu.add(item);

		menu.addSeparator();

		item = new JMenuItem("Save");
		item.setMnemonic(KeyEvent.VK_S);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		menu.add(item);

		item = new JMenuItem("Save As...");
		item.setMnemonic(KeyEvent.VK_A);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		menu.add(item);

		menu.addSeparator();

		item = new JMenuItem("Close");
		item.setMnemonic(KeyEvent.VK_C);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFile();
			}
		});
		menu.add(item);

		item = new JMenuItem("Quit");
		item.setMnemonic(KeyEvent.VK_Q);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		menu.add(item);

		// create the Timetable menu
		menu = new JMenu("Timetable");
		menu.setMnemonic(KeyEvent.VK_T);
		menubar.add(menu);

		item = new JMenuItem("New module...");
		item.setMnemonic(KeyEvent.VK_M);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl shift N"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newModule();
			}
		});
		menu.add(item);

		item = new JMenuItem("New slot...");
		item.setMnemonic(KeyEvent.VK_N);
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSlot();
			}
		});
		menu.add(item);

		// create the Help menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menubar.add(menu);

		item = new JMenuItem("Help files");
		item.setMnemonic(KeyEvent.VK_H);
		item.setAccelerator(KeyStroke.getKeyStroke("F1"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});
		menu.add(item);

		item = new JMenuItem("About...");
		item.setMnemonic(KeyEvent.VK_A);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		menu.add(item);
	}

	/**
	 * Create the toolbar with all the relevant buttons.
	 * 
	 * @return the toolbar.
	 */
	private JToolBar makeToolbar() {
		JToolBar toolbar = new JToolBar("Main Toolbar");
		toolbar.setFloatable(false);

		JButton button;

		button = createButton("Open");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		toolbar.add(button);

		button = createButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		toolbar.add(button);

		button = createButton("SaveAs");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		toolbar.add(button);

		toolbar.addSeparator();

		return toolbar;
	}

	/**
	 * Create a JButton with the correct icon from the Java Look and Feel
	 * toolbarIcons from a given name.
	 * 
	 * @param name
	 *            the name of the icon.
	 * @return the button.
	 */
	private JButton createButton(String name) {
		JButton button = new JButton();
		try {
			File file = new File(ICON_PATH + name + "24.gif");
			Image img = ImageIO.read(file);
			ImageIcon icon = new ImageIcon(img);
			button.setIcon(icon);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setBorder(null);
			button.setBackground(null);
			button.setOpaque(false);
		} catch (IOException e) {
			// If icon can't be loaded, create a text-based button:
			button.setText(name);
		}
		return button;
	}

}
