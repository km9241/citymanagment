package model;

public class Payment {
    private int paymentId;
    private int citizenId;
    private String citizenName;
    private String serviceType;
    private double amount;
    private String paymentDate;

    public Payment() {}

    public Payment(int paymentId, int citizenId, String serviceType, double amount, String paymentDate) {
        this.paymentId = paymentId;
        this.citizenId = citizenId;
        this.serviceType = serviceType;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getCitizenId() { return citizenId; }
    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getCitizenName() { return citizenName; }
    public void setCitizenName(String citizenName) { this.citizenName = citizenName; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return "Payment [ID=" + paymentId + ", Service=" + serviceType + ", Amount=" + amount + "]";
    }
}
