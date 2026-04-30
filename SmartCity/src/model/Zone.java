package model;

public class Zone {
    private int zoneId;
    private String zoneName;

    public Zone(int zoneId, String zoneName) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
    }

    public int getZoneId() { return zoneId; }
    public String getZoneName() { return zoneName; }

    @Override
    public String toString() {
        return zoneName;
    }
}
