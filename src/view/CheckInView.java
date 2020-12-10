package view;

import controller.ConfirmMessage;
import controller.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class CheckInView extends JFrame{
    BlockingQueue<Message> queue;
    JFrame checkInView;

    public CheckInView(BlockingQueue<Message> queue) {
        this.queue = queue;
        checkInView = new JFrame();
        this.setTitle("Check In");

        JButton confirm  = new JButton("Confirm");
        add(confirm);
        JButton goBack = new JButton ("Go Back");
        add(goBack);

        setVisible(true);
        setSize(200, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        confirm.addActionListener(event -> {
            try {
                this.queue.put(new ConfirmMessage()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.dispose();
        });

        goBack.addActionListener(event -> {
            this.dispose();
        });
    }
}
