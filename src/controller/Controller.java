package controller;

import model.HotelModel;
import view.HotelView;
import view.RoomView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private BlockingQueue<Message> queue;

    private HotelView hotelView;
    private RoomView roomView;
    private HotelModel hotelModel;

    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(HotelView hotelView, HotelModel hotelModel, BlockingQueue<Message> queue) {
        this.hotelView = hotelView;
        this.hotelModel = hotelModel;
        this.queue = queue;

        valves.add(new BookMessageValve());
        valves.add(new CheckInMessageValve());
    }

    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        while (response != ValveResponse.FINISH) {
            Message message = null;
            try {
                message = queue.take(); // Take next message from queue when ready/available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Valve valve : valves) {
                response = valve.execute(message);
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    private class BookMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != BookMessage.class) {
                return ValveResponse.MISS;
            }

            // Otherwise message is of BookMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }

    private class CheckInMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CheckInMessage.class) {
                return ValveResponse.MISS;
            }
            // Otherwise message is of CheckInMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }

    private class CheckOutMessageValve implements Valve {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }
}
