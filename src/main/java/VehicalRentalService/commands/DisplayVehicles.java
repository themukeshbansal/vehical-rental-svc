package VehicalRentalService.commands;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import VehicalRentalService.models.Branch;
import VehicalRentalService.models.Vehicle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisplayVehicles implements Command{

    public static final String COMMAND_NAME = "DISPLAY_VEHICLES";
    String branchId;
    int startTime;
    int endTime;

    public DisplayVehicles(String line) throws VehicleRentalCommandParseException {
        this.parse(line);
    }
    @Override
    public void parse(String line) throws VehicleRentalCommandParseException {
        String[] commands = line.split(COMMAND_SEPARATOR);
        if (commands.length == 4) {
            this.branchId = commands[1];
            this.startTime = Integer.parseInt(commands[2]); // To handle non int value
            this.endTime = Integer.parseInt(commands[3]); // To handle non int value
        } else {
            throw new VehicleRentalCommandParseException("Command Not Parseable");
        }
    }

    @Override
    public String execute(VehicleRental vehicleRental) {
        ArrayList<Vehicle> availableVehicles = vehicleRental.getAvailableVehicles(branchId, startTime, endTime);
        availableVehicles.sort(Comparator.comparing(o -> o.getFare(startTime, endTime)));
        String availableSortedVehicles = availableVehicles.stream()
                .flatMap(vehicle -> Stream.of(vehicle.getId()))
                .collect(Collectors.joining(","));
        return availableSortedVehicles;
    }
}
