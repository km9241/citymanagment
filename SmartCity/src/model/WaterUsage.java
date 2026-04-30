package model;

public class WaterUsage {
    private int usageId;
    private int citizenId;
    private String citizenName;
    private int litersUsed;
    private double billAmount;
    private String month;

    public WaterUsage() {}

    public WaterUsage(int usageId, int citizenId, int litersUsed, double billAmount, String month) {
        this.usageId = usageId;
        this.citizenId = citizenId;
        this.litersUsed = litersUsed;
        this.billAmount = billAmount;
        this.month = month;
    }

    public int getUsageId() { return usageId; }
    public void setUsageId(int usageId) { this.usageId = usageId; }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getCitizenName() { return citizenName; }
    public void setCitizenName(String citizenName) { this.citizenName = citizenName; }

    public int getLitersUsed() { return litersUsed; }
    public void setLitersUsed(int litersUsed) { this.litersUsed = litersUsed; }

    public double getBillAmount() { return billAmount; }
    public void setBillAmount(double billAmount) { this.billAmount = billAmount; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    @Override
    public String toString() {
        return "WaterUsage [ID=" + usageId + ", Month=" + month + "]";
    }
}
