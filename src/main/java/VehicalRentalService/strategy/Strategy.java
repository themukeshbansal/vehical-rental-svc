package VehicalRentalService.strategy;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.models.Branch;

/**
 * Fare Strategy Class
 */
public interface Strategy {
    int getStrategicMultiplier(VehicleRental vehicleRental);
}
