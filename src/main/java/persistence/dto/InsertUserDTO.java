package persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import network.RoleType;


@Getter
@Setter
@ToString
@Builder
public class InsertUserDTO {
    private int user_id;
    private String user_name, user_phone, login_id, login_pwd, user_birthday;
    private RoleType role;


        public InsertUserDTO(int user_id, String user_name, String user_phone, String user_birthday, String login_id, String login_pwd, RoleType role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_birthday = user_birthday;
        this.login_id = login_id;
        this.login_pwd = login_pwd;
        this.role = role;
    }
}
