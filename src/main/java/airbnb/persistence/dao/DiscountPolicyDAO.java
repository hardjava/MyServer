package airbnb.persistence.dao;

import airbnb.persistence.dto.DiscountPolicyDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DiscountPolicyDAO {
    private final SqlSessionFactory sqlSessionFactory;
    public DiscountPolicyDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert(DiscountPolicyDTO discountPolicyDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DiscountPolicyDTO check = session.selectOne("mapper.DiscountPolicyMapper.getDiscountByHouseId", discountPolicyDTO.getHouseId());

            if (check == null) {    // 없으면 새로 넣음
                session.insert("mapper.DiscountPolicyMapper.insert", discountPolicyDTO);
                session.commit();
            } else {        // 있으면 바꿈
                session.update("mapper.DiscountPolicyMapper.update", discountPolicyDTO);
                session.commit();
            }
            session.update("mapper.DiscountPolicyMapper.reservationFeeSynchronize", discountPolicyDTO);
        }
    }

    public DiscountPolicyDTO getDiscountPolicyByHouseId(int houseId) {
        DiscountPolicyDTO discountPolicyDTO;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            discountPolicyDTO = session.selectOne("mapper.DiscountPolicyMapper.getDiscountPolicyByHouseId", houseId);
        }
        return discountPolicyDTO;
    }

//    public void reservationFeeSynchronize(DiscountPolicyDTO discountPolicyDTO) {
//        try(SqlSession session = sqlSessionFactory.openSession()) {
//            session.update("mapper.DiscountPolicyMapper.reservationFeeSynchronize", discountPolicyDTO);
//            session.commit();
//        }
//    }
}
