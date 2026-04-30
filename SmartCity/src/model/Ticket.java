package model;

public class Ticket {
    private int ticketId;
    private int citizenId;
    private String citizenName;
    private int busId;
    private double fare;
    private String travelDate;

    public Ticket() {}

    public Ticket(int ticketId, int citizenId, int busId, double fare, String travelDate) {
        this.ticketId = ticketId;
        this.citizenId = citizenId;
        this.busId = busId;
        this.fare = fare;
        this.travelDate = travelDate;
    }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getCitizenName() { return citizenName; }
    public void setCitizenName(String citizenName) { this.citizenName = citizenName; }

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }

    @Override
    public String toString() {
        return "Ticket [ID=" + ticketId + ", Bus=" + busId + ", Date=" + travelDate + "]";
    }
}
