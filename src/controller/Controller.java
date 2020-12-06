package controller;

import model.Hotel;
import view.HotelView;
import view.RoomView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private BlockingQueue<Message> queue;

    private HotelView hotelView;
    private RoomView roomView;
    private Hotel hotel;

    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(HotelView hotelView, Hotel hotel, BlockingQueue<Message> queue) {
        this.hotelView = hotelView;
        this.hotel = hotel;
        this.queue = queue;
    }

    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
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
}
