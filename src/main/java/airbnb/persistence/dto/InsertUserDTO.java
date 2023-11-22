package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class InsertUserDTO {
    private String userName, userPhone, loginId;
    private RoleType role;
    private String loginPwd, userBirthday;
}
