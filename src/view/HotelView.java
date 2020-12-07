package view;

import controller.BookMessage;
import controller.Message;
import controller.ReservationsMessage;
import model.Hotel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class HotelView extends JFrame {
    private JFrame hotelFrame;
    private BlockingQueue<Message> queue;

    public static HotelView init(BlockingQueue<Message> queue) {
        // Create object of type view
        return new HotelView(queue);
    }

    private HotelView(BlockingQueue<Message> queue) {
        this.queue = queue;
        // TO DO:
        // you should initialize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it
        hotelFrame = new JFrame();

        JButton book = new JButton("BOOK");
        JButton reservations = new JButton("RESERVATIONS");

        book.addActionListener(event -> {
            try {
                this.queue.put(new BookMessage()); // add Book message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        reservations.addActionListener(event -> {
            try {
                this.queue.put(new ReservationsMessage()); // add Reservation message to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        hotelFrame.add(book);
        hotelFrame.add(reservations);
        hotelFrame.pack();
        setSize(500,500);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
