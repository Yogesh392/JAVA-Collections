import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Rental {
    String carModel, carNumber, customerName, contact, days;

    Rental(String carModel, String carNumber, String customerName, String contact, String days) {
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.customerName = customerName;
        this.contact = contact;
        this.days = days;
    }
}

public class CarRentalSystem extends JFrame {

    private JTextField tfCarModel, tfCarNumber, tfCustomerName, tfContact, tfDays, tfSearch;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Rental> rentalList = new ArrayList<>();

    public CarRentalSystem() {
        setTitle("Car Rental Management System");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Car Rental Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 102, 204));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        form.setBackground(new Color(240, 248, 255));

        tfCarModel = new JTextField();
        tfCarNumber = new JTextField();
        tfCustomerName = new JTextField();
        tfContact = new JTextField();
        tfDays = new JTextField();
        tfSearch = new JTextField();

        form.add(new JLabel("Car Model:"));
        form.add(tfCarModel);
        form.add(new JLabel("Car Number:"));
        form.add(tfCarNumber);
        form.add(new JLabel("Customer Name:"));
        form.add(tfCustomerName);
        form.add(new JLabel("Contact No.:"));
        form.add(tfContact);
        form.add(new JLabel("Rental Days:"));
        form.add(tfDays);

        JButton btnAdd = new JButton("Add Rental");
        JButton btnSearch = new JButton("Search by Customer");

        form.add(btnAdd);
        form.add(btnSearch);
        add(form, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Car Model", "Car No.", "Customer", "Contact", "Days"}, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBackground(new Color(240, 248, 255));
        bottom.add(new JLabel("Search:"));
        bottom.add(tfSearch);
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnShowAll = new JButton("Show All");
        bottom.add(btnShowAll);
        bottom.add(btnDelete);
        add(bottom, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addRental());
        btnSearch.addActionListener(e -> searchRental());
        btnDelete.addActionListener(e -> deleteRental());
        btnShowAll.addActionListener(e -> showAllRentals());

        // Sample data
        rentalList.add(new Rental("Toyota Camry", "KA-01-AA-1234", "Alice", "9876543210", "3"));
        showAllRentals();
    }

    private void addRental() {
        String carModel = tfCarModel.getText().trim();
        String carNumber = tfCarNumber.getText().trim();
        String customer = tfCustomerName.getText().trim();
        String contact = tfContact.getText().trim();
        String days = tfDays.getText().trim();

        if (carModel.isEmpty() || carNumber.isEmpty() || customer.isEmpty() || contact.isEmpty() || days.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        rentalList.add(new Rental(carModel, carNumber, customer, contact, days));
        showAllRentals();

        tfCarModel.setText("");
        tfCarNumber.setText("");
        tfCustomerName.setText("");
        tfContact.setText("");
        tfDays.setText("");
    }

    private void showAllRentals() {
        model.setRowCount(0);
        for (Rental r : rentalList) {
            model.addRow(new Object[]{r.carModel, r.carNumber, r.customerName, r.contact, r.days});
        }
    }

    private void searchRental() {
        String search = tfSearch.getText().trim();
        model.setRowCount(0);
        for (Rental r : rentalList) {
            if (r.customerName.equalsIgnoreCase(search)) {
                model.addRow(new Object[]{r.carModel, r.carNumber, r.customerName, r.contact, r.days});
            }
        }
    }

    private void deleteRental() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a rental to delete.");
            return;
        }

        String carNo = model.getValueAt(row, 1).toString();
        rentalList.removeIf(r -> r.carNumber.equals(carNo));
        showAllRentals();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarRentalSystem().setVisible(true));
    }
}