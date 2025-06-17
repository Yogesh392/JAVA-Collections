import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoWayColorfulChat extends JFrame {

    private JTextArea chatAreaLeft, chatAreaRight;
    private JTextArea messageAreaLeft, messageAreaRight;
    private JButton sendButtonLeft, sendButtonRight;

    public TwoWayColorfulChat() {
        setTitle("Two-Way Colorful Chat Messenger");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BorderLayout for main frame
        setLayout(new BorderLayout());

        // Create left chat panel (User 1)
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("User 1"));
        chatAreaLeft = createChatArea(new Color(240, 255, 255));  // Light Cyan background
        messageAreaLeft = createMessageArea();
        sendButtonLeft = createSendButton(new Color(70, 130, 180)); // Steel Blue

        JScrollPane scrollPaneLeft = new JScrollPane(chatAreaLeft);
        JScrollPane messageScrollLeft = new JScrollPane(messageAreaLeft);

        JPanel inputPanelLeft = new JPanel(new BorderLayout(5, 5));
        inputPanelLeft.add(messageScrollLeft, BorderLayout.CENTER);
        inputPanelLeft.add(sendButtonLeft, BorderLayout.EAST);

        leftPanel.add(scrollPaneLeft, BorderLayout.CENTER);
        leftPanel.add(inputPanelLeft, BorderLayout.SOUTH);

        // Create right chat panel (User 2)
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("User 2"));
        chatAreaRight = createChatArea(new Color(255, 240, 245)); // Lavender Blush background
        messageAreaRight = createMessageArea();
        sendButtonRight = createSendButton(new Color(255, 105, 180)); // Hot Pink

        JScrollPane scrollPaneRight = new JScrollPane(chatAreaRight);
        JScrollPane messageScrollRight = new JScrollPane(messageAreaRight);

        JPanel inputPanelRight = new JPanel(new BorderLayout(5, 5));
        inputPanelRight.add(messageScrollRight, BorderLayout.CENTER);
        inputPanelRight.add(sendButtonRight, BorderLayout.EAST);

        rightPanel.add(scrollPaneRight, BorderLayout.CENTER);
        rightPanel.add(inputPanelRight, BorderLayout.SOUTH);

        // Split pane to separate both chat areas
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(450);
        add(splitPane, BorderLayout.CENTER);

        // Add background decoration (over the JFrame's layered pane)
        addShapesBackground();

        // Action for left send button
        sendButtonLeft.addActionListener(e -> {
            sendMessage(messageAreaLeft, chatAreaLeft, chatAreaRight, "User 1");
        });

        // Action for right send button
        sendButtonRight.addActionListener(e -> {
            sendMessage(messageAreaRight, chatAreaRight, chatAreaLeft, "User 2");
        });

        // Enter key press for left input area
        messageAreaLeft.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    sendButtonLeft.doClick();
                }
            }
        });

        // Enter key press for right input area
        messageAreaRight.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    sendButtonRight.doClick();
                }
            }
        });
    }

    // Helper to create chat display areas
    private JTextArea createChatArea(Color bgColor) {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ta.setBackground(bgColor);
        ta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return ta;
    }

    // Helper to create message input areas
    private JTextArea createMessageArea() {
        JTextArea ta = new JTextArea(3, 20);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return ta;
    }

    // Helper to create buttons
    private JButton createSendButton(Color bgColor) {
        JButton btn = new JButton("Send");
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    // Method to send message from one user to the other
    private void sendMessage(JTextArea inputArea, JTextArea senderChat, JTextArea receiverChat, String user) {
        String message = inputArea.getText().trim();
        if (!message.isEmpty()) {
            String formattedMessage = user + ": " + message + "\n";
            senderChat.append(formattedMessage);
            receiverChat.append(formattedMessage);
            inputArea.setText("");
            senderChat.setCaretPosition(senderChat.getDocument().getLength());
            receiverChat.setCaretPosition(receiverChat.getDocument().getLength());
        }
    }

    // Add colorful shapes in the background using glass pane overlay
    private void addShapesBackground() {
        JComponent glass = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                // Transparent gradient overlay
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 192, 203, 80),
                        w, h, new Color(135, 206, 250, 80));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);

                // Draw colorful circles
                g2d.setColor(new Color(255, 105, 180, 60));
                for (int i = 0; i < 6; i++) {
                    int size = 120 + i * 30;
                    g2d.fillOval(50 * i, 30 * i, size, size);
                }

                // Draw colorful rectangles
                g2d.setColor(new Color(30, 144, 255, 60));
                for (int i = 0; i < 4; i++) {
                    int width = 80 + i * 40;
                    int height = 40 + i * 20;
                    g2d.fillRoundRect(200 + 60 * i, 400 - 30 * i, width, height, 30, 30);
                }
            }
        };
        glass.setOpaque(false);
        setGlassPane(glass);
        getGlassPane().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TwoWayColorfulChat chat = new TwoWayColorfulChat();
            chat.setVisible(true);
        });
    }
}