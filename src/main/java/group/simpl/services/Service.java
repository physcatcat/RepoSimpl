package group.simpl.services;

import group.simpl.components.*;
import group.simpl.dto.DepartmentWellDto;
import group.simpl.dto.ParameterDto;
import group.simpl.dto.WellParametersDto;
import group.simpl.repositories.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Data
@Builder
public class Service {
    private Repository repository;

    public List<String> getUniqueWellParametersNames(){
        return repository.getAllWellParameters().stream()
                .map(WellParameter::getParameterName)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<WellParameter> getWellParamsByWellId(Long wellId){
        return repository.getAllWellParameters().stream()
                .filter(p -> p.getWellId().equals(wellId))
                .collect(Collectors.toList());
    }

    private Double calcMediane(List<Double> params){
        List<Double> sortedParams =  params.stream()
                .sorted(Double::compareTo)
                .collect(Collectors.toList());
        int paramsSize = sortedParams.size();
        return paramsSize % 2 == 1 ?
                sortedParams.get(paramsSize / 2) :
                0.5 * (sortedParams.get(paramsSize / 2 - 1) + sortedParams.get(paramsSize / 2));
    }

    private ParameterDto calculateParamDto(String paramName, List<WellParameter> wellParameters){
        DoubleSummaryStatistics result = wellParameters.stream()
                .collect(Collectors.summarizingDouble(WellParameter::getValue));
        return ParameterDto.builder()
                .paramName(paramName)
                .medValue(
                        calcMediane(
                                wellParameters.stream()
                                        .map(WellParameter::getValue)
                                        .collect(Collectors.toList())
                        )
                )
                .minValue(result.getMin())
                .maxValue(result.getMax())
                .avgValue(result.getAverage())
                .build();
    }

    public List<ParameterDto> buildParamDtoList(List<WellParameter> wellParameters){
        return wellParameters.stream()
                .collect(Collectors.groupingBy(WellParameter::getParameterName))
                .entrySet().stream()
                .map(p ->
                       calculateParamDto(p.getKey(), p.getValue())
                )
                .collect(Collectors.toList());
    }

    public List<WellParametersDto> getWellsWithParametersById(long startId, long endId){
        return repository.getAllWells().stream()
                .filter(w -> w.getId() <= endId && w.getId() >= startId)
                .map(w ->
                        WellParametersDto.builder()
                                .wellName(w.getName())
                                .parameterDtos(
                                        buildParamDtoList(
                                                getWellParamsByWellId(w.getId())
                                        )
                                )
                                .build()
                )
                .collect(Collectors.toList());
    }


    public List<DepartmentWellDto> getDepartmentsWithWells(){
        return repository.getAllWells().stream()
                .collect(Collectors.groupingBy(well -> repository.getAllDepartments().stream()
                        .filter(d -> d.isWellInDepartment(well))
                        .map(Department::getName)
                        .findFirst().orElse("Неизвестное месторождение")))
                .entrySet().stream()
                .map(entry ->
                        DepartmentWellDto.builder()
                                .deptName(entry.getKey())
                                .wellNames(entry.getValue().stream()
                                        .map(Well::getName)
                                        .collect(Collectors.toList())
                                )
                                .build()
                )
                .collect(Collectors.toList());
    }
}
