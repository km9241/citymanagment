package dao;

import db.DBConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT p.payment_id, c.name, p.service_type, p.amount, p.payment_date \n" +
                     "FROM Payment p JOIN Citizen c ON p.citizen_id = c.citizen_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentId(rs.getInt("payment_id"));
                // citizen_id omitted
                p.setCitizenName(rs.getString("name"));
                p.setServiceType(rs.getString("service_type"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentDate(rs.getString("payment_date"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addPayment(Payment p) {
        if (p.getAmount() <= 0) {
            return false; // Reject if amount <= 0
        }
        String sql = "INSERT INTO Payment (payment_id, citizen_id, service_type, amount, payment_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, p.getPaymentId());
            pstmt.setInt(2, p.getCitizenId());
            pstmt.setString(3, p.getServiceType());
            pstmt.setDouble(4, p.getAmount());
            pstmt.setString(5, p.getPaymentDate());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
