package model;

import javax.swing.*;

public class Hotel {

    JButton book = new JButton("Book");
    JButton reservations = new JButton("Reservations");

    book.addActionListener(event -> {
        try {
            this.queue.put(new NewGameMessage()); // <-- adding NewGame message to the queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
}
