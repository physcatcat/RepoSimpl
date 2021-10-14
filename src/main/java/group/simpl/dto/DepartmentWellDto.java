package group.simpl.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DepartmentWellDto {
    private String deptName;
    private List<String> wellNames;
}
