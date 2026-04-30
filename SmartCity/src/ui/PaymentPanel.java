package ui;

import dao.CitizenDAO;
import dao.PaymentDAO;
import model.Citizen;
import model.Payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentPanel extends JPanel {
    private final PaymentDAO dao;
    private final CitizenDAO citizenDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    private JComboBox<Citizen> citizenBox;
    private JTextField typeField, amountField, dateField;

    public PaymentPanel() {
        dao = new PaymentDAO();
        citizenDAO = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Citizen Name", "Service Type", "Amount", "Date"}, 0) {
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

        inputPanel.add(new JLabel("Service Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Payment");
        buttonPanel.add(addButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.SOUTH);

        // Load data
        refreshTable();
        loadDropdowns();

        // Listeners
        addButton.addActionListener(e -> addPayment());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Payment> payments = dao.getAllPayments();
        for (Payment p : payments) {
            tableModel.addRow(new Object[]{
                p.getPaymentId(),
                p.getCitizenName(),
                p.getServiceType(),
                p.getAmount(),
                p.getPaymentDate()
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

    private void addPayment() {
        try {
            Citizen citizen = (Citizen) citizenBox.getSelectedItem();
            String serviceType = typeField.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());
            String date = dateField.getText().trim();

            if (citizen == null || serviceType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Citizen and Service Type are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            Payment p = new Payment(newId, citizen.getCitizenId(), serviceType, amount, date);
            boolean success = dao.addPayment(p);

            if (success) {
                JOptionPane.showMessageDialog(this, "Payment added successfully!");
                refreshTable();
                typeField.setText("");
                amountField.setText("");
                dateField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add payment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
