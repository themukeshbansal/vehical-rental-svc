package VehicalRentalService.strategy;

import VehicalRentalService.VehicleRental;

public class DefaultFareStrategy implements Strategy{
    @Override
    public int getStrategicMultiplier(VehicleRental vehicleRental) {
        return 1;
    }
}
