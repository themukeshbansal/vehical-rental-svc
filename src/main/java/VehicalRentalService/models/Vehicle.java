package VehicalRentalService.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Vehicle {
    String id;
    int fare;
    List<Boolean> slots;

    public Vehicle(String id, int fare){
        this.setId(id);
        this.setFare(fare);
        this.slots = new ArrayList<>(Arrays.asList(new Boolean[48]));
        Collections.fill(this.getSlots(), Boolean.FALSE);
    }

    public boolean isAvailableForBooking(int startTime, int endTime){
        if(startTime >= endTime) {
            return false;
        }
        for (int i = startTime; i <= endTime ; i++) {
            Boolean isBooked = this.slots.get(i);
            if (isBooked){
                return false;
            }
        }
        return true;
    }

    public int bookVehicle(int startTime, int endTime){
        int calculatedFare = -1;
        if (isAvailableForBooking(startTime, endTime)) {
            calculatedFare = getFare(startTime, endTime);
            for (int i = startTime; i <= endTime ; i++) {
                this.slots.set(i, true);
            }
        };
        return calculatedFare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFare(int startTime, int endTime) {
        return this.fare * (endTime - startTime);
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public List<Boolean> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Boolean> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", fare=" + fare +
                ", slots=" + slots +
                '}';
    }
}
