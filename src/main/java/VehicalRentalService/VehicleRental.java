package VehicalRentalService;

import VehicalRentalService.models.Branch;

import java.util.HashMap;
import java.util.Optional;

public class VehicleRental {
    HashMap<String, Branch> branches;

    public VehicleRental(){
        this.branches = new HashMap<>();
    }

    public Optional<Branch> getBranchIfExists(String branchId) {
        return this.branches.containsKey(branchId) ?
                Optional.ofNullable(this.branches.get(branchId)) : Optional.empty();
    }

    public String onboardBranch(Branch branch){
        branches.put(branch.getId(), branch);
        return "TRUE";
    }

    @Override
    public String toString() {
        return "VehicleRental{" +
                "branches=" + branches +
                '}';
    }
}
