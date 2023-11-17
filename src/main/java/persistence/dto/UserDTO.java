package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import network.RoleType;

@Getter
@Setter
@ToString
public class UserDTO {
    private int user_id;
    private String user_name, user_birthday, user_phone, login_id, login_pwd;
    private RoleType role;
}