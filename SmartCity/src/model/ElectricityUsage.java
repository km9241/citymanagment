package model;

public class ElectricityUsage {
    private int usageId;
    private int citizenId;
    private String citizenName;
    private int unitsConsumed;
    private double billAmount;
    private String month;

    public ElectricityUsage() {}

    public ElectricityUsage(int usageId, int citizenId, int unitsConsumed, double billAmount, String month) {
        this.usageId = usageId;
        this.citizenId = citizenId;
        this.unitsConsumed = unitsConsumed;
        this.billAmount = billAmount;
        this.month = month;
    }

    public int getUsageId() { return usageId; }
    public void setUsageId(int usageId) { this.usageId = usageId; }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getCitizenName() { return citizenName; }
    public void setCitizenName(String citizenName) { this.citizenName = citizenName; }

    public int getUnitsConsumed() { return unitsConsumed; }
    public void setUnitsConsumed(int unitsConsumed) { this.unitsConsumed = unitsConsumed; }

    public double getBillAmount() { return billAmount; }
    public void setBillAmount(double billAmount) { this.billAmount = billAmount; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    @Override
    public String toString() {
        return "ElectricityUsage [ID=" + usageId + ", Month=" + month + "]";
    }
}
