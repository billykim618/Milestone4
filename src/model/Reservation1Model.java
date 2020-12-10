package model;

public class Reservation1Model {
    int days;
    int beds;
    private boolean checkedIn;
    private boolean checkedOut;
    private boolean canceled;
    private boolean reserved;

    public Reservation1Model() {
        this.days = 0;
        this.beds = 0;
        this.checkedIn = false;
        this.checkedOut = false;
        this.canceled = false;
        this.reserved = false;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() { return this.days; }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBeds() { return this.beds; }

    public void checkIn() { this.checkedIn = true; }

    public void checkOut() { if (checkedIn) this.checkedOut = true; }

    public void cancel() { this.reserved = false; }

    public void setReserved(boolean reserved) { this.reserved = reserved; }

    public boolean isReserved() { return this.reserved; }
}
