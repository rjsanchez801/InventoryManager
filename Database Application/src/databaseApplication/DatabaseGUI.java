package databaseApplication;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

/**
 * 
 * @author Ramon Sanchez, Austin Poch
 *
 */
public class DatabaseGUI extends JFrame implements ComponentListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private int width = 800;
	private int height = 600;

	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem addMenuBtn = new JMenuItem("Add");
	private JMenuItem updateMenuBtn = new JMenuItem("Update");
	private JMenuItem deleteMenuBtn = new JMenuItem("Delete");
	private JMenuItem queryMenuBtn = new JMenuItem("Queries");

	private JPanel output = new JPanel();
	private JLabel lblTableName = new JLabel();
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String[] colNames;
	private String[][] rowDataArray;

	private JPanel control = new JPanel();
	private CardLayout cardLayout = new CardLayout();

	private JPanel addPanel = new JPanel();
	private JLabel carModelAddLabel = new JLabel();
	private JLabel carStyleAddLabel = new JLabel();
	private JLabel carYearAddLabel = new JLabel();
	private JLabel carPriceAddLabel = new JLabel();
	private JLabel lblCarAddMaker = new JLabel();
	private JButton addCarBtn = new JButton();

	private JPanel updatePanel = new JPanel();
	private JLabel carModelUpdateLabel = new JLabel();
	private JLabel carStyleUpdateLabel = new JLabel();
	private JLabel carYearUpdateLabel = new JLabel();
	private JLabel carPriceUpdateLabel = new JLabel();
	private JLabel lblCarUpdateMaker = new JLabel();
	private JLabel updateCarLabel = new JLabel();
	private JButton updateCarBtn = new JButton();

	private JPanel deletePanel = new JPanel();
	private JLabel deleteCarLabel = new JLabel();
	private JButton deleteCarBtn = new JButton();

	private JPanel queryPanel = new JPanel();
	private JLabel lblQueryMakeStyle = new JLabel();
	private JLabel lblMake = new JLabel();
	private JLabel lblStyle = new JLabel();
	private JButton carMakeBtn = new JButton();
	private JLabel lblQueryCountry = new JLabel();
	private JLabel lblCountry = new JLabel();
	private JButton btnGetCountry = new JButton();
	private JLabel labelQueryPrice = new JLabel();
	private JLabel labelToPrice = new JLabel();
	private JLabel labelFromPrice = new JLabel();
	private JButton priceCarBtn = new JButton();
	private JLabel labelQueryYear = new JLabel();
	private JLabel labelFromYear = new JLabel();
	private JLabel labelToYear = new JLabel();
	private JButton btnGetCars = new JButton();

	private static Choice carMakerChoice = new Choice();
	private static Choice carMakerUpdate = new Choice();
	private static Choice updateCarID = new Choice();
	private static Choice deleteCarID = new Choice();
	private static Choice makeDropDown = new Choice();
	private static Choice beginYear = new Choice();
	private static Choice endYear = new Choice();
	private static Choice beginPrice = new Choice();
	private static Choice endPrice = new Choice();
	private static Choice styleDropDown = new Choice();
	private static Choice carCountry = new Choice();

	private static JTextField carModelField;
	private static JTextField carStyleField;
	private static JTextField carYearField;
	private static JTextField carPriceField;
	private static JTextField carModelFieldAdd;
	private static JTextField carStyleFieldAdd;
	private static JTextField carYearFieldAdd;
	private static JTextField carPriceFieldAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					DatabaseLogic.databaseQueries();
					DatabaseGUI frame = new DatabaseGUI();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Set up GUI
	 */
	public DatabaseGUI() {

		// Creates contentPane then adds the control and output panels to it.
		createContainers();

		// Creates/updates the table and sets the selected values in update panel
		createTable();

		// Menu Bar with Add, Update, Delete, Query, buttons
		carJMenuBar();

		// JPanel setup for Add Car information
		addCarJPanel();

		// JPanel setup for Update Car information
		updateCarJPanel();

		// JPanel setup for Delete Car information
		deleteCarJPanel();

		// JPanel setup for Query Car information
		queryCarJPanel();

	}

	/**
	 * Creates contentPane then adds the control and output panels to it.
	 */
	private void createContainers() {
		// Configures contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addComponentListener(this);

		// Configures control panel
		control.setBounds(0, 0, 232, 484);
		control.setBackground(Color.WHITE);
		contentPane.add(control);
		control.setLayout(cardLayout);

		// Configures output panel
		output.setBounds(232, 0, 540, 484);
		output.setBackground(new Color(255, 255, 255));
		contentPane.add(output);
		output.setLayout(null);
		output.add(scrollPane);

	}

	/**
	 * Creates/updates the table and sets the selected values in update panel
	 */
	private void createTable() {
		lblTableName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		// Creates table name label
		lblTableName.setText(DatabaseLogic.tableName);
		lblTableName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableName.setBounds(10, 0, output.getWidth(), 30);
		output.add(lblTableName);

		// Saves table data
		colNames = new String[DatabaseLogic.colCount];
		rowDataArray = new String[DatabaseLogic.rowCount - 1][DatabaseLogic.colCount];

		for (int i = 0; i < DatabaseLogic.colCount; i++) {
			colNames[i] = DatabaseLogic.colName.get(i);
		}
		int counter = 0;
		for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
			for (int j = 0; j < DatabaseLogic.colCount; j++) {
				rowDataArray[i][j] = DatabaseLogic.rowData.get(counter);
				counter++;
			}
		}

		// Create table with database data
		model = new DefaultTableModel(rowDataArray, colNames);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setAutoCreateRowSorter(true);
		table.setBounds(0, 0, output.getWidth() - 20, output.getHeight() - 20);
		table.setBackground(new Color(180, 180, 180));

		// Create scroll panel
		scrollPane.setBounds(0 + 10, 0 + 30, output.getWidth() - 20, output.getHeight() - 40);
		scrollPane.setViewportView(table);

		// Pulls database data from selected row of table into update panel's Textfields

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateCarID.select((String) table.getValueAt(table.getSelectedRow(), 0));
				carModelField.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				carStyleField.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				carYearField.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				carPriceField.setText((String) table.getValueAt(table.getSelectedRow(), 4));
			}
		});
	}

	/**
	 * Menu Bar with Add, Update, Delete, Query, buttons
	 */
	private void carJMenuBar() {

		setJMenuBar(menuBar);

		// Add button
		addMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(control, "addPanel");
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
			}
		});
		menuBar.add(addMenuBtn);

		// Update button
		updateMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(control, "updatePanel");
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
			}
		});
		menuBar.add(updateMenuBtn);

		// Delete button
		deleteMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(control, "deletePanel");
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
			}
		});
		menuBar.add(deleteMenuBtn);

		// Query button
		queryMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(control, "queryPanel");

			}
		});
		menuBar.add(queryMenuBtn);
	}

	/**
	 * JPanel setup for Add Car information
	 */
	private void addCarJPanel() {

		control.add(addPanel, "addPanel");
		addPanel.setLayout(null);

		// Car Model label
		carModelAddLabel.setText("Car Model");
		carModelAddLabel.setBounds(13, 94, 86, 14);
		addPanel.add(carModelAddLabel);

		// Car Model user input Textfield
		carModelFieldAdd = new JTextField();
		carModelFieldAdd.setBounds(13, 108, 212, 20);
		carModelFieldAdd.setColumns(10);
		addPanel.add(carModelFieldAdd);

		// Car Style label
		carStyleAddLabel.setText("Car Style");
		carStyleAddLabel.setBounds(13, 130, 86, 14);
		addPanel.add(carStyleAddLabel);

		// Car Style user input Textfield
		carStyleFieldAdd = new JTextField();
		carStyleFieldAdd.setColumns(10);
		carStyleFieldAdd.setBounds(13, 148, 212, 20);
		addPanel.add(carStyleFieldAdd);

		// Car Year label
		carYearAddLabel.setText("Car Year");
		carYearAddLabel.setBounds(13, 173, 86, 14);
		addPanel.add(carYearAddLabel);

		// Car Year user input Textfield
		carYearFieldAdd = new JTextField();
		carYearFieldAdd.setColumns(10);
		carYearFieldAdd.setBounds(13, 195, 212, 20);
		addPanel.add(carYearFieldAdd);
		// Check for number input for Car Year TextField
		carYearFieldAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c))) && (c != '\b')) {
					ke.consume();
					JFrame f = new JFrame();
					f.setLocationRelativeTo(null);
					JLabel numberCheck = new JLabel("Please enter only numbers for year and price.");
					numberCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
					JOptionPane.showMessageDialog(f, numberCheck);
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

		});

		// Car Price label
		carPriceAddLabel.setText("Car Price");
		carPriceAddLabel.setBounds(13, 215, 86, 14);
		addPanel.add(carPriceAddLabel);

		// Car Price user input Textfield
		carPriceFieldAdd = new JTextField();
		carPriceFieldAdd.setColumns(10);
		carPriceFieldAdd.setBounds(13, 235, 212, 20);
		addPanel.add(carPriceFieldAdd);
		// Check for number input for Car Price Textfield
		carPriceFieldAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c))) && (c != '\b')) {
					ke.consume();
					JFrame f = new JFrame();
					f.setLocationRelativeTo(null);
					JLabel numberCheck = new JLabel("Please enter only numbers for year and price.");
					numberCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
					JOptionPane.showMessageDialog(f, numberCheck);
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

		});

		// Car Maker label
		lblCarAddMaker.setText("Car Maker");
		lblCarAddMaker.setBounds(13, 259, 86, 14);
		addPanel.add(lblCarAddMaker);

		// Car Maker drop down
		carMakerChoice.setBounds(13, 279, 86, 20);
		carMakerChoice.add("Honda");
		carMakerChoice.add("Ford");
		carMakerChoice.add("Subaru");
		carMakerChoice.add("Toyota");
		carMakerChoice.add("Dodge");
		addPanel.add(carMakerChoice);

		// Add car button
		addCarBtn.setText("Add Car");
		addCarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.execute(addCar());
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
				addPanelUpdate();
			}
		});
		addCarBtn.setBounds(10, 305, 113, 23);
		addPanel.add(addCarBtn);
	}

	/**
	 * Refreshes the data in the drop down box on the add panel
	 */
	private void addPanelUpdate() {
		updateCarID.removeAll();
		// Add cars IDs and models to update panel's drop down box
		for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
			updateCarID.add((String) DatabaseLogic.rowData.get(i * DatabaseLogic.colCount));
		}
	}

	/**
	 * Grabs user input from the Textfields on the Add Car JPanel and inserts it
	 * into SQL Table
	 * 
	 * @return SQL Table after adding new row
	 */
	public static String addCar() {
		String maker = "";
		if (carMakerUpdate.getSelectedItem() == "Honda") {
			maker = "1";
		} else if (carMakerUpdate.getSelectedItem() == "Ford") {
			maker = "2";
		} else if (carMakerUpdate.getSelectedItem() == "Subaru") {
			maker = "3";
		} else if (carMakerUpdate.getSelectedItem() == "Toyota") {
			maker = "4";
		} else {
			maker = "5";
		}
		return "INSERT INTO CarModel (CarModel, CarStyle, CarYear, CarPrice, CarMakeID) VALUES" + "('"
				+ carModelFieldAdd.getText() + "', '" + carStyleFieldAdd.getText() + "', " + carYearFieldAdd.getText()
				+ ", " + carPriceFieldAdd.getText() + ", " + maker + ")";
	}

	/**
	 * JPanel setup for Update Car information
	 */
	private void updateCarJPanel() {

		// Car ID label
		updateCarLabel.setText("Car ID");
		updateCarLabel.setBounds(13, 98, 40, 22);
		updatePanel.add(updateCarLabel);

		// Car ID drop down
		control.add(updatePanel, "updatePanel");
		updatePanel.setLayout(null);
		updateCarID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
					if (table.getValueAt(i, 0).equals(updateCarID.getSelectedItem()))
						table.setRowSelectionInterval(i, i);
				}
				updateCarID.select((String) table.getValueAt(table.getSelectedRow(), 0));
				carModelField.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				carStyleField.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				carYearField.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				carPriceField.setText((String) table.getValueAt(table.getSelectedRow(), 4));
			}
		});
		updateCarID.setBounds(53, 100, 70, 20);
		updatePanel.add(updateCarID);

		// Car Model label
		carModelUpdateLabel.setText("Car Model");
		carModelUpdateLabel.setBounds(13, 126, 86, 14);
		updatePanel.add(carModelUpdateLabel);

		// Car Model user input Textfield
		carModelField = new JTextField();
		carModelField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				carModelField.selectAll();
			}
		});
		carModelField.setBounds(13, 140, 209, 20);
		carModelField.setColumns(10);
		updatePanel.add(carModelField);

		// Car Style label
		carStyleUpdateLabel.setText("Car Style");
		carStyleUpdateLabel.setBounds(13, 161, 86, 14);
		updatePanel.add(carStyleUpdateLabel);

		// Car Style user input Textfield
		carStyleField = new JTextField();
		carStyleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				carStyleField.selectAll();
			}
		});
		carStyleField.setColumns(10);
		carStyleField.setBounds(13, 180, 209, 20);
		updatePanel.add(carStyleField);

		// Car Year label
		carYearUpdateLabel.setText("Car Year");
		carYearUpdateLabel.setBounds(13, 201, 86, 14);
		updatePanel.add(carYearUpdateLabel);

		// Car Year user input Textfield
		carYearField = new JTextField();
		carYearField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				carYearField.selectAll();
			}
		});
		carYearField.setColumns(10);
		carYearField.setBounds(13, 220, 209, 20);
		updatePanel.add(carYearField);
		// Check for number input for Car Year TextField
		carYearField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c))) && (c != '\b')) {
					ke.consume();
					JFrame f = new JFrame();
					f.setLocationRelativeTo(null);
					JLabel numberCheck = new JLabel("Please enter only numbers for year and price.");
					numberCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
					JOptionPane.showMessageDialog(f, numberCheck);
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// Car Price label
		carPriceUpdateLabel.setText("Car Price");
		carPriceUpdateLabel.setBounds(13, 240, 86, 14);
		updatePanel.add(carPriceUpdateLabel);

		// Car Price user input Textfield
		carPriceField = new JTextField();
		carPriceField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				carPriceField.selectAll();
			}
		});
		carPriceField.setColumns(10);
		carPriceField.setBounds(13, 257, 209, 20);
		updatePanel.add(carPriceField);
		// Check for number input for Car Price TextField
		carPriceField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c))) && (c != '\b')) {
					ke.consume();
					JFrame f = new JFrame();
					f.setLocationRelativeTo(null);
					JLabel numberCheck = new JLabel("Please enter only numbers for year and price.");
					numberCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
					JOptionPane.showMessageDialog(f, numberCheck);
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// Car Update label
		lblCarUpdateMaker.setText("Car Maker");
		lblCarUpdateMaker.setBounds(13, 276, 86, 20);
		updatePanel.add(lblCarUpdateMaker);

		// Car Update user input Textfield
		carMakerUpdate.setBounds(13, 297, 86, 20);
		carMakerUpdate.add("Honda");
		carMakerUpdate.add("Ford");
		carMakerUpdate.add("Subaru");
		carMakerUpdate.add("Toyota");
		carMakerUpdate.add("Dodge");
		updatePanel.add(carMakerUpdate);

		// Refreshes the data in the drop down box on the update panel
		updatePanelUpdate();

		// Update Car button
		updateCarBtn.setText("Update Car");
		updateCarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.execute(updateCar());
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
				updatePanelUpdate();
			}
		});
		updateCarBtn.setBounds(10, 323, 113, 23);
		updatePanel.add(updateCarBtn);
	}

	/**
	 * Refreshes the data in the drop down box on the update panel
	 */
	private void updatePanelUpdate() {
		updateCarID.removeAll();
		// Add cars IDs and models to update panel's drop down box
		for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
			updateCarID.add((String) DatabaseLogic.rowData.get(i * DatabaseLogic.colCount));
		}
	}

	/**
	 * Grabs user input from the Textfields on the Update Car JPanel and inserts it
	 * into SQL Table
	 * 
	 * @return SQL Table after row update
	 */
	public static String updateCar() {
		String maker = "";
		String indexID = updateCarID.getSelectedItem().toString();
		if (carMakerUpdate.getSelectedItem() == "Honda") {
			maker = "1";
		} else if (carMakerUpdate.getSelectedItem() == "Ford") {
			maker = "2";
		} else if (carMakerUpdate.getSelectedItem() == "Subaru") {
			maker = "3";
		} else if (carMakerUpdate.getSelectedItem() == "Toyota") {
			maker = "4";
		} else {
			maker = "5";
		}
		return "UPDATE CarModel " + "SET CarModel = '" + carModelField.getText() + "', CarStyle = '"
				+ carStyleField.getText() + "', CarYear = " + carYearField.getText() + ", CarPrice = "
				+ carPriceField.getText() + ", CarMakeID = " + maker + " " + "WHERE ID = " + indexID + "";
	}

	/**
	 * JPanel setup for Delete Car information
	 */
	private void deleteCarJPanel() {

		control.add(deletePanel, "deletePanel");
		deletePanel.setLayout(null);

		// Delete Car label
		deleteCarLabel.setText("Delete Car by ID");
		deleteCarLabel.setBounds(10, 46, 110, 14);
		deletePanel.add(deleteCarLabel);

		// Delete Car drop down
		deleteCarID.setBounds(10, 66, 86, 20);
		deletePanel.add(deleteCarID);
		deletePanelUpdate();

		// Delete Car button
		deleteCarBtn.setText("Delete Car");
		deleteCarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.execute(deleteCar());
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				createTable();
				deletePanelUpdate();
			}
		});
		deleteCarBtn.setBounds(7, 102, 113, 23);
		deletePanel.add(deleteCarBtn);

	}

	/**
	 * Refreshes the data in the drop down box on the delete panel
	 */
	private void deletePanelUpdate() {
		deleteCarID.removeAll();
		// Add car ID's to delete panel's drop down box
		for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
			deleteCarID.add((String) DatabaseLogic.rowData.get(i * DatabaseLogic.colCount));
		}
		updateCarID.removeAll();
		// Add cars IDs and models to update panel's drop down box
		for (int i = 0; i < DatabaseLogic.rowCount - 1; i++) {
			updateCarID.add((String) DatabaseLogic.rowData.get(i * DatabaseLogic.colCount));
		}
	}

	/**
	 * Grabs user input from the drop down on the Delete Car JPanel and removes it
	 * from the SQL Table
	 * 
	 * @return SQL Table after row delete
	 */
	public static String deleteCar() {
		String indexID = deleteCarID.getSelectedItem().toString();
		return "DELETE FROM CarModel WHERE ID = " + indexID + "";
	}

	/**
	 * JPanel setup for Query Car information
	 */
	private void queryCarJPanel() {

		control.add(queryPanel, "queryPanel");
		queryPanel.setLayout(null);

		// Creates the Make and Style section on the query panel
		queryMakeStyle(queryPanel);

		// Creates the Price section on the query panel
		queryPrice(queryPanel);

		// Creates the Year section on the query panel
		queryYear(queryPanel);

		// Creates the Country section on the query panel
		queryCountry(queryPanel);

	}

	/**
	 * Creates the Make and Style section on the query panel
	 * 
	 * @param queryPanel - the panel being added to
	 */
	private void queryMakeStyle(JPanel queryPanel) {

		// Query Make & Style label
		lblQueryMakeStyle.setText("Query Make & Style");
		lblQueryMakeStyle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQueryMakeStyle.setBounds(10, 11, 212, 20);
		queryPanel.add(lblQueryMakeStyle);

		// Make label
		lblMake.setText("Make:");
		lblMake.setBounds(10, 37, 44, 20);
		queryPanel.add(lblMake);

		// Make drop down
		makeDropDown.setBounds(60, 37, 86, 20);
		makeDropDown.add("Honda");
		makeDropDown.add("Ford");
		makeDropDown.add("Subaru");
		makeDropDown.add("Toyota");
		makeDropDown.add("Dodge");
		queryPanel.add(makeDropDown);

		// Style label
		lblStyle.setText("Style:");
		lblStyle.setBounds(10, 63, 44, 20);
		queryPanel.add(lblStyle);

		// Style drop down
		styleDropDown.setBounds(60, 63, 86, 22);
		styleDropDown.add("SUV");
		styleDropDown.add("Sedan");
		styleDropDown.add("Truck");
		styleDropDown.add("Coupe");
		styleDropDown.add("Van");
		styleDropDown.add("Hybrid");
		queryPanel.add(styleDropDown);

		// Query Make & Style button
		carMakeBtn.setText("Get Cars");
		carMakeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.execute(SqlModelMake.dropJoinTable());
				DatabaseLogic.execute(SqlModelMake.createJoinTable());
				DatabaseLogic.execute(fillJoinTable());
				DatabaseLogic.executeQueries(SqlModelMake.joinTable());
				createTable();

			}
		});
		carMakeBtn.setBounds(10, 94, 140, 23);
		queryPanel.add(carMakeBtn);
	}

	/**
	 * Creates a MySql query with the Make and Style constraints
	 * 
	 * @return SQL query created
	 */
	public static String fillJoinTable() {
		String make;
		if (makeDropDown.getSelectedItem() == "Honda") {
			make = "1";
		} else if (makeDropDown.getSelectedItem() == "Ford") {
			make = "2";
		} else if (makeDropDown.getSelectedItem() == "Subaru") {
			make = "3";
		} else if (makeDropDown.getSelectedItem() == "Toyota") {
			make = "4";
		} else {
			make = "5";
		}
		String style = styleDropDown.getSelectedItem().toString();

		return "INSERT INTO ModelStyle (MakerID, MakerJoinID, StyleID) VALUES" + "(" + make + " , " + make + " , '"
				+ style + "')";
	}

	/**
	 * Creates the Country section on the query panel
	 * 
	 * @param queryPanel - the panel being added to
	 */
	private void queryCountry(JPanel queryPanel) {

		// Query Country label
		lblQueryCountry.setText("Query Country");
		lblQueryCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQueryCountry.setBounds(10, 130, 212, 20);
		queryPanel.add(lblQueryCountry);

		// Country label
		lblCountry.setText("Country:");
		lblCountry.setBounds(10, 156, 50, 20);
		queryPanel.add(lblCountry);

		// Country drop down
		carCountry.setBounds(60, 156, 86, 20);
		carCountry.add("Japan");
		carCountry.add("USA");
		queryPanel.add(carCountry);

		// Country button
		btnGetCountry.setText("Get Cars");
		btnGetCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.execute(SqlModelMake.dropCountryTable());
				DatabaseLogic.execute(SqlModelMake.createCountryTable());
				DatabaseLogic.execute(fillCountryTable());
				DatabaseLogic.executeQueries(SqlModelMake.joinCountryTable());
				createTable();
			}
		});
		btnGetCountry.setBounds(10, 187, 140, 23);
		queryPanel.add(btnGetCountry);
	}

	/**
	 * Creates a MySql query with the Country constraint
	 * 
	 * @return SQL query created
	 */
	public static String fillCountryTable() {
		String maker = "";
		String maker2 = "";
		String maker3 = "";
		if (carCountry.getSelectedItem() == "Japan") {
			maker = "1";
			maker2 = "4";
			maker3 = "3";
			return "INSERT INTO ModelCountry (MakerID, CarCountryID) VALUES" + "(" + maker + ", " + maker + ")," + "("
					+ maker2 + ", " + maker2 + ")," + "(" + maker3 + ", " + maker3 + ")";

		} else if (carCountry.getSelectedItem() == "USA") {
			maker = "2";
			maker2 = "5";
		}
		return "INSERT INTO ModelCountry (MakerID, CarCountryID) VALUES" + "(" + maker + ", " + maker + ")," + "("
				+ maker2 + ", " + maker2 + ")";
	}

	/**
	 * Creates the Price section on the query panel
	 * 
	 * @param queryPanel - the panel being added to
	 */
	private void queryPrice(JPanel queryPanel) {

		// Query Price label
		labelQueryPrice.setText("Query Price");
		labelQueryPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelQueryPrice.setBounds(10, 219, 212, 20);
		queryPanel.add(labelQueryPrice);

		// From label
		labelFromPrice.setText("From:");
		labelFromPrice.setBounds(10, 250, 44, 20);
		queryPanel.add(labelFromPrice);

		// From drop down
		endPrice.setBounds(60, 276, 86, 22);
		endPrice.add("5000");
		endPrice.add("10000");
		endPrice.add("15000");
		endPrice.add("20000");
		endPrice.add("25000");
		endPrice.add("30000");
		endPrice.add("35000");
		endPrice.add("40000");
		endPrice.add("45000");
		queryPanel.add(endPrice);

		// To label
		labelToPrice.setText("To:");
		labelToPrice.setBounds(10, 276, 44, 20);
		queryPanel.add(labelToPrice);

		// To drop down
		beginPrice.setBounds(60, 250, 86, 22);
		beginPrice.add("5000");
		beginPrice.add("10000");
		beginPrice.add("15000");
		beginPrice.add("20000");
		beginPrice.add("25000");
		beginPrice.add("30000");
		beginPrice.add("35000");
		beginPrice.add("40000");
		beginPrice.add("45000");
		queryPanel.add(beginPrice);

		// Price button
		priceCarBtn.setText("Get Cars");
		priceCarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				DatabaseLogic.executeQueries(getCarPrice());
				createTable();

			}
		});
		priceCarBtn.setBounds(10, 302, 136, 23);
		queryPanel.add(priceCarBtn);
	}

	/**
	 * Creates a MySql query with the Price constraints
	 * 
	 * @return SQL query created
	 */
	public static String getCarPrice() {
		String start = beginPrice.getSelectedItem().toString();
		String end = endPrice.getSelectedItem().toString();
		return "SELECT * FROM CarModel WHERE CarPrice BETWEEN " + start + " AND " + end + "";
	}

	/**
	 * Creates the Year section on the query panel
	 * 
	 * @param queryPanel - the panel being added to
	 */
	private void queryYear(JPanel queryPanel) {

		// Query Year label
		labelQueryYear.setText("Query Year");
		labelQueryYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelQueryYear.setBounds(10, 349, 212, 20);
		queryPanel.add(labelQueryYear);

		// From Year label
		labelFromYear.setText("From:");
		labelFromYear.setBounds(10, 380, 44, 20);
		queryPanel.add(labelFromYear);

		// From Year drop down
		beginYear.setBounds(60, 375, 86, 22);
		beginYear.add("2010");
		beginYear.add("2011");
		beginYear.add("2012");
		beginYear.add("2013");
		beginYear.add("2014");
		beginYear.add("2015");
		beginYear.add("2016");
		beginYear.add("2017");
		beginYear.add("2018");
		beginYear.add("2019");
		beginYear.add("2020");
		queryPanel.add(beginYear);

		// To Year label
		labelToYear.setText("To:");
		labelToYear.setBounds(10, 401, 44, 20);
		queryPanel.add(labelToYear);

		// To Year drop down
		endYear.setBounds(60, 401, 86, 22);
		endYear.add("2010");
		endYear.add("2011");
		endYear.add("2012");
		endYear.add("2013");
		endYear.add("2014");
		endYear.add("2015");
		endYear.add("2016");
		endYear.add("2017");
		endYear.add("2018");
		endYear.add("2019");
		endYear.add("2020");
		queryPanel.add(endYear);

		// Year button
		btnGetCars.setText("Get Cars");
		btnGetCars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseLogic.executeQueries(SqlModel.getAllCarMake());
				DatabaseLogic.executeQueries(getCarYear());
				createTable();
			}
		});
		btnGetCars.setBounds(10, 427, 136, 23);
		queryPanel.add(btnGetCars);
	}

	/**
	 * Creates a MySql query with the Year constraints
	 * 
	 * @return SQL query created
	 */
	public static String getCarYear() {
		String start = beginYear.getSelectedItem().toString();
		String end = endYear.getSelectedItem().toString();
		return "SELECT * FROM CarModel WHERE CarYear BETWEEN " + start + " AND " + end + "";
	}

	/**
	 * Responsive window resizing for top containers and table
	 * 
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		control.setBounds(0, 0, (int) (contentPane.getWidth() * .3), contentPane.getHeight());
		output.setBounds((int) (contentPane.getWidth() * .3), 0, (int) (contentPane.getWidth() * .7),
				contentPane.getHeight());
		scrollPane.setBounds(0 + 10, 0 + 30, output.getWidth() - 20, output.getHeight() - 40);
		table.setBounds(0, 0, output.getWidth() - 20, output.getHeight() - 20);
		lblTableName.setBounds(10, 0, output.getWidth(), 30);

	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}