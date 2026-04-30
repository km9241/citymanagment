package dao;

import db.DBConnection;
import model.ElectricityUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElectricityDAO {

    public List<ElectricityUsage> getAllUsage() {
        List<ElectricityUsage> list = new ArrayList<>();
        String sql = "SELECT e.usage_id, e.citizen_id, c.name AS citizen_name, e.units_consumed, e.bill_amount, e.month " +
                     "FROM Electricity_Usage e JOIN Citizen c ON e.citizen_id = c.citizen_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ElectricityUsage e = new ElectricityUsage();
                e.setUsageId(rs.getInt("usage_id"));
                e.setCitizenId(rs.getInt("citizen_id"));
                e.setCitizenName(rs.getString("citizen_name"));
                e.setUnitsConsumed(rs.getInt("units_consumed"));
                e.setBillAmount(rs.getDouble("bill_amount"));
                e.setMonth(rs.getString("month"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean addUsage(ElectricityUsage e) {
        String sql = "INSERT INTO Electricity_Usage (usage_id, citizen_id, units_consumed, bill_amount, month) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, e.getUsageId());
            pstmt.setInt(2, e.getCitizenId());
            pstmt.setInt(3, e.getUnitsConsumed());
            pstmt.setDouble(4, e.getBillAmount());
            pstmt.setString(5, e.getMonth());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
