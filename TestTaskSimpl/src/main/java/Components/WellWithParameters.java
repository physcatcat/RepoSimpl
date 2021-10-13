package Components;

import lombok.Builder;
import lombok.Data;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

@Data
@Builder

//для 2 задания содержит скважину и Map, где String - название параметра, DoubleSumm... содержит информацию о максимальном значении, минимальном и тд
public class WellWithParameters {
    private Well well;
    private Map<String, DoubleSummaryStatistics> parameters;
}
