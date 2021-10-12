package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Well {
    private long Id;
    private String Name;
    private double X;
    private double Y;
}
