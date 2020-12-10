package view;

import controller.BookMessage;
import controller.CloseMessage;
import controller.Message;
import controller.SubmitMessage;
import model.BookModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class BookView extends JFrame {
    BookModel model;
    JFrame bookFrame;
    BlockingQueue<Message> queue;

    public BookView() {// int n1, int n2, BookModel model
        bookFrame = new JFrame();
        this.setTitle("Booking");

        // 2 label and text for this days and beds
        add(new JLabel("Days: "));
        JTextField daysField = new JTextField(10);
        daysField.setText("");
        add(daysField);

        add(new JLabel("Beds: "));
        JTextField bedsField = new JTextField(10);
        bedsField.setText("");
        add(bedsField);
        //2 buttons
        JButton submit = new JButton("Submit");
        add(submit);
        JButton back = new JButton("Go Back");
        add(back);
        //basic setup
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(event -> {
            int days = Integer.parseInt(daysField.getText());
            int beds = Integer.parseInt(bedsField.getText());
//            model.setValues(number1, number2, model); // "setValue" depend what's called in model
            model.setBeds(beds);
            model.setDays(days);
        });

        submit.addActionListener(event -> {
            try {
                this.queue.put(new SubmitMessage()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        back.addActionListener(event -> {
            this.dispose();

//            try {
//                this.queue.put(new CloseMessage()); // add Reservation message to queue
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
    }
}