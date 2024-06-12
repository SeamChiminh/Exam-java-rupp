import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

public class Program extends JFrame {
    private JTextField fileName1Field;
    private JTextField fileName2Field;
    private JTextArea textArea;

    public Program() {
        setTitle("File Operation");

        fileName1Field = new JTextField(20);
        fileName2Field = new JTextField(20);
        textArea = new JTextArea(5, 10);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        JButton readButton = new JButton("READ");
        JButton writeButton = new JButton("WRITE");
        JButton deleteButton = new JButton("DELETE");
        JButton copyButton = new JButton("COPY");
        JButton closeButton = new JButton("CLOSE");

        readButton.addActionListener(e -> readFile());
        writeButton.addActionListener(e -> writeFile());
        deleteButton.addActionListener(e -> deleteFile());
        copyButton.addActionListener(e -> copyFile());
        closeButton.addActionListener(e -> closeApp());

        JLabel titleLabel = new JLabel("File Operation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel sourceFilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sourceFilePanel.add(new JLabel("File name 1 :"));
        sourceFilePanel.add(fileName1Field);

        JPanel destFilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        destFilePanel.add(new JLabel("File name 2 :"));
        destFilePanel.add(fileName2Field);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(readButton);
        buttonPanel.add(writeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(closeButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(sourceFilePanel);
        panel.add(destFilePanel);
        panel.add(scrollPane);
        panel.add(buttonPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void readFile() {
        String fileName = fileName1Field.getText();
        try {
            String content = new String(Files.readAllBytes(new File(fileName).toPath()));
            textArea.setText(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }

    private void writeFile() {
        String fileName = fileName1Field.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "File written successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
        }
    }

    private void deleteFile() {
        String fileName = fileName1Field.getText();
        File file = new File(fileName);
        if (file.exists() && file.delete()) {
            JOptionPane.showMessageDialog(this, "File deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting file.");
        }
    }

    private void copyFile() {
        String sourceFileName = fileName1Field.getText();
        String destFileName = fileName2Field.getText();
        try {
            Files.copy(new File(sourceFileName).toPath(), new File(destFileName).toPath());
            JOptionPane.showMessageDialog(this, "File copied successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error copying file: " + e.getMessage());
        }
    }

    private void closeApp() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::new);
    }
}
