package Components;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class WellWithParameters {
    Well well;
    List<WellParameter> parameters;
}
