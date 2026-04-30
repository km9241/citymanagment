package dao;

import db.DBConnection;
import model.Bus;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT t.ticket_id, t.citizen_id, c.name AS citizen_name, t.bus_id, t.fare, t.travel_date " +
                     "FROM Ticket t JOIN Citizen c ON t.citizen_id = c.citizen_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setTicketId(rs.getInt("ticket_id"));
                t.setCitizenId(rs.getInt("citizen_id"));
                t.setCitizenName(rs.getString("citizen_name"));
                t.setBusId(rs.getInt("bus_id"));
                t.setFare(rs.getDouble("fare"));
                t.setTravelDate(rs.getString("travel_date"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean bookTicket(Ticket t) {
        String sql = "INSERT INTO Ticket (ticket_id, citizen_id, bus_id, fare, travel_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, t.getTicketId());
            pstmt.setInt(2, t.getCitizenId());
            pstmt.setInt(3, t.getBusId());
            pstmt.setDouble(4, t.getFare());
            pstmt.setString(5, t.getTravelDate());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT bus_id, capacity, route_id FROM Bus";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                buses.add(new Bus(rs.getInt("bus_id"), rs.getString("route_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }
}
