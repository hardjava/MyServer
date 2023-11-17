package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.InsertUserDTO;
import persistence.dto.UserDTO;

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

    public void insertUser(InsertUserDTO insertUserDTO){
        try(SqlSession session = sqlSessionFactory.openSession()){
            session.insert("mapper.UserMapper.insertUser", insertUserDTO);
            session.commit();
        }
    }
}