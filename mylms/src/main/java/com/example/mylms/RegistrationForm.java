package com.example.mylms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField contactNumberField;
    private JTextArea addressArea;
    private JTextField interestField;
    private JButton registerButton;
    private JButton backButton;

    public RegistrationForm() {
        // Initialize JFrame
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        usernameField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        contactNumberField = new JTextField(20);
        addressArea = new JTextArea(5, 20);
        interestField = new JTextField(20);
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");

        // Layout using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Username label and field
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        // Name label and field
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        // Email label and field
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        // Password label and field
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Confirm Password label and field
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Confirm Password:"), constraints);

        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        // Contact Number label and field
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Contact Number:"), constraints);

        constraints.gridx = 1;
        panel.add(contactNumberField, constraints);

        // Address label and field
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Address:"), constraints);

        constraints.gridx = 1;
        panel.add(new JScrollPane(addressArea), constraints);

        // Interest label and field
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(new JLabel("Interest:"), constraints);

        constraints.gridx = 1;
        panel.add(interestField, constraints);

        // Register button
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(registerButton, constraints);

        // Back button
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(backButton, constraints);

        // Add panel to JFrame
        getContentPane().add(panel);

        // Set JFrame properties
        pack(); // pack components for preferred size
        setLocationRelativeTo(null); // center JFrame on screen

        // Add ActionListener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate all fields are filled out
                if (validateFields()) {
                    // Proceed with registration logic
                    String username = usernameField.getText();
                    String name = nameField.getText();
                    String email = emailField.getText();
                    char[] password = passwordField.getPassword();
                    char[] confirmPassword = confirmPasswordField.getPassword();
                    String contactNumber = contactNumberField.getText();
                    String address = addressArea.getText();
                    String interest = interestField.getText();

                    // For demo purposes, just print out the registered user information
                    System.out.println("Registered User Information:");
                    System.out.println("Username: " + username);
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Password: " + new String(password));
                    System.out.println("Confirm Password: " + new String(confirmPassword));
                    System.out.println("Contact Number: " + contactNumber);
                    System.out.println("Address: " + address);
                    System.out.println("Interest: " + interest);

                    // Generate library card details
                    String libraryCardNumber = generateLibraryCardNumber(username);
                    Date expiryDate = generateExpiryDate();

                    // Display library card information
                    showLibraryCard(username, libraryCardNumber, expiryDate);

                    // Optionally, store user information in database here

                    // Optionally, switch back to login form after registration
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                    dispose(); // Close current RegistrationForm
                } else {
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Please fill out all required fields.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add ActionListener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to login form
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose(); // Close current RegistrationForm
            }
        });
    }

    // Method to validate all fields are filled out
    private boolean validateFields() {
        String username = usernameField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        String contactNumber = contactNumberField.getText();
        String address = addressArea.getText();
        String interest = interestField.getText();

        // Check if any field is empty
        if (username.isEmpty() || name.isEmpty() || email.isEmpty() || password.length == 0 ||
                confirmPassword.length == 0 || contactNumber.isEmpty() || address.isEmpty() || interest.isEmpty()) {
            return false;
        }

        return true;
    }

    // Method to generate a unique library card number
    private String generateLibraryCardNumber(String username) {
        // Example implementation: concatenate username and a random number
        int randomNumber = (int) (Math.random() * 1000000); // Generate random number
        return username.substring(0, Math.min(username.length(), 5)).toUpperCase() + "-" + randomNumber;
    }

    // Method to generate an expiry date for the library card (example
    // implementation)
    private Date generateExpiryDate() {
        // Example implementation: generate expiry date as 1 year from current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1); // Add 1 year
        return calendar.getTime();
    }

    // Method to display the library card information
    private void showLibraryCard(String username, String libraryCardNumber, Date expiryDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedExpiryDate = dateFormat.format(expiryDate);

        JOptionPane.showMessageDialog(this,
                "Library Card Details:\n" +
                        "Name: " + username + "\n" +
                        "Library Card Number: " + libraryCardNumber + "\n" +
                        "Expiry Date: " + formattedExpiryDate,
                "Library Card Generated",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            RegistrationForm registrationForm = new RegistrationForm();
            registrationForm.setVisible(true);
        });
    }
}
