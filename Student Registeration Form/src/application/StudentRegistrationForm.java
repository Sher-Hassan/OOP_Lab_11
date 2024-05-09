package application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistrationForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField nameField, idField, emailField, courseField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JCheckBox termsCheckBox;
    private DefaultTableModel tableModel;

    public StudentRegistrationForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Student Registration Form");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE); 

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        initializeTableModel();
        JScrollPane tableScrollPane = createTableScrollPane();
        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(6, 1));
        formPanel.setBackground(Color.WHITE);

        nameField = new JTextField();
        formPanel.add(createLabeledField("Name:", nameField, 300, 30));

        idField = new JTextField();
        formPanel.add(createLabeledField("ID:", idField, 300, 30));

        emailField = new JTextField();
        formPanel.add(createLabeledField("Email:", emailField, 300, 30));

        courseField = new JTextField();
        formPanel.add(createLabeledField("Course:", courseField, 300, 30));

        JPanel genderPanel = createGenderPanel();
        formPanel.add(createLabeledPanel("Gender:", genderPanel));

        termsCheckBox = new JCheckBox("I agree");
        termsCheckBox.setBackground(Color.WHITE);
        termsCheckBox.setPreferredSize(new Dimension(150, 30)); // Adjust checkbox size
        formPanel.add(createLabeledPanel("Agree to Terms:", termsCheckBox));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> addStudentToTable());
        formPanel.add(submitButton);

        return formPanel;
    }

    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.setBackground(Color.WHITE);
        return genderPanel;
    }

    private JScrollPane createTableScrollPane() {
        JTable table = new JTable(tableModel);
        return new JScrollPane(table);
    }

    private void initializeTableModel() {
        String[] columnNames = {"Name", "ID", "Email", "Course", "Gender", "Agreed to Terms"};
        tableModel.setColumnIdentifiers(columnNames);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private JPanel createLabeledPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);
        panel.add(createLabel(labelText));
        panel.add(component);
        return panel;
    }

    private JPanel createLabeledField(String labelText, JTextField field, int width, int height) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);
        panel.add(createLabel(labelText));
        field.setPreferredSize(new Dimension(width, height)); // Adjust text field size
        panel.add(field);
        return panel;
    }

    private void addStudentToTable() {
        String name = nameField.getText();
        String id = idField.getText();
        String email = emailField.getText();
        String course = courseField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        String agreedToTerms = termsCheckBox.isSelected() ? "Yes" : "No";
        Object[] rowData = {name, id, email, course, gender, agreedToTerms};
        tableModel.addRow(rowData);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRegistrationForm::new);
    }
}
