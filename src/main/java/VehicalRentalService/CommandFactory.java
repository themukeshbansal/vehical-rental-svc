package VehicalRentalService;


import VehicalRentalService.commands.*;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;

public class CommandFactory {
    public static Command getCommand(String line) throws VehicleRentalCommandParseException {
        Command command = null;
        String[] commandArray = line.split(" ");
//        System.out.println(commandArray[0]);
        switch (commandArray[0]) {
            case AddBranch.COMMAND_NAME:
                command = new AddBranch(line);
                break;
            case AddVehicle.COMMAND_NAME:
                command = new AddVehicle(line);
                break;
            case Book.COMMAND_NAME:
                command = new Book(line);
                break;
            case DisplayVehicles.COMMAND_NAME:
                command = new DisplayVehicles(line);
                break;

        }
        return command;
    }
}
