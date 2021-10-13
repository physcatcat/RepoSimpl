package Controllers;

import Components.*;
import Services.Service;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@Builder

public class Controller {
    private Service service;

    public List<String> getUniqueWellParametersNames(){
        return service.getWellParameters().stream()
                .collect(Collectors.groupingBy(WellParameter::getParameterName, Collectors.counting())) //группировка параметров по имени + считаем количество таких имён
                .entrySet().stream()// мап переводим в энтрисет, затем в поток
                .filter(entry -> entry.getValue() == 1)//фильтруем по количеству имён (раз уникальное, то == 1)
                .map(Map.Entry::getKey) //мапим в поток строк
                .collect(Collectors.toList()); // в лист
    }

    //можно ли было проще это сделать?
    public List<WellWithParameters> getWellsWithParametersById(long startId, long endId){
        List <WellWithParameters> out = service.getWells().stream()
                .filter(w -> w.getId() <= endId && w.getId() >= startId)
                .map(w -> WellWithParameters.builder().well(w).build())
                .toList(); //получаю список скважин с id от startId до endId, попутно конвертируя их в WellWithParameters

        /*
        немного не по заданию дальше идёт - нет подсчёта медианного значения
        я пытался сделать, но там совсем мудрёно было, поэтому решил пока что просто группировкой с SummarizingDouble сделать
         */

        Map <Long, List<WellParameter>> paramsByWellId = service.getWellParameters().stream()
                .filter(p -> p.getWellId() <= endId && p.getWellId() >= startId) // отбрасываю параметры не подходящие по IdWell
                .collect(Collectors.groupingBy(WellParameter::getWellId)); //группирую параметры по Id скважин

        return out.stream()
                .peek(w -> w.setParameters( //для каждой скважины задаём parameters
                        paramsByWellId.get(w.getWell().getId()).stream() //из сгруппированных параметров по Id получаю параметры данной скважины
                                .collect(Collectors.groupingBy(WellParameter::getParameterName, Collectors.summarizingDouble(WellParameter::getValue)))))
                .collect(Collectors.toList()); //группирую их по имени и указываю summarizingDouble (подсчитывает минимальное, максимальное и среднее)
    }

    //здесь немного намудрил, но проще не смог придумать
    public List<WellsInDepartment> getDepartmentsWithWells(){
        List<WellsInDepartment> out = service.getDepartments().stream()
                .map(D -> WellsInDepartment.builder().department(D).build())
                .toList(); //конвертирую месторождения в WellsInDepartment

        out = Stream.concat(out.stream(), Stream.of(WellsInDepartment.builder().department(Department.builder().Name("Неизвестное месторождение").build()).build()))
                .toList(); // добавляю к списку неизвестное месторождение

        return out.stream()
                .peek(d -> d.setWells(service.getWells().stream() //для каждого месторождения задаю лист из скважин
                        .filter(w -> Math.pow(w.getX()-d.getDepartment().getX(), 2.0)+Math.pow(w.getY()-d.getDepartment().getY(), 2.0) <= Math.pow(d.getDepartment().getRadius(), 2.0))
                        .toList())) // +фильтрация скважин на попадание в текущее месторождение
                .toList();
    }
}
