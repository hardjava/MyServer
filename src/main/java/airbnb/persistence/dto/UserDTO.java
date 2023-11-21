package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private int userId;
    private String userName, userPhoneNumber, loginId, loginPwd;
    private LocalDateTime userBirthday;
    private RoleType role;
}