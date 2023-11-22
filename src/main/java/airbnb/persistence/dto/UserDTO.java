package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDTO implements Serializable {
    private int userId;
    private String userName, userPhone, loginId;
    private RoleType role;
    private String loginPwd, userBirthday;
}