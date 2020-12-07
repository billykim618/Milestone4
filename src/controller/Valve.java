package controller;

interface Valve {
    // Performs certain action in response to message
    public ValveResponse execute(Message message);
}
