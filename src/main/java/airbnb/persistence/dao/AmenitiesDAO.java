package airbnb. persistence.dao;

import airbnb.persistence.dto.AmenitiesDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AmenitiesDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public AmenitiesDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<AmenitiesDTO> getAll() {
        List<AmenitiesDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.Amenities.getAll");
        }

        return list;
    }

    public void incrementAmenitiesCount(List<String> amenities) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.selectList("mapper.HouseMapper.incrementAmenitiesCount", amenities);
        }
    }
}
