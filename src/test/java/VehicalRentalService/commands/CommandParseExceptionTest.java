package VehicalRentalService.commands;

import VehicalRentalService.CommandFactory;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CommandParseExceptionTest {

    @Test
    public void testAddBranchCommandParseException(){
        String commandLine = "ADD_BRANCH B1 V1 CAR,BIKE,VAN";
        Exception exception = assertThrows(VehicleRentalCommandParseException.class, () -> {
            CommandFactory.getCommand(commandLine);
        });
        String expectedMessage = "Command Not Parseable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddVehicleCommandParseException(){
        String commandLine = "ADD_VEHICLE CAR V1 500";
        Exception exception = assertThrows(VehicleRentalCommandParseException.class, () -> {
            CommandFactory.getCommand(commandLine);
        });
        String expectedMessage = "Command Not Parseable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBookCommandParseException(){
        String commandLine = "BOOK B1 BIKE 5";
        Exception exception = assertThrows(VehicleRentalCommandParseException.class, () -> {
            CommandFactory.getCommand(commandLine);
        });
        String expectedMessage = "Command Not Parseable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDisplayVehiclesCommandParseException(){
        String commandLine = "DISPLAY_VEHICLES B1 V1 1 5";
        Exception exception = assertThrows(VehicleRentalCommandParseException.class, () -> {
            CommandFactory.getCommand(commandLine);
        });
        String expectedMessage = "Command Not Parseable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
