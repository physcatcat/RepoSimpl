package group.simpl.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import group.simpl.components.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRepository implements Repository {
    private String wellsPath;
    private String wellParametersPath;
    private String departmentsPath;

    @Override
    public List<Well> getAllWells() {
        try{
            String json = Files.readString(Paths.get(wellsPath));
            Gson g = new Gson();
            Type typeOfWell = new TypeToken<List<Well>>() {}.getType();
            return g.fromJson(json, typeOfWell);
        } catch (Exception err) {
            System.out.println("Error in reading " + wellsPath);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Department> getAllDepartments() {
        try{
            String json = Files.readString(Paths.get(departmentsPath));
            Gson g = new Gson();
            Type typeOfWell = new TypeToken<List<Department>>() {}.getType();
            return g.fromJson(json, typeOfWell);
        } catch (Exception err) {
            System.out.println("Error in reading " + departmentsPath);
        }
        return Collections.emptyList();
    }

    @Override
    public List<WellParameter> getAllWellParameters() {
        try{
            String json = Files.readString(Paths.get(wellParametersPath));
            Gson g = new Gson();
            Type typeOfWellParameter = new TypeToken<List<WellParameter>>() {}.getType();
            return g.fromJson(json, typeOfWellParameter);
        } catch (Exception err) {
            System.out.println("Error in reading " + wellParametersPath);
        }
        return Collections.emptyList();
    }
}
