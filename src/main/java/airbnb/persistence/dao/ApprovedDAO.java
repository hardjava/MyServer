package airbnb.persistence.dao;


import airbnb.persistence.dto.ApprovedDTO;
import airbnb.persistence.dto.InsertApprovedDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ApprovedDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public ApprovedDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insertApproved(InsertApprovedDTO insertApprovedDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("mapper.ApprovedMapper.insertApproved", insertApprovedDTO);
            session.commit();
        }
    }

    public void deleteApprovedByHouseId(int houseId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("mapper.ApprovedMapper.deleteApproved", houseId);
            session.commit();
        }
    }
}
