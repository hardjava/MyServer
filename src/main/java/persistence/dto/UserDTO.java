package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private int userID, roleID;
    private String userName, userPhone, userBirth, loginID, loginPWD;
}