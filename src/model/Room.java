package model;

import javax.swing.*;

public class Room extends JFrame {
     private int days;
     private int numBeds;
     private String bedSize;

     public void setDays(int days) {
          this.days = days;
     }

     public int getDays() {
          return this.days;
     }

     public void setNumBeds(int numBeds) {
          this.numBeds = numBeds;
     }

     public int getNumBeds() {
          return this.numBeds;
     }

     public void setBedSize(String bedSize) {
          this.bedSize = bedSize;
     }

     public String getBedSize() {
          return this.bedSize;
     }

     @Override
     public String toString() {
          return numBeds + " " + bedSize + " beds.";
     }
}
