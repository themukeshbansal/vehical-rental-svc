package VehicalRentalService.commands;

import VehicalRentalService.CommandFactory;
import VehicalRentalService.VehicleRental;
import VehicalRentalService.commands.Command;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommandExecuteExceptionTest {
    VehicleRental vehicleRental;
    String commandLine;

    @BeforeEach
    void setUp() throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        vehicleRental = new VehicleRental();
        commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        executeCommandAndPrintOutput(commandLine);
    }

    @Test
    public void testAddBranchCommandExecuteException(){
        commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        Exception exception = assertThrows(VehicleRentalCommandExecuteException.class, () -> {
            executeCommandAndPrintOutput(commandLine);
        });
        String expectedMessage = "Branch Exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddVehicleCommandExecuteException(){
        String commandLine = "ADD_VEHICLE B1 JEEP V1 500";
        Exception exception = assertThrows(VehicleRentalCommandExecuteException.class, () -> {
            executeCommandAndPrintOutput(commandLine);
        });
        String expectedMessage = "FALSE";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void executeCommandAndPrintOutput(String commandLine) throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        Command command = CommandFactory.getCommand(commandLine);
        String commandOutput = command.execute(vehicleRental);
        System.out.print(commandOutput);
    }
}
