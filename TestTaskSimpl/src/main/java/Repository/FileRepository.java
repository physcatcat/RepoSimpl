package Repository;

import Components.Department;
import Components.Well;
import Components.WellParameter;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@Slf4j

public class FileRepository implements Repository {
    private String wellsPath;
    private String wellParametersPath;
    private String departmentsPath;

    @Override
    public List<Well> getAllWells() {
        try{
            String json = Files.readString(Paths.get(wellsPath));
            Gson g = new Gson();
            return Arrays.stream(g.fromJson(json, Well[].class)).toList();
        } catch (Exception err) {
            log.info("Error in reading " + wellsPath);
        }
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        try{
            String json = Files.readString(Paths.get(departmentsPath));
            Gson g = new Gson();
            return Arrays.stream(g.fromJson(json, Department[].class)).toList();
        } catch (Exception err) {
            log.info("Error in reading " + departmentsPath);
        }
        return null;
    }

    @Override
    public List<WellParameter> getAllWellParameters() {
        try{
            String json = Files.readString(Paths.get(wellParametersPath));
            Gson g = new Gson();
            return Arrays.stream(g.fromJson(json, WellParameter[].class)).toList();
        } catch (Exception err) {
            log.info("Error in reading " + wellParametersPath);
        }
        return null;
    }

}
