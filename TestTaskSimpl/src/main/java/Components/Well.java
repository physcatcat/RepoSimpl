package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Well {
    private long id;
    private String name;
    private double x;
    private double y;
}
