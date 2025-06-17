import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class CrimeRecord {
    String id, crimeType, location, accusedName, officer;

    CrimeRecord(String id, String crimeType, String location, String accusedName, String officer) {
        this.id = id;
        this.crimeType = crimeType;
        this.location = location;
        this.accusedName = accusedName;
        this.officer = officer;
    }
}

public class CrimeRecordSystem extends JFrame {

    private JTextField tfId, tfLocation, tfAccused, tfOfficer, tfSearch;
    private JComboBox cbCrimeType;
    private DefaultTableModel tableModel;
    private JTable table;
    private ArrayList<CrimeRecord> crimeList = new ArrayList<>();

    public CrimeRecordSystem() {
        setTitle("Crime Record Management System");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Crime Record Management System", JLabel.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(Color.RED);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(new Color(255, 240, 240));

        tfId = new JTextField();
        cbCrimeType = new JComboBox(new String[]{"Theft", "Murder", "Fraud", "Assault", "Cybercrime"});
        tfLocation = new JTextField();
        tfAccused = new JTextField();
        tfOfficer = new JTextField();
        tfSearch = new JTextField();

        formPanel.add(new JLabel("Crime ID:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Crime Type:"));
        formPanel.add(cbCrimeType);
        formPanel.add(new JLabel("Location:"));
        formPanel.add(tfLocation);
        formPanel.add(new JLabel("Accused Name:"));
        formPanel.add(tfAccused);
        formPanel.add(new JLabel("Officer In Charge:"));
        formPanel.add(tfOfficer);

        JButton btnAdd = new JButton("Add Record");
        JButton btnSearch = new JButton("Search Crime Type");
        formPanel.add(btnAdd);
        formPanel.add(btnSearch);

        add(formPanel, BorderLayout.WEST);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Crime Type", "Location", "Accused", "Officer"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(255, 240, 240));
        bottomPanel.add(new JLabel("Search Crime Type:"));
        bottomPanel.add(tfSearch);

        JButton btnShowAll = new JButton("Show All");
        JButton btnDelete = new JButton("Delete Selected");
        bottomPanel.add(btnShowAll);
        bottomPanel.add(btnDelete);
        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        btnAdd.addActionListener(e -> addRecord());
        btnSearch.addActionListener(e -> searchCrimeType());
        btnDelete.addActionListener(e -> deleteRecord());
        btnShowAll.addActionListener(e -> showAllRecords());

        // Sample Record
        crimeList.add(new CrimeRecord("C101", "Theft", "NYC", "John Doe", "Officer Mark"));
        showAllRecords();
    }

    private void addRecord() {
        String id = tfId.getText().trim();
        String crimeType = cbCrimeType.getSelectedItem().toString();
        String location = tfLocation.getText().trim();
        String accused = tfAccused.getText().trim();
        String officer = tfOfficer.getText().trim();

        if (id.isEmpty() || location.isEmpty() || accused.isEmpty() || officer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        crimeList.add(new CrimeRecord(id, crimeType, location, accused, officer));
        showAllRecords();

        tfId.setText("");
        tfLocation.setText("");
        tfAccused.setText("");
        tfOfficer.setText("");
    }

    private void showAllRecords() {
        tableModel.setRowCount(0);
        for (CrimeRecord cr : crimeList) {
            tableModel.addRow(new Object[]{cr.id, cr.crimeType, cr.location, cr.accusedName, cr.officer});
        }
    }

    private void searchCrimeType() {
        String search = tfSearch.getText().trim();
        tableModel.setRowCount(0);
        for (CrimeRecord cr : crimeList) {
            if (cr.crimeType.equalsIgnoreCase(search)) {
                tableModel.addRow(new Object[]{cr.id, cr.crimeType, cr.location, cr.accusedName, cr.officer});
            }
        }
    }

    private void deleteRecord() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a record to delete.");
            return;
        }
        String id = tableModel.getValueAt(row, 0).toString();
        crimeList.removeIf((CrimeRecord cr) -> cr.id.equals(id));
        showAllRecords();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrimeRecordSystem().setVisible(true));
    }
}
// This code implements a simple Crime Record Management System using Java Swing.
// It allows users to add, search, and delete crime records, displaying them in a table format.
// The system includes fields for crime ID, type, location, accused name, and officer in charge.
// It also provides a search functionality to filter records by crime type and a button to show all records.
// The records are stored in an ArrayList and displayed using a JTable with a DefaultTableModel.
// The GUI is designed with a modern look using colors and layout managers for better user experience.
