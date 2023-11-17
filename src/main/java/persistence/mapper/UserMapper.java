package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.UserDTO;

import java.util.List;

public interface UserMapper {
    final String getAll = "SELECT * FROM USER";
    @Select(getAll)
    @Results(
            id = "userResultSet", value = {
                    @Result(property = "user_id", column = "user_id"),
                    @Result(property = "user_name", column = "user_name"),
                    @Result(property = "user_phone", column = "user_phone"),
                    @Result(property = "user_birthday", column = "user_birthday"),
                    @Result(property = "role", column = "role"),
                    @Result(property = "login_id", column = "login_id"),
                    @Result(property = "login_pwd", column = "login_pwd")
    })
    List<UserDTO> getAll();
}