package dao;

import db.DBConnection;
import model.WaterUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaterDAO {

    public List<WaterUsage> getAllUsage() {
        List<WaterUsage> list = new ArrayList<>();
        String sql = "SELECT w.usage_id, w.citizen_id, c.name AS citizen_name, w.liters_used, w.bill_amount, w.month " +
                     "FROM Water_Usage w JOIN Citizen c ON w.citizen_id = c.citizen_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                WaterUsage w = new WaterUsage();
                w.setUsageId(rs.getInt("usage_id"));
                w.setCitizenId(rs.getInt("citizen_id"));
                w.setCitizenName(rs.getString("citizen_name"));
                w.setLitersUsed(rs.getInt("liters_used"));
                w.setBillAmount(rs.getDouble("bill_amount"));
                w.setMonth(rs.getString("month"));
                list.add(w);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean addUsage(WaterUsage w) {
        String sql = "INSERT INTO Water_Usage (usage_id, citizen_id, liters_used, bill_amount, month) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, w.getUsageId());
            pstmt.setInt(2, w.getCitizenId());
            pstmt.setInt(3, w.getLitersUsed());
            pstmt.setDouble(4, w.getBillAmount());
            pstmt.setString(5, w.getMonth());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
