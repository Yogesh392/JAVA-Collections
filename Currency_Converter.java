import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.File;

public class Currency_Converter extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton, toggleThemeButton, historyButton, exportButton, clearButton;
    private JTextArea historyArea;
    private JScrollPane historyScroll;
    private boolean isDarkMode = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR"};
    private double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};

    private java.util.List<String> historyList = new ArrayList<>();

    public Currency_Converter() {
        setTitle("Currency Converter");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        amountLabel = new JLabel("Amount:");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        resultLabel = new JLabel();

        amountField = new JTextField();
        fromComboBox = new JComboBox<>(currencies);
        toComboBox = new JComboBox<>(currencies);

        convertButton = new JButton("Convert");
        toggleThemeButton = new JButton("Toggle Theme");
        historyButton = new JButton("Show History");
        exportButton = new JButton("Export History");
        clearButton = new JButton("Clear All");

        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(fromLabel);
        inputPanel.add(fromComboBox);
        inputPanel.add(toLabel);
        inputPanel.add(toComboBox);
        inputPanel.add(convertButton);
        inputPanel.add(resultLabel);
        inputPanel.add(toggleThemeButton);
        inputPanel.add(historyButton);
        inputPanel.add(exportButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        // History Area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyScroll = new JScrollPane(historyArea);
        add(historyScroll, BorderLayout.CENTER);

        // Convert Button Action
        convertButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = (String) fromComboBox.getSelectedItem();
                String to = (String) toComboBox.getSelectedItem();
                int fromIndex = getIndex(from);
                int toIndex = getIndex(to);

                double rate = exchangeRates[toIndex] / exchangeRates[fromIndex];
                double result = amount * rate;
                String formattedResult = decimalFormat.format(result);
                resultLabel.setText(formattedResult + " " + to);

                String historyEntry = amount + " " + from + " = " + formattedResult + " " + to;
                historyList.add(historyEntry);

                playSound("success.wav"); // 🔊 sound
            } catch (Exception ex) {
                resultLabel.setText("Invalid input");
                playSound("error.wav"); // 🔊 error sound
            }
        });

        // Theme Toggle
        toggleThemeButton.addActionListener(e -> toggleTheme());

        // Show History
        historyButton.addActionListener(e -> {
            historyArea.setText("");
            for (String entry : historyList) {
                historyArea.append(entry + "\n");
            }
        });

        // Export Button
        exportButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("conversion_history.txt")) {
                for (String entry : historyList) {
                    writer.write(entry + "\n");
                }
                JOptionPane.showMessageDialog(this, "History exported to conversion_history.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing file.");
            }
        });

        // Clear Button
        clearButton.addActionListener(e -> {
            amountField.setText("");
            resultLabel.setText("");
            historyList.clear();
            historyArea.setText("");
        });

        setVisible(true);
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) return i;
        }
        return -1;
    }

    private void toggleTheme() {
        Color bgColor = isDarkMode ? Color.WHITE : Color.DARK_GRAY;
        Color fgColor = isDarkMode ? Color.BLACK : Color.WHITE;

        getContentPane().setBackground(bgColor);
        historyArea.setBackground(bgColor);
        historyArea.setForeground(fgColor);
        resultLabel.setForeground(fgColor);
        amountLabel.setForeground(fgColor);
        fromLabel.setForeground(fgColor);
        toLabel.setForeground(fgColor);

        isDarkMode = !isDarkMode;
    }

    // 🔊 Sound Effect Method
    private void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception e) {
            System.out.println("Sound error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Currency_Converter();
    }
}
// This code implements a Currency Converter application using Java Swing.
// It allows users to convert between different currencies, toggle between light and dark themes,