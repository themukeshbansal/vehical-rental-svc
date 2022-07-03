package VehicalRentalService.models;

import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Branch {
    String id;
    HashMap<String, ArrayList<Vehicle>> vehicles;
    float percentageBooked;
    public Branch(String id, String[] vehicleTypes) {
        this.setId(id);
        this.vehicles = new HashMap<>();
        for (String vehicleType :
                vehicleTypes) {
            this.vehicles.put(vehicleType, new ArrayList<>(48));
        }
    }
    public void addVehicle(String vehicleType, String vehicleId, int fare) throws VehicleRentalCommandExecuteException {
        if (this.vehicles.containsKey(vehicleType)){
            ArrayList<Vehicle> branchVehiclesOfType = this.vehicles.get(vehicleType);;
            Optional<Vehicle> existingVehicle = branchVehiclesOfType.stream()
                    .filter(v -> v.getId().equals(vehicleId))
                    .findFirst();

            if (existingVehicle.isPresent()){
                existingVehicle.get().setFare(fare);
            } else {
                Vehicle vehicle = new Vehicle(vehicleId, fare);
                branchVehiclesOfType.add(vehicle);
            }
        } else {
            throw new VehicleRentalCommandExecuteException("Vehicle type does not exist");
        }
    }

    public float getPercentageBooked() {
        return percentageBooked;
    }

    public void setPercentageBooked(float percentageBooked) {
        this.percentageBooked = percentageBooked;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, ArrayList<Vehicle>> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", vehicles=" + vehicles +
                ", percentageBooked=" + percentageBooked +
                '}';
    }
}

