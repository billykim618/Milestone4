package view;

import controller.CancelMessage;
import controller.CheckInMessage;
import controller.CheckOutMessage;
import controller.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Reservation2View extends JFrame{
//    Model model;
    BlockingQueue<Message> queue;
    JFrame res2View;

    public Reservation2View(BlockingQueue<Message> queue) {
        this.queue = queue;
        res2View = new JFrame();
        this.setTitle("Reservation 2");

        // 4 buttons
        JButton checkIn = new JButton("Check In");
        add(checkIn);
        JButton checkOut = new JButton("Check Out");
        add(checkOut);
        JButton cancel = new JButton("Cancel");
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        cancel.addActionListener(event -> {
            try {
                this.queue.put(new CancelMessage()); // add Reservation message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        goBack.addActionListener(event -> {
            this.dispose();
        });
    }
}