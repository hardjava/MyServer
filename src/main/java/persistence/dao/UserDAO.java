package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.UserDTO;

import java.util.List;

public class UserDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public UserDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<UserDTO> selectAll() {
        List<UserDTO> list = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.UserMapper.selectAll");
        }
        return list;
    }
}