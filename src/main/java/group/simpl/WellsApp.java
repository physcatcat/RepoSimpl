package group.simpl;

import group.simpl.controllers.Controller;
import group.simpl.repositories.FileRepository;
import group.simpl.repositories.Repository;
import group.simpl.services.Service;
import lombok.extern.slf4j.Slf4j;


public class WellsApp {
    public static void main(String[] args) {
        String pathWells = "C:\\Users\\Professional\\Downloads\\Telegram Desktop\\wells.json";
        String pathDepartments = "C:\\Users\\Professional\\Downloads\\Telegram Desktop\\departments.json";
        String pathWellParameters = "C:\\Users\\Professional\\Downloads\\Telegram Desktop\\wellParameters.json";

        Repository repository = FileRepository.builder()
                .departmentsPath(pathDepartments)
                .wellsPath(pathWells)
                .wellParametersPath(pathWellParameters).build();

        Service service = Service.builder()
                .repository(repository)
                .build();

        Controller controller = Controller.builder()
                .service(service)
                .build();

        System.out.println(controller.getUniqueWellParametersNames().toString());
        System.out.println(controller.getWellsWithParametersById(10, 30).toString());
        //System.out.println(controller.getDepartmentsWithWells().toString());
    }
}
