package airbnb.persistence.dao;

import airbnb.persistence.dto.DiscountPolicyDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DiscountPolicyDAO {
    private final SqlSessionFactory sqlSessionFactory;
    public DiscountPolicyDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insertDiscountPolicy(DiscountPolicyDTO discountPolicyDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
//            session.insert()
        }
    }
}
