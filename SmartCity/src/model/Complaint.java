package model;

public class Complaint {
    private int complaintId;
    private int citizenId;
    private String citizenName;
    private String complaintType;
    private String description;
    private String status;
    private int zoneId;
    private String zoneName;

    public Complaint() {}

    public Complaint(int complaintId, int citizenId, String complaintType, String description, String status, int zoneId) {
        this.complaintId = complaintId;
        this.citizenId = citizenId;
        this.complaintType = complaintType;
        this.description = description;
        this.status = status;
        this.zoneId = zoneId;
    }

    public int getComplaintId() { return complaintId; }
    public void setComplaintId(int complaintId) { this.complaintId = complaintId; }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getCitizenName() { return citizenName; }
    public void setCitizenName(String citizenName) { this.citizenName = citizenName; }

    public String getComplaintType() { return complaintType; }
    public void setComplaintType(String complaintType) { this.complaintType = complaintType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getZoneId() { return zoneId; }
    public void setZoneId(int zoneId) { this.zoneId = zoneId; }

    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }

    @Override
    public String toString() {
        return "Complaint [ID=" + complaintId + ", Type=" + complaintType + ", Status=" + status + "]";
    }
}
