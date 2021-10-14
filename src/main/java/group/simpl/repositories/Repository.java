package group.simpl.repositories;


import group.simpl.components.Department;
import group.simpl.components.Well;
import group.simpl.components.WellParameter;

import java.util.List;

public interface Repository {
    List<Well> getAllWells();
    List<Department> getAllDepartments();
    List<WellParameter> getAllWellParameters();
}
