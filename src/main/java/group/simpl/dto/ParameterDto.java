package group.simpl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParameterDto {
    private String paramName;
    private Double minValue;
    private Double maxValue;
    private Double avgValue;
    private Double medValue;
}
