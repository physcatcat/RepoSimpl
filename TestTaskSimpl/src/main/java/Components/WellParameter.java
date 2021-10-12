package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class WellParameter {
    private long WellId;
    private String ParameterName;
    private double Value;
}
