import javax.swing.*;
import java.awt.*;

public class AlumniManagementSystem extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;

    public AlumniManagementSystem() {
        setTitle("Alumni Connect - All in One Frame");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create and add all screens
        cardPanel.add(loginPanel(), "Login");
        cardPanel.add(dashboardPanel(), "Dashboard");
        cardPanel.add(addAlumniPanel(), "AddAlumni");
        cardPanel.add(viewAlumniPanel(), "ViewAlumni");
        cardPanel.add(galleryPanel(), "Gallery");

        add(cardPanel);
        cardLayout.show(cardPanel, "Login");
        setVisible(true);
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Welcome to Alumni Connect", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));
        panel.add(label, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel dashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton addBtn = new JButton("Add Alumni");
        JButton viewBtn = new JButton("View Alumni");
        JButton galleryBtn = new JButton("Gallery");
        JButton exitBtn = new JButton("Exit");

        addBtn.addActionListener(e -> cardLayout.show(cardPanel, "AddAlumni"));
        viewBtn.addActionListener(e -> cardLayout.show(cardPanel, "ViewAlumni"));
        galleryBtn.addActionListener(e -> cardLayout.show(cardPanel, "Gallery"));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(galleryBtn);
        panel.add(exitBtn);
        return panel;
    }

    private JPanel addAlumniPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField batchField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField jobField = new JTextField();
        JButton uploadBtn = new JButton("Upload Photo");
        JButton saveBtn = new JButton("Save");

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Batch:")); panel.add(batchField);
        panel.add(new JLabel("Department:")); panel.add(deptField);
        panel.add(new JLabel("Email:")); panel.add(emailField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Job Title:")); panel.add(jobField);
        panel.add(uploadBtn); panel.add(new JLabel(""));
        panel.add(saveBtn); panel.add(new JLabel(""));

        saveBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Alumni details saved!"));
        return panel;
    }

    private JPanel viewAlumniPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea alumniArea = new JTextArea("Sample Alumni List\n(Name, Batch, Department...)");
        alumniArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(alumniArea);
        panel.add(scrollPane);
        return panel;
    }

    private JPanel galleryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(new ImageIcon("resources/images/sample_event.jpg")); // Place your image in this path
        panel.add(imgLabel, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlumniManagementSystem::new);
    }
}