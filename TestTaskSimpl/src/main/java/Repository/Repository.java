package Repository;

import Components.Department;
import Components.Well;
import Components.WellParameter;

import java.util.List;

public interface Repository {
    List<Well> getAllWells();
    List<Department> getAllDepartments();
    List<WellParameter> getAllWellParameters();
}
