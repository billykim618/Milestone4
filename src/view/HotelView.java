package view;

import model.Hotel;

import javax.swing.*;
import java.awt.*;

public class HotelView extends JFrame {
    public HotelView() {
        JButton book = new JButton("BOOK");
        JButton reservations = new JButton("RESERVATIONS");
        add(book);
        setVisible(true);
        setSize(500,500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
