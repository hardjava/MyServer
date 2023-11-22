package airbnb.persistence.dao;

import airbnb.persistence.dto.CompletedStayDTO;
import airbnb.persistence.dto.ReservationDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.naming.spi.ResolveResult;
import java.util.List;

public class ReservationDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public ReservationDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<ReservationDTO> getAll() {
        List<ReservationDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getAll");
        }

        return list;
    }

    // GUEST -> get reservation list
    public List<ReservationDTO> getReservationByUserId(int userId) {
        List<ReservationDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getReservationByUserId", userId);
        }

        return list;
    }
// HOST -> 예약 현황 조회
    public List<ReservationDTO> getReservationByHouseId(int houseId) {
        List<ReservationDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getReservationByHouseId", houseId);
        }
        return list;
    }

    // GUEST -> 숙박 완료
    public List<CompletedStayDTO> getCompletedStayReservationByUserId(int userId) {
        List<CompletedStayDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.Reservation.getCompletedStayReservationByUserId", userId);
        }

        return list;
    }
}