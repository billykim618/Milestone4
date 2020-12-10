package model;

import javax.swing.*;
import java.util.ArrayList;

public class ReservationModel extends JFrame {
    ArrayList<ReservationModel> reservationModels = new ArrayList<>();
    private int numReservations;
    private int days;
    private int numBeds;
    private String bedSize;
    private boolean isCheckedIn;
    private boolean isCheckedOut;
    private boolean isReserved = false;


    public ReservationModel(RoomModel roomModel) {
        this.days = roomModel.getDays();
        this.numBeds = roomModel.getNumBeds();
        this.bedSize = roomModel.getBedSize();
        this.isCheckedIn = false;
        this.isCheckedOut = false;
        reservationModels.add(this);
        numReservations++;
    }

    public void cancel() {
        reservationModels.remove(this);
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
