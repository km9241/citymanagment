package ui;

import dao.CitizenDAO;
import dao.ElectricityDAO;
import model.Citizen;
import model.ElectricityUsage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ElectricityPanel extends JPanel {
    private final ElectricityDAO dao;
    private final CitizenDAO citizenDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    private JComboBox<Citizen> citizenBox;
    private JTextField unitsField, billField, monthField;

    public ElectricityPanel() {
        dao = new ElectricityDAO();
        citizenDAO = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Citizen Name", "Units Consumed", "Bill Amount", "Month"}, 0) {
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

        inputPanel.add(new JLabel("Units Consumed:"));
        unitsField = new JTextField();
        inputPanel.add(unitsField);

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
        List<ElectricityUsage> records = dao.getAllUsage();
        for (ElectricityUsage e : records) {
            tableModel.addRow(new Object[]{
                e.getUsageId(),
                e.getCitizenName(),
                e.getUnitsConsumed(),
                e.getBillAmount(),
                e.getMonth()
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
            int units = Integer.parseInt(unitsField.getText().trim());
            double bill = Double.parseDouble(billField.getText().trim());
            String month = monthField.getText().trim();

            if (citizen == null || month.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Citizen and Month are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            ElectricityUsage eu = new ElectricityUsage(newId, citizen.getCitizenId(), units, bill, month);
            boolean success = dao.addUsage(eu);

            if (success) {
                JOptionPane.showMessageDialog(this, "Record added successfully!");
                refreshTable();
                unitsField.setText("");
                billField.setText("");
                monthField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add record.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for Units or Bill.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
