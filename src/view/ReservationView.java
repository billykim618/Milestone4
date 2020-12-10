package view;

import controller.Message;
import controller.Reservation1Message;
import controller.Reservation2Message;
import model.ReservationModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class ReservationView extends JFrame {
    ReservationModel model;
    BlockingQueue<Message> queue;

    public ReservationView() {
        // 3 buttons for this view
        JButton Reservation_1  = new JButton("Reservation 1");
        add(Reservation_1);
        JButton Reservation_2 = new JButton ("Reservation 2");
        add(Reservation_2);
        JButton goBack = new JButton("Go Back");
        add(goBack);
        //basic set up
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Reservation_1.addActionListener(event -> {
            try {
                this.queue.put(new Reservation1Message()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Reservation_2.addActionListener(event -> {
            try {
                this.queue.put(new Reservation2Message()); // add Reservation message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
