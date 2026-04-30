package dao;

import db.DBConnection;
import model.Citizen;
import model.Zone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO {

    public List<Citizen> getAllCitizens() {
        List<Citizen> list = new ArrayList<>();
        String sql = "SELECT c.citizen_id, c.name, c.dob, c.gender, c.phone, c.email, c.zone_id, z.zone_name " +
                     "FROM Citizen c JOIN Zone z ON c.zone_id = z.zone_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Citizen c = new Citizen();
                c.setCitizenId(rs.getInt("citizen_id"));
                c.setName(rs.getString("name"));
                c.setDob(rs.getString("dob"));
                c.setGender(rs.getString("gender"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setZoneId(rs.getInt("zone_id"));
                c.setZoneName(rs.getString("zone_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addCitizen(Citizen c) {
        String sql = "INSERT INTO Citizen (citizen_id, name, dob, gender, phone, email, zone_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, c.getCitizenId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getDob());
            pstmt.setString(4, c.getGender());
            pstmt.setString(5, c.getPhone());
            pstmt.setString(6, c.getEmail());
            pstmt.setInt(7, c.getZoneId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCitizen(int id) {
        String sql = "DELETE FROM Citizen WHERE citizen_id = ?";
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

    public List<Zone> getAllZones() {
        List<Zone> zones = new ArrayList<>();
        String sql = "SELECT zone_id, zone_name FROM Zone";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                zones.add(new Zone(rs.getInt("zone_id"), rs.getString("zone_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zones;
    }
}
