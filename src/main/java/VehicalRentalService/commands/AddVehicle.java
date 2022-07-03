package VehicalRentalService.commands;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import VehicalRentalService.models.Branch;

import java.util.Optional;

public class AddVehicle implements Command {

    public static final String COMMAND_NAME = "ADD_VEHICLE";
    String branchId;
    String vehicleType;
    String vehicleId;
    int price;

    public AddVehicle(String line) throws VehicleRentalCommandParseException {
        this.parse(line);
    }
    @Override
    public void parse(String line) throws VehicleRentalCommandParseException {
        String[] commands = line.split(COMMAND_SEPARATOR);
        if (commands.length == 5) {
            this.branchId = commands[1];
            this.vehicleType = commands[2]; // To handle error if vehicle type does not exist
            this.vehicleId = commands[3];
            this.price = Integer.parseInt(commands[4]); // To handle non int value

        } else {
            System.out.println("");
            throw new VehicleRentalCommandParseException("Command Not Parseable");
        }
    }

    @Override
    public String execute(VehicleRental vehicleRental) throws VehicleRentalCommandExecuteException {
        Optional<Branch> branch = vehicleRental.getBranchIfExists(this.branchId);
        if(branch.isPresent()){
            branch.get().addVehicle(vehicleType, vehicleId, price);
            return "TRUE";
        }
        return "FALSE";
    }
}
