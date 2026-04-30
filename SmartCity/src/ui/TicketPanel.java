package ui;

import dao.CitizenDAO;
import dao.TicketDAO;
import model.Bus;
import model.Citizen;
import model.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TicketPanel extends JPanel {
    private final TicketDAO dao;
    private final CitizenDAO citizenDAO;
    private JTable table;
    private DefaultTableModel tableModel;

    private JComboBox<Citizen> citizenBox;
    private JComboBox<Bus> busBox;
    private JTextField fareField, dateField;

    public TicketPanel() {
        dao = new TicketDAO();
        citizenDAO = new CitizenDAO();
        setLayout(new BorderLayout(10, 10));

        // 1. TOP SECTION - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Citizen Name", "Bus", "Fare", "Travel Date"}, 0) {
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

        inputPanel.add(new JLabel("Bus:"));
        busBox = new JComboBox<>();
        inputPanel.add(busBox);

        inputPanel.add(new JLabel("Fare (Amount):"));
        fareField = new JTextField();
        inputPanel.add(fareField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton bookButton = new JButton("Book Ticket");
        buttonPanel.add(bookButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.SOUTH);

        // Load data
        refreshTable();
        loadDropdowns();

        // Listeners
        bookButton.addActionListener(e -> bookTicket());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Ticket> tickets = dao.getAllTickets();
        for (Ticket t : tickets) {
            tableModel.addRow(new Object[]{
                t.getTicketId(),
                t.getCitizenName(),
                t.getBusId(),
                t.getFare(),
                t.getTravelDate()
            });
        }
    }

    private void loadDropdowns() {
        citizenBox.removeAllItems();
        List<Citizen> citizens = citizenDAO.getAllCitizens();
        for (Citizen c : citizens) {
            citizenBox.addItem(c);
        }

        busBox.removeAllItems();
        List<Bus> buses = dao.getAllBuses();
        for (Bus b : buses) {
            busBox.addItem(b);
        }
    }

    private void bookTicket() {
        try {
            Citizen citizen = (Citizen) citizenBox.getSelectedItem();
            Bus bus = (Bus) busBox.getSelectedItem();
            double fare = Double.parseDouble(fareField.getText().trim());
            String date = dateField.getText().trim();

            if (citizen == null || bus == null || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Citizen, Bus and Date are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = (int)(System.currentTimeMillis() % 100000);
            Ticket t = new Ticket(newId, citizen.getCitizenId(), bus.getBusId(), fare, date);
            boolean success = dao.bookTicket(t);

            if (success) {
                JOptionPane.showMessageDialog(this, "Ticket booked successfully!");
                refreshTable();
                fareField.setText("");
                dateField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book ticket.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for Fare.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
