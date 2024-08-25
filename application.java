package realestate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Application extends JFrame {

    private final UserDAO userDAO = new UserDAO();
    private final PropertyDAO propertyDAO = new PropertyDAO();
    private User loggedInUser = null;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public Application() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setTitle("Real Estate Application");
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        loginButton.addActionListener(e -> login(usernameField.getText(), new String(passwordField.getPassword())));
        registerButton.addActionListener(e -> createAccount(usernameField.getText(), new String(passwordField.getPassword())));

        JPanel mainPanel = new JPanel();
        JButton addPropertyButton = new JButton("Add Property");
        JButton searchPropertiesButton = new JButton("Search Properties");
        JButton logoutButton = new JButton("Logout");

        mainPanel.add(addPropertyButton);
        mainPanel.add(searchPropertiesButton);
        mainPanel.add(logoutButton);

        addPropertyButton.addActionListener(e -> addProperty());
        searchPropertiesButton.addActionListener(e -> searchProperties());
        logoutButton.addActionListener(e -> logout());
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(mainPanel, "Main");

        add(cardPanel);
        cardLayout.show(cardPanel, "Login");
    }

    private void createAccount(String username, String password) {
        userDAO.createAccount(username, password);
        JOptionPane.showMessageDialog(this, "Account created successfully.");
    }

    private void login(String username, String password) {
        User user = userDAO.login(username, password);
        if (user != null) {
            loggedInUser = user;
            cardLayout.show(cardPanel, "Main");
        } else {
            JOptionPane.showMessageDialog(this, "Login failed.");
        }
    }

    private void logout() {
        loggedInUser = null;
        cardLayout.show(cardPanel, "Login");
    }

    private void addProperty() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField locationField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField bedroomsField = new JTextField();
        JTextField bathroomsField = new JTextField();
        JTextField squareFeetField = new JTextField();

        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Bedrooms:"));
        panel.add(bedroomsField);
        panel.add(new JLabel("Bathrooms:"));
        panel.add(bathroomsField);
        panel.add(new JLabel("Square Feet:"));
        panel.add(squareFeetField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Property",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Property property = new Property();
            // Assuming a constructor or use setters here
            property.setLocation(locationField.getText());
            property.setPrice(Float.parseFloat(priceField.getText()));
            property.setBedrooms(Integer.parseInt(bedroomsField.getText()));
            property.setBathrooms(Integer.parseInt(bathroomsField.getText()));
            property.setSquareFeet(Integer.parseInt(squareFeetField.getText()));
            property.setUserId(loggedInUser.getId()); // Assuming you have the loggedInUser's ID available

            propertyDAO.addProperty(property);
            JOptionPane.showMessageDialog(this, "Property added successfully.");
        } else {
            System.out.println("Cancelled");
        }
    }
    private void searchProperties() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField locationField = new JTextField();

        panel.add(new JLabel("Location:"));
        panel.add(locationField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Search Properties",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            List<Property> properties = propertyDAO.searchProperties(locationField.getText(), 0, Float.MAX_VALUE, 0, 0, 0);
            StringBuilder propertiesList = new StringBuilder();
            for (Property property : properties) {
                propertiesList.append(property.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(20, 40);
            textArea.setText(propertiesList.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Search cancelled");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
