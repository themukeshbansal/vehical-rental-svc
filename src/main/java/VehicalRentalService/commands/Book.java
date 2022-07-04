package VehicalRentalService.commands;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import VehicalRentalService.models.Branch;
import VehicalRentalService.models.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class Book implements Command {

    public static final String COMMAND_NAME = "BOOK";
    String branchId;
    String vehicleType;
    int startTime;
    int endTime;

    public Book(String line) throws VehicleRentalCommandParseException {
        this.parse(line);
    }

    @Override
    public void parse(String line) throws VehicleRentalCommandParseException {
        String[] commands = line.split(COMMAND_SEPARATOR);
        if (commands.length == 5) {
            this.branchId = commands[1];
            this.vehicleType = commands[2];
            this.startTime = Integer.parseInt(commands[3]); // To handle non int value
            this.endTime = Integer.parseInt(commands[4]); // To handle non int value

        } else {
            throw new VehicleRentalCommandParseException("Command Not Parseable");
        }
    }

    @Override
    public String execute(VehicleRental vehicleRental) {
        ArrayList<Vehicle> availableVehicles = vehicleRental.getAvailableVehiclesOfType(branchId, vehicleType, startTime, endTime);
        Vehicle vehicle = availableVehicles.stream()
                .min(Comparator.comparing(v -> v.getFare(startTime, endTime))).orElse(null);
        if (vehicle != null){
            return String.valueOf(vehicle.bookVehicle(startTime, endTime));
        }
        return "-1";
    }
}
