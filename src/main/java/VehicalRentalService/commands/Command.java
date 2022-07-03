package VehicalRentalService.commands;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;

public interface Command {

    String COMMAND_SEPARATOR = " ";
    String VEHICLE_TYPE_SEPARATOR = ",";

    void parse(String line) throws VehicleRentalCommandParseException;
    String execute(VehicleRental vehicleRental) throws VehicleRentalCommandExecuteException;
}
