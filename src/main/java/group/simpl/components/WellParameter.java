package group.simpl.components;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WellParameter {
    @SerializedName("WellId")
    private Long wellId;
    @SerializedName("ParameterName")
    private String parameterName;
    @SerializedName("Value")
    private Double value;
}
