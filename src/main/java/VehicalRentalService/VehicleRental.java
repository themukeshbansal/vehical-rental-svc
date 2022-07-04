package VehicalRentalService;

import VehicalRentalService.models.Branch;
import VehicalRentalService.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRental {
    HashMap<String, Branch> branches;

    public VehicleRental(){
        this.branches = new HashMap<>();
    }

    public Optional<Branch> getBranchIfExists(String branchId) {
        return this.branches.containsKey(branchId) ?
                Optional.ofNullable(this.branches.get(branchId)) : Optional.empty();
    }

    public String onboardBranch(Branch branch){
        branches.put(branch.getId(), branch);
        return "TRUE";
    }

    public ArrayList<Vehicle> getAvailableVehicles(String branchId, int startTime, int endTime) {
        Optional<Branch> branch = getBranchIfExists(branchId);
        ArrayList<Vehicle> availableVehicles = new ArrayList<>();
        if (branch.isPresent()) {
            for(Map.Entry<String, ArrayList<Vehicle>> entry : branch.get().getVehicles().entrySet()) {
                ArrayList<Vehicle> vehicles = entry.getValue();
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.isAvailableForBooking(startTime, endTime)){
                        availableVehicles.add(vehicle);
                    }
                }
            }
        }
        return availableVehicles;
    }

    public ArrayList<Vehicle> getAvailableVehiclesOfType(String branchId, String vehicleType, int startTime, int endTime) {
        Optional<Branch> branch = getBranchIfExists(branchId);
        ArrayList<Vehicle> availableVehicles = new ArrayList<>();
        if (branch.isPresent()) {
            for(Vehicle vehicle : branch.get().getVehicles().get(vehicleType)) {
                if (vehicle.isAvailableForBooking(startTime, endTime)){
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }

    @Override
    public String toString() {
        return "VehicleRental{" +
                "branches=" + branches +
                '}';
    }
}
