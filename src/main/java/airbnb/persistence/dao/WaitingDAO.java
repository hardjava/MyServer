package airbnb.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class WaitingDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public WaitingDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void deleteWaiting(int waitingId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("mapper.WaitingMapper.deleteWaiting", waitingId);
            session.commit();
        }
    }
}
