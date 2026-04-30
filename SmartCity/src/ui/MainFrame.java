package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Smart City Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Styling tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(11, 60, 93));
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Add Panels
        tabbedPane.addTab("Citizens", new CitizenPanel());
        tabbedPane.addTab("Complaints", new ComplaintPanel());
        tabbedPane.addTab("Payments", new PaymentPanel());
        tabbedPane.addTab("Electricity", new ElectricityPanel());
        tabbedPane.addTab("Water Usage", new WaterPanel());
        tabbedPane.addTab("Tickets", new TicketPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
