package group.simpl.controllers;

import group.simpl.components.*;
import group.simpl.dto.WellParametersDto;
import group.simpl.services.Service;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@Builder
public class Controller {
    private Service service;

    public List<String> getUniqueWellParametersNames(){
        return service.getUniqueWellParametersNames();
    }

    public List<WellParametersDto> getWellsWithParametersById(long startId, long endId){
        return service.getWellsWithParametersById(startId, endId);
    }
}
