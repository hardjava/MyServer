package airbnb.persistence.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class DiscountPolicyDTO {
    private int discountId, discountDay, discountOpt, houseId;

    public DiscountPolicyDTO(int discountDay, int discountOpt, int houseId) {
        this.discountDay = discountDay;
        this.discountOpt = discountOpt;
        this.houseId = houseId;
    }

    public DiscountPolicyDTO(int discountDay, int discountOpt) {
        this.discountDay = discountDay;
        this.discountOpt = discountOpt;
    }
}
