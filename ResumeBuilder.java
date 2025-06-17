import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ResumeBuilder extends JFrame {

    JTextField nameField, emailField, phoneField, skillsField, eduField, expField;
    JTextArea previewArea;

    public ResumeBuilder() {
        setTitle("📄 Creative Resume Builder");
        setSize(700, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 250, 240));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("🎯 Resume Builder");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBounds(240, 10, 300, 30);
        add(title);

        JLabel name = new JLabel("👤 Name:");
        name.setBounds(30, 60, 100, 25);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(140, 60, 200, 25);
        add(nameField);

        JLabel email = new JLabel("📧 Email:");
        email.setBounds(30, 100, 100, 25);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(140, 100, 200, 25);
        add(emailField);

        JLabel phone = new JLabel("📱 Phone:");
        phone.setBounds(30, 140, 100, 25);
        add(phone);

        phoneField = new JTextField();
        phoneField.setBounds(140, 140, 200, 25);
        add(phoneField);

        JLabel skills = new JLabel("🛠 Skills:");
        skills.setBounds(30, 180, 100, 25);
        add(skills);

        skillsField = new JTextField();
        skillsField.setBounds(140, 180, 200, 25);
        add(skillsField);

        JLabel edu = new JLabel("🎓 Education:");
        edu.setBounds(30, 220, 100, 25);
        add(edu);

        eduField = new JTextField();
        eduField.setBounds(140, 220, 200, 25);
        add(eduField);

        JLabel exp = new JLabel("🏢 Experience:");
        exp.setBounds(30, 260, 100, 25);
        add(exp);

        expField = new JTextField();
        expField.setBounds(140, 260, 200, 25);
        add(expField);

        JButton generateBtn = new JButton("📝 Generate Resume");
        generateBtn.setBounds(400, 60, 250, 30);
        generateBtn.setBackground(new Color(144, 238, 144));
        add(generateBtn);

        JButton saveBtn = new JButton("📥 Save as Text");
        saveBtn.setBounds(400, 100, 250, 30);
        saveBtn.setBackground(new Color(173, 216, 230));
        add(saveBtn);

        JButton resetBtn = new JButton("🔄 Reset");
        resetBtn.setBounds(400, 140, 250, 30);
        resetBtn.setBackground(new Color(255, 182, 193));
        add(resetBtn);

        previewArea = new JTextArea();
        previewArea.setBounds(30, 320, 620, 220);
        previewArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        previewArea.setBorder(BorderFactory.createTitledBorder("📄 Resume Preview"));
        add(previewArea);

        generateBtn.addActionListener(e -> generateResume());
        saveBtn.addActionListener(e -> saveResume());
        resetBtn.addActionListener(e -> resetFields());

        setVisible(true);
    }

    void generateResume() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String skills = skillsField.getText();
        String education = eduField.getText();
        String experience = expField.getText();

        String resume = "==================== RESUME ====================\n";
        resume += "👤 Name: " + name + "\n";
        resume += "📧 Email: " + email + "\n";
        resume += "📱 Phone: " + phone + "\n";
        resume += "🛠 Skills: " + skills + "\n";
        resume += "🎓 Education: " + education + "\n";
        resume += "🏢 Experience: " + experience + "\n";
        resume += "===============================================\n";

        previewArea.setText(resume);
    }

    void saveResume() {
        try {
            FileWriter writer = new FileWriter("MyResume.txt");
            writer.write(previewArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "Resume saved as MyResume.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving file!");
        }
    }

    void resetFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        skillsField.setText("");
        eduField.setText("");
        expField.setText("");
        previewArea.setText("");
    }

    public static void main(String[] args) {
        new ResumeBuilder();
    }
}
