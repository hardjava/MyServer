package airbnb.persistence.dto;

import airbnb.network.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor

public class HouseDTO implements Serializable {
    private int houseId;
    private String houseName, houseAddress, houseIntroduce;
    private int bedroom, bathroom;
    private Type type;

    public HouseDTO(String houseName, String houseAddress, String houseIntroduce, int bedroom, int bathroom) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseIntroduce = houseIntroduce;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
    }

    public String toString() {
        return String.format("%-40s%-40s", houseName, houseAddress);
    }
}
