package airbnb.persistence.dao;

import airbnb.exception.ExsistIdException;
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
            UserDTO userDTO = session.selectOne("mapper.UserMapper.searchId");
            if (userDTO != null) {
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
}