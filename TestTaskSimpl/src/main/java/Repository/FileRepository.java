package Repository;

import Components.Department;
import Components.Well;
import Components.WellParameter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class FileRepository implements Repository {
    private String wellsPath;
    private String wellParametersPath;
    private String departmentsPath;

    @Override
    public List<Well> getAllWells() {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public List<WellParameter> getAllWellParameters() {
        return null;
    }

}
