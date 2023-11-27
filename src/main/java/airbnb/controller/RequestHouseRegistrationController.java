package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.HouseDTO;

import java.io.IOException;
import java.util.List;

public class RequestHouseRegistrationController {
    Protocol protocol;
    Protocol returnProtocol;
    public RequestHouseRegistrationController(Protocol protocol) {
        this.protocol = protocol;
    }

    //print waiting q list
    // 숙소 등록 신청 리스트 출력
    public void sendWaitHouse() throws IOException {
        List<HouseDTO> list;
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        list = houseDAO.getWaitingHouse();
        returnProtocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
    }
}
