package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Department {
    private String name;
    private long x;
    private long y;
    private long radius;
}
