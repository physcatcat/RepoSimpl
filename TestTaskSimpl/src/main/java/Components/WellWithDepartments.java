package Components;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class WellWithDepartments {
    private List<Well> wells;
    private Department department;
}
