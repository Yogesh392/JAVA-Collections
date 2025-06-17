import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame {

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the boiling point of water?",
            "What is the largest mammal?"
    };

    private String[][] options = {
            {"Berlin", "Madrid", "Paris", "London"},
            {"Earth", "Mars", "Jupiter", "Venus"},
            {"William Shakespeare", "Charles Dickens", "Mark Twain", "Leo Tolstoy"},
            {"100°C", "90°C", "120°C", "80°C"},
            {"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}
    };

    private int[] answers = {2, 1, 0, 0, 1};  // Correct option indices

    private int currentQuestion = 0;
    private int score = 0;

    private JLabel lblQuestion;
    private JRadioButton[] radioOptions = new JRadioButton[4];
    private ButtonGroup optionsGroup;
    private JButton btnNext;

    public QuizApp() {
        setTitle("Simple Quiz Application");
        setSize(600, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Question label
        lblQuestion = new JLabel("Question");
        lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblQuestion.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(lblQuestion, BorderLayout.NORTH);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioOptions[i] = new JRadioButton();
            radioOptions[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
            optionsGroup.add(radioOptions[i]);
            optionsPanel.add(radioOptions[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        // Next button
        btnNext = new JButton("Next");
        btnNext.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnNext.setFocusPainted(false);
        btnNext.setBackground(new Color(70, 130, 180));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNext);
        add(buttonPanel, BorderLayout.SOUTH);

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isAnswerSelected()) {
                    checkAnswer();
                    currentQuestion++;
                    if (currentQuestion < questions.length) {
                        loadQuestion(currentQuestion);
                    } else {
                        showResult();
                    }
                } else {
                    JOptionPane.showMessageDialog(QuizApp.this, "Please select an answer.");
                }
            }
        });

        // ✅ Load first question after all radio buttons are ready
        loadQuestion(currentQuestion);
    }

    private void loadQuestion(int index) {
        lblQuestion.setText("Q" + (index + 1) + ": " + questions[index]);
        optionsGroup.clearSelection();
        for (int i = 0; i < 4; i++) {
            radioOptions[i].setText(options[index][i]);
        }
        btnNext.setText(index == questions.length - 1 ? "Submit" : "Next");
    }

    private boolean isAnswerSelected() {
        for (JRadioButton radio : radioOptions) {
            if (radio.isSelected()) {
                return true;
            }
        }
        return false;
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (radioOptions[i].isSelected()) {
                if (i == answers[currentQuestion]) {
                    score++;
                }
                break;
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "Quiz completed!\nYour score: " + score + "/" + questions.length,
                "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuizApp().setVisible(true);
        });
    }
}
// This code implements a simple quiz application using Java Swing.
// It displays a series of questions with multiple-choice answers, allows the user to select an answer,
// and calculates the score at the end. The application is designed to be user-friendly with a