package airbnb.persistence.dao;

import airbnb.persistence.dto.InsertWaitingDTO;
import airbnb.persistence.dto.WaitingDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class WaitingDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public WaitingDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 숙소 대기 큐 조회
    public List<WaitingDTO> getAll() {
        List<WaitingDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.WaitingMapper.getAll");
        }
        return list;
    }

    // 숙소 대기 큐 삽입
    public void insertWaiting(InsertWaitingDTO insertWaitingDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("mapper.WaitingMapper.insertWaiting", insertWaitingDTO);
            session.commit();
        }
    }

    public void insertWaitingRecentHouse() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int houseId = session.selectOne("mapper.HouseMapper.getMaxHouseId");
            session.insert("mapper.WaitingMapper.insertWaiting", new InsertWaitingDTO(houseId));
            session.commit();
        }
    }

    // 숙소 대기 큐 삭제
    public void deleteWaiting(int waitingId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("mapper.WaitingMapper.deleteWaiting", waitingId);
            session.commit();
        }
    }
}
