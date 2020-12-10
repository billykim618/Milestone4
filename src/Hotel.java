import controller.Controller;
import controller.Message;
import model.HotelModel;
import view.HotelView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hotel {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static HotelView view;
    private static HotelModel hotelModel;

    public static void main(String[] args) {
        view = HotelView.init(queue);
        hotelModel = new HotelModel();
        Controller controller = new Controller(view, hotelModel, queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}
