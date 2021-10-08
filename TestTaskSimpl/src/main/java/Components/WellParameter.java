package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class WellParameter {
    private String name;
    private long wellId;
    private long value;
}
