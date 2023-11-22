package airbnb.persistence.dao;

import airbnb.exception.ExsistIdException;
import airbnb.exception.WrongBirthdayException;
import airbnb.exception.WrongPhoneNumberException;
import airbnb.exception.WrongUserNameException;
import airbnb.persistence.dto.ModifyBirthdayDTO;
import airbnb.persistence.dto.ModifyPhoneNumberDTO;
import airbnb.persistence.dto.ModifyUserNameDTO;
import airbnb.persistence.dto.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public UserDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<UserDTO> getAll() {
        List<UserDTO> list;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.UserMapper.getAll");
        }
        return list;
    }

    public void updateUser(UserDTO updateDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("mapper.UserMapper.userUpdate", updateDTO);
            session.commit();
        }
    }

    public void insertUser(UserDTO insertUserDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserDTO userDTO = session.selectOne("mapper.UserMapper.searchId", insertUserDTO.getLoginId());
            if (userDTO == null) {
                session.insert("mapper.UserMapper.insertUser", insertUserDTO);
                session.commit();
            } else {
                throw new ExsistIdException("Exist Id");
            }
        }
    }

    // 개인정보 조회할 때 (마이페이지) 호출해서 사용하면 됨
    public UserDTO getUserByUserId(int userId) {
        UserDTO userDTO;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            userDTO = session.selectOne("mapper.UserMapper.getUserByUserId", userId);
        }
        return userDTO;
    }

    public void updateUserBirthday(ModifyBirthdayDTO modifyBirthdayDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int i = session.update("mapper.UserMapper.updateUserBirthday", modifyBirthdayDTO);
            if (i == 0) {
                new WrongBirthdayException("Wrong Birthday");
            } else {
                session.commit();
            }
        }
    }

    public void updateUserPhone(ModifyPhoneNumberDTO modifyPhoneNumberDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int i = session.update("mapper.UserMapper.updateUserPhone", modifyPhoneNumberDTO);
            if (i == 0) {
                new WrongPhoneNumberException("Wrong Phone Number");
            } else {
                session.commit();
            }
        }
    }

    public void updateUserName(ModifyUserNameDTO modifyUserNameDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int i = session.update("mapper.UserMapper.updateUserName", modifyUserNameDTO);
            if (i == 0) {
                new WrongUserNameException("Wrong User Name");
            } else {
                session.commit();
            }
        }
    }
}