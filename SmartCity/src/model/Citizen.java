package model;

public class Citizen {
    private int citizenId;
    private String name;
    private String dob;
    private String gender;
    private String phone;
    private String email;
    private int zoneId;
    private String zoneName; // useful for display

    public Citizen() {}

    public Citizen(int citizenId, String name, String dob, String gender, String phone, String email, int zoneId) {
        this.citizenId = citizenId;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.zoneId = zoneId;
    }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getZoneId() { return zoneId; }
    public void setZoneId(int zoneId) { this.zoneId = zoneId; }

    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }

    @Override
    public String toString() {
        return name; // Useful for JComboBox displaying Citizen objects
    }
}
