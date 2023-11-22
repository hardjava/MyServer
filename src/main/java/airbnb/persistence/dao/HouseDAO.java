package airbnb.persistence.dao;

import airbnb.persistence.dto.HouseDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class HouseDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public HouseDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<HouseDTO> getAll() {
        List<HouseDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.HouseMapper.getAll");
        }

        return list;
    }

    public void insertHouse(HouseDTO insertHouseDTO) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("mapper.HouseMapper.insertHouse", insertHouseDTO);
            session.commit();
        }
    }

    public HouseDTO getHouseByName(String name) {
        HouseDTO houseDTO;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            houseDTO = session.selectOne("mapper.HouseMapper.getHouseByName", name);
        }
        return houseDTO;
    }

    // 편의시설 필터링한거 가져오는거 string을 list로 만들어 전달 !
    // 숙소 검색때 쓰면 됨
    public List<HouseDTO> getHouseByAmenities(List<String> amenities) {
        List<HouseDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.HouseMapper.getHouseByAmenities", amenities);
            AmenitiesDAO amenitiesDAO = new AmenitiesDAO(sqlSessionFactory);
            amenitiesDAO.incrementAmenitiesCount(amenities);
            // 검색 횟수 늘려주는건데 일단 여기 적어놓고 getHouseByAmenities 호출한데서 검색 횟수 증가하는게 맞을 듯함
        }

        return list;
    }

    public List<HouseDTO> getApprovedHouseNotSetFeePolicy() {
        List<HouseDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.HouseMapper.getApprovedHouseNotSetFeePolicy");
        }

        return list;
    }

    public List<HouseDTO> getApprovedHouse() {
        List<HouseDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.HouseMapper.getApprovedHouse");
        }

        return list;
    }

    public List<HouseDTO> getNotApprovedHouse() {
        List<HouseDTO> list;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            list = session.selectList("mapper.HouseMapper.getNotApprovedHouse");
        }

        return list;
    }
}
