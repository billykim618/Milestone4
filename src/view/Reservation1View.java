package view;

import controller.CancelMessage;
import controller.CheckInMessage;
import controller.CheckOutMessage;
import controller.Message;
import model.Reservation1Model;
import model.ReservationModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Reservation1View extends JFrame {
    Reservation1Model res1;
    JFrame res1View;
    BlockingQueue<Message> queue;

    public Reservation1View(BlockingQueue<Message> queue, Reservation1Model res1) {
        this.res1 = res1;
        this.queue = queue;
        res1View = new JFrame();
        this.setTitle("Reservation 1");

//        JLabel daysLabel = new JLabel(String.valueOf(res1.getDays()));
//        add(daysLabel);

        // 4 buttons
        JButton checkIn  = new JButton("Check In");
        add(checkIn);
        JButton checkOut = new JButton ("Check Out");
        add(checkOut);
        JButton cancel = new JButton ("Cancel");
        add(cancel);
        JButton goBack = new JButton("Go Back");
        add(goBack);
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

        checkOut.addActionListener(event -> {
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
            this.dispose();
        });

        goBack.addActionListener(event -> {
            this.dispose();
        });

    }

    public void setBeds(int beds) {
    }
}
