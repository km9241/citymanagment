package dao;

import db.DBConnection;
import model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT cp.complaint_id, cp.citizen_id, c.name AS citizen_name, cp.type, cp.description, cp.status, cp.zone_id, z.zone_name " +
                     "FROM Complaint cp JOIN Citizen c ON cp.citizen_id = c.citizen_id " +
                     "JOIN Zone z ON cp.zone_id = z.zone_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Complaint cp = new Complaint();
                cp.setComplaintId(rs.getInt("complaint_id"));
                cp.setCitizenId(rs.getInt("citizen_id"));
                cp.setCitizenName(rs.getString("citizen_name"));
                cp.setComplaintType(rs.getString("type"));
                cp.setDescription(rs.getString("description"));
                cp.setStatus(rs.getString("status"));
                cp.setZoneId(rs.getInt("zone_id"));
                cp.setZoneName(rs.getString("zone_name"));
                list.add(cp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addComplaint(Complaint c) {
        String sql = "INSERT INTO Complaint (complaint_id, citizen_id, complaint_type, description, status, zone_id) VALUES (?, ?, ?, ?, 'Pending', ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, c.getComplaintId());
            pstmt.setInt(2, c.getCitizenId());
            pstmt.setString(3, c.getComplaintType());
            pstmt.setString(4, c.getDescription());
            pstmt.setInt(5, c.getZoneId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resolveComplaint(int id) {
        String sql = "UPDATE Complaint SET status = 'Resolved' WHERE complaint_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
