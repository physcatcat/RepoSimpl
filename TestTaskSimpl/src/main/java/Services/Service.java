package Services;

import Components.Department;
import Components.Well;
import Components.WellParameter;
import Repository.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Service {
    Repository repository;
    List<Well> wells;
    List<Department> departments;
    List<WellParameter> wellParameters;

    public void loadData() {
        wells = repository.getAllWells();
        departments = repository.getAllDepartments();
        wellParameters = repository.getAllWellParameters();
    }
}
