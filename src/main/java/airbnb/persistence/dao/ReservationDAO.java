package airbnb.persistence.dao;

import airbnb.exception.ImpossibleCancelException;
import airbnb.network.Status;
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

    public List<ReservationDTO> getWaitingReservationStatusWAITING() {
        List<ReservationDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getWaitingReservationStatusWAITING");
        }

        return list;
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

    public List<ReservationDTO> getReservationWithStatusAfterStayByHouseId(int houseId) {
        List<ReservationDTO> list;

        try(SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getReservationWithStatusAfterStayByHouseId", houseId);
        }

        return list;
    }

    // GUEST -> 숙박 완료
    public List<CompletedStayDTO> getCompletedStayReservationByUserId(int userId) {
        List<CompletedStayDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getCompletedStayReservationByUserId", userId);
            for (CompletedStayDTO completedStayDTO : list) {
                completedStayDTO.setHasReview(session.selectOne("mapper.ReservationMapper.hasReview", completedStayDTO.getReservationId()));
            }
        }

        return list;
    }

    public List<CompletedStayDTO> getBeforeStayReservationByUserId(int userId) {
        List<CompletedStayDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.ReservationMapper.getBeforeStayReservationByUserId", userId);
        }

        return list;
    }
    
    public void deleteByReservationId(ReservationDTO reservationDTO) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            if (reservationDTO.getReservationStatus() == Status.BEFORE_STAY) {
                session.delete("mapper.ReservationMapper.deleteByReservationId", reservationDTO.getReservationId());
                session.commit();
            }
            else {
                throw new ImpossibleCancelException("Cancellation not possible before approval");
            }
        }
    }

    public void updateReservationStatus(ReservationDTO reservationDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("mapper.ReservationUpdate.updateReservationStatus", reservationDTO);
            session.commit();
        }
    }
}