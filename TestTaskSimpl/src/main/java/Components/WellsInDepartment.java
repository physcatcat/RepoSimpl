package Components;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

//для 3 задания, содержит месторождения и входящие в него скважины
public class WellsInDepartment {
    private List<Well> wells;
    private Department department;
}
