import controller.Controller;
import controller.Message;
import model.Hotel;
import view.HotelView;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Flow;
import java.util.concurrent.LinkedBlockingQueue;

public class HotelTest {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static HotelView view;
    private static Hotel hotel;
//
    public static void main(String[] args) {
        view = HotelView.init(queue);
        hotel = new Hotel();
        Controller controller = new Controller(view, hotel, queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
        JFrame frame = new JFrame();
//
//        JButton helloButton = new JButton("Hello");
//        JButton goodbyeButton = new JButton("Goodbye");
//
//        final int FIELD_WIDTH = 20;
//        JTextField textField = new JTextField(FIELD_WIDTH);
//        textField.setText("Click a button");

        frame.setLayout(new FlowLayout());
//
//        frame.add(helloButton);
//        frame.add(goodbyeButton);
//        frame.add(textField);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
