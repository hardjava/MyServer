package airbnb.persistence.dao;

import airbnb.exception.ExsistIdException;
import airbnb.persistence.dto.ReviewDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ReviewDAO {
    private final SqlSessionFactory sqlSessionFactory;
    public ReviewDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insertReview(ReviewDTO reviewDTO) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            ReviewDTO check = session.selectOne("mapper.ReviewMapper.getReviewByReservationId", reviewDTO.getReservationId());
            if (check == null) {
                session.insert("mapper.ReviewMapper.insert", reviewDTO);
                session.commit();
            } else {
                throw new ExsistIdException("Already exist !");
            }
        }
    }
}
