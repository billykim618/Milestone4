package controller;

import model.HotelModel;
import model.Reservation1Model;
import model.Reservation2Model;
import view.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private BlockingQueue<Message> queue;

    private HotelView hotelView;
    private BookView bookView;
    private RoomView roomView;
    private ReservationsView reservationsView;
    private HotelModel hotelModel;
    private Reservation1View res1View;
    private Reservation2View res2View;
    private Reservation1Model res1;
    private Reservation2Model res2;
    private CheckInView checkInView;
    private CheckOutView checkOutView;
    private CancelView cancelView;


    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(HotelView hotelView, HotelModel hotelModel, BlockingQueue<Message> queue, Reservation1Model res1, Reservation2Model res2) {
        this.res1 = res1;
        this.res2 = res2;
        this.hotelView = hotelView;
        this.hotelModel = hotelModel;
        this.queue = queue;

        valves.add(new BookMessageValve());
        valves.add(new ReservationsMessageValve());
        valves.add(new SubmitReservation1MessageValve());
        valves.add(new Reservation1MessageValve());
        valves.add(new Reservation2MessageValve());
        valves.add(new CheckInMessageValve());
        valves.add(new CheckOutMessageValve());
        valves.add(new CancelMessageValve());
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
            bookView = new BookView(queue, res1, res2);
            // Otherwise message is of BookMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }

    private class ReservationsMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ReservationsMessage.class) {
                return ValveResponse.MISS;
            }
            reservationsView = new ReservationsView(queue);
            return ValveResponse.EXECUTED;
        }
    }

    private class SubmitReservation1MessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SubmitReservation1Message.class) {
                return ValveResponse.MISS;
            }
            if (!((SubmitReservation1Message) message).isReserved()) {
                res1 = new Reservation1Model();
            }
            return ValveResponse.EXECUTED;
        }
    }

    private class SubmitReservation2MessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SubmitReservation2Message.class) {
                return ValveResponse.MISS;
            }
            if (!((SubmitReservation2Message) message).isReserved()) {
                res1 = new Reservation1Model();
            }
            return ValveResponse.EXECUTED;
        }
    }

    private class SubmitMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SubmitReservation1Message.class) {
                return ValveResponse.MISS;
            }

            return ValveResponse.EXECUTED;
        }
    }

    private class Reservation1MessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {

            if (message.getClass() != Reservation1Message.class) {
                return ValveResponse.MISS;
            }
            res1View = new Reservation1View(queue, res1);
            return ValveResponse.EXECUTED;
        }
    }

    private class Reservation2MessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != Reservation2Message.class) {
                return ValveResponse.MISS;
            }
            res2View = new Reservation2View(queue, res2);
            return ValveResponse.EXECUTED;
        }
    }

    private class CheckInMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CheckInMessage.class) {
                return ValveResponse.MISS;
            }
            checkInView = new CheckInView(queue);
            // Otherwise message is of CheckInMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }

    private class CheckOutMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CheckOutMessage.class) {
                return ValveResponse.MISS;
            }
            checkOutView = new CheckOutView(queue);
            return ValveResponse.EXECUTED;
        }
    }

    private class CancelMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CancelMessage.class) {
                return ValveResponse.MISS;
            }
            cancelView = new CancelView(queue);
            return ValveResponse.EXECUTED;
        }
    }

}
