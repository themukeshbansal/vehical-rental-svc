package VehicalRentalService;

import VehicalRentalService.commands.Command;
import VehicalRentalService.exceptions.VehicleRentalCommandExecuteException;
import VehicalRentalService.exceptions.VehicleRentalCommandParseException;
import VehicalRentalService.CommandFactory;
import VehicalRentalService.VehicleRental;
import VehicalRentalService.strategy.DefaultFareStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VehicleRentalTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    VehicleRental vehicleRental;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        vehicleRental = new VehicleRental(new DefaultFareStrategy());
    }


    @Test
    public void testAddBranch() throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        String commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        executeCommandAndPrintOutput(commandLine);
        assertEquals("TRUE", outContent.toString());
    }

    @Test
    public void testAddVehicle() throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        String commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        executeCommandAndPrintOutput(commandLine);
        commandLine = "ADD_VEHICLE B1 CAR V1 500";
        executeCommandAndPrintOutput(commandLine);
        assertEquals("TRUETRUE", outContent.toString());
    }

    @Test
    public void testBookVehicle() throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        String commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        executeCommandAndPrintOutput(commandLine);
        commandLine = "ADD_VEHICLE B1 CAR V1 500";
        executeCommandAndPrintOutput(commandLine);
        commandLine = "BOOK B1 CAR 1 3";
        executeCommandAndPrintOutput(commandLine);
        assertEquals("TRUETRUE1000", outContent.toString());
    }

    @Test
    public void testDisplayVehicle() throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        String commandLine = "ADD_BRANCH B1 CAR,BIKE,VAN";
        executeCommandAndPrintOutput(commandLine);
        commandLine = "ADD_VEHICLE B1 CAR V1 500";
        executeCommandAndPrintOutput(commandLine);
        commandLine = "DISPLAY_VEHICLES B1 1 5";
        executeCommandAndPrintOutput(commandLine);
        assertEquals("TRUETRUEV1", outContent.toString());
    }

    private void executeCommandAndPrintOutput(String commandLine) throws VehicleRentalCommandParseException, VehicleRentalCommandExecuteException {
        Command command = CommandFactory.getCommand(commandLine);
        String commandOutput = command.execute(vehicleRental);
        System.out.print(commandOutput);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void getBranchIfExists() {
    }

    @Test
    void onboardBranch() {
    }
}
