package ui;

import dao.CitizenDAO;
import dao.WaterDAO;
import model.Citizen;
import model.WaterUsage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WaterPanel extends JPanel {
    private final WaterDAO dao;
    private final CitizenDAO citizenDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    private JComboBox<Citizen> citizenBox;
    private JTextField litersField, billField, monthField;

    public WaterPanel() {
        dao = new WaterDAO();
        citizenDAO = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Citizen Name", "Liters Used", "Bill Amount", "Month"}, 0) {
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

        inputPanel.add(new JLabel("Liters Used:"));
        litersField = new JTextField();
        inputPanel.add(litersField);

        inputPanel.add(new JLabel("Bill Amount:"));
        billField = new JTextField();
        inputPanel.add(billField);

        inputPanel.add(new JLabel("Month (YYYY-MM):"));
        monthField = new JTextField();
        inputPanel.add(monthField);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Record");
        buttonPanel.add(addButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.SOUTH);

        // Load data
        refreshTable();
        loadDropdowns();

        // Listeners
        addButton.addActionListener(e -> addRecord());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<WaterUsage> records = dao.getAllUsage();
        for (WaterUsage w : records) {
            tableModel.addRow(new Object[]{
                w.getUsageId(),
                w.getCitizenName(),
                w.getLitersUsed(),
                w.getBillAmount(),
                w.getMonth()
            });
        }
    }

    private void loadDropdowns() {
        citizenBox.removeAllItems();
        List<Citizen> citizens = citizenDAO.getAllCitizens();
        for (Citizen c : citizens) {
            citizenBox.addItem(c);
        }
    }

    private void addRecord() {
        try {
            Citizen citizen = (Citizen) citizenBox.getSelectedItem();
            int liters = Integer.parseInt(litersField.getText().trim());
            double bill = Double.parseDouble(billField.getText().trim());
            String month = monthField.getText().trim();

            if (citizen == null || month.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Citizen and Month are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            WaterUsage wu = new WaterUsage(newId, citizen.getCitizenId(), liters, bill, month);
            boolean success = dao.addUsage(wu);

            if (success) {
                JOptionPane.showMessageDialog(this, "Record added successfully!");
                refreshTable();
                litersField.setText("");
                billField.setText("");
                monthField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add record.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for Liters or Bill.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
