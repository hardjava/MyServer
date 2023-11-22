package airbnb.persistence.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class InsertHouseDTO {
    private int capacity, bedroom, bathroom;
    private String house_name, house_address, house_introduce;

}
