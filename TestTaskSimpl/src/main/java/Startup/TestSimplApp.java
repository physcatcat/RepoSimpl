package Startup;

import Controllers.Controller;
import Repository.FileRepository;
import Repository.Repository;
import Services.Service;
import lombok.extern.slf4j.Slf4j;


@Slf4j

public class TestSimplApp {
    public static void main(String[] args) {
        String pathWells = "C:\\Users\\Павел\\Downloads\\Telegram Desktop\\wells.json";
        String pathDepartments = "C:\\Users\\Павел\\Downloads\\Telegram Desktop\\departments.json";
        String pathWellParameters = "C:\\Users\\Павел\\Downloads\\Telegram Desktop\\wellParameters.json";

        Repository repository = FileRepository.builder()
                .departmentsPath(pathDepartments)
                .wellsPath(pathWells)
                .wellParametersPath(pathWellParameters).build();

        Service service = Service.builder()
                .repository(repository).build();
        service.loadData();

        Controller controller = Controller.builder()
                .service(service).build();

        log.info(controller.getUniqueWellParametersNames().toString());
    //    log.info(controller.getWellsWithParametersById(10, 30).toString());
    //    log.info(controller.getWellsWithDepartments().toString());

    //    log.info(repository.getAllDepartments().toString()); // для проверки корректного чтения из файла
    //    log.info(repository.getAllWells().toString()); // для проверки корректного чтения из файла
    //    log.info(repository.getAllWellParameters().toString()); // для проверки корректного чтения из файла
    }
}
