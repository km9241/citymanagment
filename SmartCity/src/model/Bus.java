package model;

public class Bus {
    private int busId;
    private String route;

    public Bus(int busId, String route) {
        this.busId = busId;
        this.route = route;
    }

    public int getBusId() { return busId; }
    public String getRoute() { return route; }

    @Override
    public String toString() {
        return "Bus " + busId + " (" + route + ")";
    }
}
