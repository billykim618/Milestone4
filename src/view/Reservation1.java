package view;

import controller.CancelMessage;
import controller.CheckInMessage;
import controller.CheckOutMessage;
import controller.Message;
import model.ReservationModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Reservation1 extends JFrame {
    ReservationModel model;
    JFrame res1view;
    BlockingQueue<Message> queue;

    public Reservation1 () {
        // 3 buttons
        JButton checkIn  = new JButton("Check In");
        add(checkIn);
        JButton checkOut = new JButton ("Check Out");
        add(checkOut);
        JButton cancel = new JButton ("Cancel");
        add(cancel);
        //basic setup
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        checkIn.addActionListener(event -> {
            try {
                this.queue.put(new CheckInMessage()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        checkIn.addActionListener(event -> {
            try {
                this.queue.put(new CheckOutMessage()); // add Reservation message to queue
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        cancel.addActionListener(event -> {
            try {
                this.queue.put(new CancelMessage()); // add Reservation message to queue
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
