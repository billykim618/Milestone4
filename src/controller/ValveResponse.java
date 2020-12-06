package controller;

public enum ValveResponse {
    MISS, // This valve cannot process this message
    EXECUTED, // This valve processed this message
    FINISH // The program was closed
}
