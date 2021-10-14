package group.simpl.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class WellParametersDto {
    private String wellName;
    private List<ParameterDto> parameterDtos;
}
