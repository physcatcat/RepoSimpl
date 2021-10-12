package Components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Department {
    private String Name;
    private double X;
    private double Y;
    private double Radius;
}
