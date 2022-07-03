package VehicalRentalService.commands;

import VehicalRentalService.VehicleRental;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import VehicalRentalService.models.Branch;

import java.util.Optional;

public class AddBranch implements Command{

    public static final String COMMAND_NAME = "ADD_BRANCH";
    String branchId;
    String[] vehicleTypes;

    public AddBranch(String line) throws VehicleRentalCommandParseException {
        this.parse(line);
    }
    @Override
    public void parse(String line) throws VehicleRentalCommandParseException {
        String[] commands = line.split(COMMAND_SEPARATOR);
        if (commands.length == 3) {
            this.branchId = commands[1];
            this.vehicleTypes = commands[2].split(VEHICLE_TYPE_SEPARATOR);
        } else {
            throw new VehicleRentalCommandParseException("Command Not Parseable");
        }
    }

    @Override
    public String execute(VehicleRental vehicleRental) throws VehicleRentalCommandExecuteException {
        Optional<Branch> existingBranch = vehicleRental.getBranchIfExists(branchId);
        if(existingBranch.isPresent()){
            throw new VehicleRentalCommandExecuteException("Branch Exists");
        }
        Branch branch = new Branch(branchId, vehicleTypes);
        return vehicleRental.onboardBranch(branch);
    }
}
