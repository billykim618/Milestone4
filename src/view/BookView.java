package view;

import controller.Message;
import controller.SubmitReservation1Message;
import controller.SubmitReservation2Message;
import model.BookModel;
import model.Reservation1Model;
import model.Reservation2Model;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class BookView extends JFrame {
    Reservation1Model res1;
    Reservation2Model res2;
    JFrame bookFrame;
    BlockingQueue<Message> queue;

    public BookView(BlockingQueue<Message> queue, Reservation1Model res1, Reservation2Model res2) {// int n1, int n2, BookModel model
        this.res1 = res1;
        this.res2 = res2;
        this.queue = queue;
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

//        submit.addActionListener(event -> {
//            int days = Integer.parseInt(daysField.getText());
//            int beds = Integer.parseInt(bedsField.getText());
////            model.setValues(number1, number2, model); // "setValue" depend what's called in model
//            model1.setBeds(beds);
//            model1.setDays(days);
//        });

        submit.addActionListener(event -> {
            try {
                int days, beds;
                days = Integer.parseInt(daysField.getText());
                beds = Integer.parseInt(bedsField.getText());
                if (!res1.isReserved()) {
                    res1.setReserved(true);
                    res1.setDays(days);
                    res1.setBeds(beds);
//                if (daysField.getText().length() > 0) {
//                    days = Integer.parseInt(daysField.getText());
//                if (bedsField.getText().length() > 0)
//                    beds = Integer.parseInt(bedsField.getText());
                    this.queue.put(new SubmitReservation1Message(days, beds));
                }
                else if (!res2.isReserved()){
                    this.queue.put(new SubmitReservation2Message(days, beds));
                    res2.setReserved(true);
                    res2.setDays(days);
                    res2.setBeds(beds);
                }
                else {
                    JFrame error = new JFrame();
                    error.setTitle("ERROR");
                    JLabel label = new JLabel("NO MORE RESERVATIONS AVAILABLE");
                    error.add(label);
                    JButton okButton = new JButton("OK");
                    error.add(okButton);

                    error.setVisible(true);
                    error.setSize(500,200);
                    error.setLayout(new FlowLayout());
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    okButton.addActionListener(event1 -> {
                        error.dispose();
                    });
                }
//                this.queue.put(new SubmitReservation1Message(days, beds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (model1.isReserved())
                this.dispose();
        });

        back.addActionListener(event -> {
            this.dispose();
        });


    }
}