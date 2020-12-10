package view;

import controller.Message;
import controller.Reservation1Message;
import controller.Reservation2Message;
import model.ReservationModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class ReservationsView extends JFrame {
    ReservationModel model;
    BlockingQueue<Message> queue;
    JFrame reservationsFrame;

    public ReservationsView() {
        reservationsFrame = new JFrame();
        this.setTitle("Your Reservations");

        // 3 buttons for this view
        JButton reservation_1Button  = new JButton("Reservation 1");
        add(reservation_1Button);
        JButton reservation_2Button = new JButton ("Reservation 2");
        add(reservation_2Button);
        JButton goBackButton = new JButton("Go Back");
        add(goBackButton);
        //basic set up
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        reservation_1Button.addActionListener(event -> {
            try {
                this.queue.put(new Reservation1Message()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        reservation_2Button.addActionListener(event -> {
            try {
                this.queue.put(new Reservation2Message()); // add Reservation message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        goBackButton.addActionListener(event -> {
            this.dispose();
        });
    }
}
