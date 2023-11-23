package airbnb.persistence.dao;

import airbnb.persistence.dto.FeePolicyDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class FeePolicyDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public FeePolicyDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public FeePolicyDTO getFeePolicyByHouseId(int houseId) {
        FeePolicyDTO feePolicyDTO;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            feePolicyDTO = session.selectOne("mapper.FeePolicy.getFeePolicyByHouseId", houseId);
        }

        return feePolicyDTO;
    }
}
