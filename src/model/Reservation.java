package model;

import javax.swing.*;
import java.util.ArrayList;

public class Reservation extends JFrame {
    ArrayList<Reservation> reservations = new ArrayList<>();
    private int numReservations;
    private int days;
    private int numBeds;
    private String bedSize;
    private boolean isCheckedIn;
    private boolean isCheckedOut;


    public Reservation(Room room) {
        this.days = room.getDays();
        this.numBeds = room.getNumBeds();
        this.bedSize = room.getBedSize();
        this.isCheckedIn = false;
        this.isCheckedOut = false;
        reservations.add(this);
        numReservations++;
    }

    public void cancel() {
        reservations.remove(this);
        numReservations--;
    }

    public void checkIn() {
        this.isCheckedIn = true;
    }

    public void checkOut() {
        if (isCheckedIn)
            this.isCheckedOut = true;
    }

    public int getReservation() {
        return numReservations;
    }
}
