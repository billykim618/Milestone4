import controller.Controller;
import controller.Message;
import model.HotelModel;
import model.Reservation1Model;
import model.Reservation2Model;
import view.HotelView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hotel {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static HotelView view;
    private static HotelModel hotelModel;
    private static Reservation1Model res1;
    private static Reservation2Model res2;

    public static void main(String[] args) {
        view = HotelView.init(queue);
        hotelModel = new HotelModel();
        res1 = new Reservation1Model();
        res2 = new Reservation2Model();

        Controller controller = new Controller(view, hotelModel, queue, res1, res2);

        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}
