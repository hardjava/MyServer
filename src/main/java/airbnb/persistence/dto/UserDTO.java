package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDTO implements Serializable {
    private int userId;
    private String userName, userPhoneNumber, loginId, loginPwd;
    private LocalDate userBirthday;
    private RoleType role;

    public UserDTO(String userName, String userPhoneNumber, String loginId, String loginPwd, LocalDate userBirthday, RoleType role) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.userBirthday = userBirthday;
        this.role = role;
    }
}