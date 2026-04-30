package ui;

import dao.CitizenDAO;
import model.Citizen;
import model.Zone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CitizenPanel extends JPanel {
    private final CitizenDAO dao;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField nameField, dobField, genderField, phoneField, emailField;
    private JComboBox<Zone> zoneBox;

    public CitizenPanel() {
        dao = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Phone", "Email", "Zone"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 2. BOTTOM SECTION - Form
        JPanel formPanel = new JPanel(new BorderLayout(5, 5));
        
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("DOB (YYYY-MM-DD):"));
        dobField = new JTextField();
        inputPanel.add(dobField);

        inputPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Zone:"));
        zoneBox = new JComboBox<>();
        inputPanel.add(zoneBox);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Citizen");
        JButton deleteButton = new JButton("Delete Selected");
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.SOUTH);

        // Load initial data
        refreshTable();
        loadZones();

        // Listeners
        addButton.addActionListener(e -> addCitizen());
        deleteButton.addActionListener(e -> deleteCitizen());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Citizen> citizens = dao.getAllCitizens();
        for (Citizen c : citizens) {
            tableModel.addRow(new Object[]{
                c.getCitizenId(),
                c.getName(),
                c.getPhone(),
                c.getEmail(),
                c.getZoneName()
            });
        }
    }

    private void loadZones() {
        zoneBox.removeAllItems();
        List<Zone> zones = dao.getAllZones();
        for (Zone z : zones) {
            zoneBox.addItem(z);
        }
    }

    private void addCitizen() {
        try {
            String name = nameField.getText().trim();
            String dob = dobField.getText().trim();
            String gender = genderField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            Zone selectedZone = (Zone) zoneBox.getSelectedItem();

            if (name.isEmpty() || selectedZone == null) {
                JOptionPane.showMessageDialog(this, "Name and Zone are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            Citizen c = new Citizen(newId, name, dob, gender, phone, email, selectedZone.getZoneId());
            boolean success = dao.addCitizen(c);

            if (success) {
                JOptionPane.showMessageDialog(this, "Citizen added successfully!");
                refreshTable();
                
                nameField.setText("");
                dobField.setText("");
                genderField.setText("");
                phoneField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add citizen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCitizen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int citizenId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this citizen?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.deleteCitizen(citizenId)) {
                    JOptionPane.showMessageDialog(this, "Citizen deleted successfully!");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete citizen (might be referenced).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a citizen to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
