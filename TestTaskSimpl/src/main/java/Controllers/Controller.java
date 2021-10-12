package Controllers;

import Components.WellWithDepartments;
import Components.WellWithParameters;
import Services.Service;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder

public class Controller {
    private Service service;

    public List<String> getUniqueWellParametersNames(){
        List<String> names = service.getWellParameters().stream().map(p -> p.getParameterName()).toList();
        return names.stream().filter(s -> names.lastIndexOf(s) == names.indexOf(s)).toList();
    }

    public List<WellWithParameters> getWellsWithParametersById(long startId, long endId){ //заменить
        return null;
    }

    public List<WellWithDepartments> getWellsWithDepartments(){ // (x-x0)^2+(y-y0)^2 = r^2
        List<WellWithDepartments> out = service.getDepartments().stream().map(D -> WellWithDepartments.builder().department(D).build()).toList();
        return null;
    }

}
