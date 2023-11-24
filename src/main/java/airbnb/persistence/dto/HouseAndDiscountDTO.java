package airbnb.persistence.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class HouseAndDiscountDTO {
    private HouseDTO houseDTO;
    private DiscountPolicyDTO discountPolicyDTO;
}
