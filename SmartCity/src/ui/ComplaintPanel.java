package ui;

import dao.CitizenDAO;
import dao.ComplaintDAO;
import model.Citizen;
import model.Complaint;
import model.Zone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ComplaintPanel extends JPanel {
    private final ComplaintDAO dao;
    private final CitizenDAO citizenDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    private JComboBox<Citizen> citizenBox;
    private JTextField typeField, descField;
    private JComboBox<Zone> zoneBox;

    public ComplaintPanel() {
        dao = new ComplaintDAO();
        citizenDAO = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Citizen Name", "Type", "Description", "Status", "Zone"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 2. BOTTOM SECTION - Form
        JPanel formPanel = new JPanel(new BorderLayout(5, 5));
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Citizen:"));
        citizenBox = new JComboBox<>();
        inputPanel.add(citizenBox);

        inputPanel.add(new JLabel("Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Description:"));
        descField = new JTextField();
        inputPanel.add(descField);

        inputPanel.add(new JLabel("Zone:"));
        zoneBox = new JComboBox<>();
        inputPanel.add(zoneBox);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton registerButton = new JButton("Register Complaint");
        JButton resolveButton = new JButton("Mark Resolved");
        
        buttonPanel.add(registerButton);
        buttonPanel.add(resolveButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.SOUTH);

        // Load data
        refreshTable();
        loadDropdowns();

        // Listeners
        registerButton.addActionListener(e -> registerComplaint());
        resolveButton.addActionListener(e -> resolveComplaint());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Complaint> complaints = dao.getAllComplaints();
        for (Complaint cp : complaints) {
            tableModel.addRow(new Object[]{
                cp.getComplaintId(),
                cp.getCitizenName(),
                cp.getComplaintType(),
                cp.getDescription(),
                cp.getStatus(),
                cp.getZoneName()
            });
        }
    }

    private void loadDropdowns() {
        citizenBox.removeAllItems();
        List<Citizen> citizens = citizenDAO.getAllCitizens();
        for (Citizen c : citizens) {
            citizenBox.addItem(c);
        }

        zoneBox.removeAllItems();
        List<Zone> zones = citizenDAO.getAllZones();
        for (Zone z : zones) {
            zoneBox.addItem(z);
        }
    }

    private void registerComplaint() {
        try {
            Citizen citizen = (Citizen) citizenBox.getSelectedItem();
            String type = typeField.getText().trim();
            String description = descField.getText().trim();
            Zone zone = (Zone) zoneBox.getSelectedItem();

            if (citizen == null || type.isEmpty() || zone == null) {
                JOptionPane.showMessageDialog(this, "Citizen, Type and Zone are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            Complaint c = new Complaint(newId, citizen.getCitizenId(), type, description, "Pending", zone.getZoneId());
            boolean success = dao.addComplaint(c);

            if (success) {
                JOptionPane.showMessageDialog(this, "Complaint registered successfully!");
                refreshTable();
                typeField.setText("");
                descField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register complaint.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resolveComplaint() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int complaintId = (int) tableModel.getValueAt(selectedRow, 0);
            String status = (String) tableModel.getValueAt(selectedRow, 4);
            
            if ("Resolved".equals(status)) {
                JOptionPane.showMessageDialog(this, "Complaint is already resolved.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (dao.resolveComplaint(complaintId)) {
                JOptionPane.showMessageDialog(this, "Complaint marked as resolved!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to resolve complaint.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a complaint.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
