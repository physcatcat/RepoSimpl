package group.simpl.components;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Well {
    @SerializedName("Id")
    private Long id;
    @SerializedName("Name")
    private String name;
    @SerializedName("X")
    private Double x;
    @SerializedName("Y")
    private Double y;
}
