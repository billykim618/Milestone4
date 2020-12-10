package controller;

public class SubmitReservation1Message implements Message {
    int days;
    int beds;
    private boolean checkedIn;
    private boolean checkedOut;
    private boolean canceled;
    private boolean reserved;

    public SubmitReservation1Message(int days, int beds) {
        this.days = days;
        this.beds = beds;
        this.checkedIn = false;
        this.checkedOut = false;
        this.canceled = false;
        this.reserved = true;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public void checkIn() { this.checkedIn = true; }

    public void checkOut() { if (checkedIn) this.checkedOut = true; }

    public void cancel() { this.reserved = false; }

    public boolean isReserved() { return this.reserved; }
}
