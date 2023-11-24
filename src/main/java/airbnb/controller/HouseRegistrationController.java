package airbnb.controller;
import airbnb.exception.ExistHouseException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.AmenitiesDAO;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.AmenitiesDTO;
import airbnb.persistence.dto.RequestHouseDTO;

import java.io.IOException;

public class HouseRegistrationController {

    Protocol protocol;
    Protocol returnProtocol;

    public HouseRegistrationController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void houseRegistration() throws IOException {// 클라이언트로(X) HOST 로부터 받은 숙소정보를 등록 하는 메소드
        RequestHouseDTO requestHouseDTO = (RequestHouseDTO) protocol.getObject();
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        try {
            houseDAO.insertHouse(requestHouseDTO.getHouseDTO());
            int houseId = houseDAO.getHouseByName(requestHouseDTO.getHouseDTO().getHouseName()).getHouseId();
            for (AmenitiesDTO amenitiesDTO : requestHouseDTO.getAmenitiesDTOList()) {
                amenitiesDTO.setHouseId(houseId);
                amenitiesDAO.insertAmenities(amenitiesDTO);
            }
            returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (ExistHouseException e) {
            returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_ERROR, e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }
}
