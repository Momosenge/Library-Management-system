package com.example.mylms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchFormButton; // Button to switch between Login and Registration forms
    private boolean isLoginForm = true; // Track whether LoginForm or RegistrationForm is currently active

    public LoginForm() {
        // Initialize JFrame
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        switchFormButton = new JButton("Switch to Registration"); // Initialize switch form button

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

        // Password label and field
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Login button
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(loginButton, constraints);

        // Switch form button
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(switchFormButton, constraints);

        // Add panel to JFrame
        getContentPane().add(panel);

        // Set JFrame properties
        pack(); // pack components for preferred size
        setLocationRelativeTo(null); // center JFrame on screen

        // Add ActionListener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login logic here
                // For demo, just print username and password
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                System.out.println("Username: " + username);
                System.out.println("Password: " + new String(password));
            }
        });

        // Add ActionListener for switch form button
        switchFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between LoginForm and RegistrationForm
                if (isLoginForm) {
                    // Switch to RegistrationForm
                    RegistrationForm registrationForm = new RegistrationForm();
                    registrationForm.setVisible(true);
                    dispose(); // Close current LoginForm
                } else {
                    // Switch back to LoginForm
                    switchFormButton.setText("Switch to Registration");
                    setTitle("Login Form");
                    isLoginForm = true;
                    // Clear fields if needed
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}
