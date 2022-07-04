import VehicalRentalService.CommandFactory;
import VehicalRentalService.VehicleRental;
import VehicalRentalService.commands.AddBranch;
import VehicalRentalService.commands.AddVehicle;
import VehicalRentalService.commands.Command;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//        Requirements:
//        Onboard a new branch with available vehicles.
//        Onboard new vehicle(s) of an existing type to a particular branch.
//        Rent a vehicle for a time slot and a vehicle type(the lowest price as the default choice extendable to any other strategy).
//        Display available vehicles for a given branch sorted on price.
//        The vehicle will have to be dropped at the same branch where it was picked up.
//        Bonus question:
//        Dynamic pricing – demand vs supply. If 80% of cars in a particular branch are booked, increase the price by 10%.

public class Driver {
    public static void main(String[] args) {
        List<Command> commands = new ArrayList<>();
        String filePath = null;
        if(args.length > 0){
            filePath = args[0];
        }
        fetchCommandsFromFilePath(commands, filePath);
        VehicleRental vehicleRental = new VehicleRental();
        executeCommands(vehicleRental, commands);
    }

    private static void fetchCommandsFromFilePath(List<Command> commands, String filePath) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            Scanner reader = new Scanner(new FileInputStream(filePath));
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                if(line.length() > 0) {
                    lines.add(line);
                } else {
                    break;
                }
            }
            lines.forEach(line -> {
                Command command = null;
                try {
                    command = CommandFactory.getCommand(line);
                } catch (VehicleRentalCommandParseException e) {
                    throw new RuntimeException(e);
                }
                if (command != null)
                    commands.add(command);
            });
        } catch (NullPointerException | FileNotFoundException e) {
            throw new RuntimeException("Error reading file from the given path");
        }
    }

    public static void executeCommands(VehicleRental vehicleRental, List<Command> commands){
        for (Command command:
             commands) {
            String commandOutput = null;
            try {
                commandOutput = command.execute(vehicleRental);
            } catch (VehicleRentalCommandExecuteException e) {
                    System.out.println(e.getMessage());
                continue;
            }
            System.out.println(commandOutput);
        }
    }
}


