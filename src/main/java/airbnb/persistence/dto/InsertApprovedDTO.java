package airbnb.persistence.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor

public class InsertApprovedDTO {
    private int houseId;
    private boolean isApproved;

}
