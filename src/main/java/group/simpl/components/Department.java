package group.simpl.components;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {
    @SerializedName("Name")
    private String name;
    @SerializedName("X")
    private Double x;
    @SerializedName("Y")
    private Double y;
    @SerializedName("Radius")
    private Double radius;

    public boolean isWellInDepartment(Well well){
        return Math.pow(well.getX()-this.getX(), 2.0) +
                Math.pow(well.getY()-this.getY(), 2.0) <= Math.pow(this.getRadius(), 2.0);
    }
}
