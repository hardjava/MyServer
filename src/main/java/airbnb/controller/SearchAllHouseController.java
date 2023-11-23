package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.HouseDTO;

import java.io.IOException;
import java.util.List;

public class SearchAllHouseController {
    Protocol protocol;

    public SearchAllHouseController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendAllHouseList() throws IOException{ // 모든 숙소 목록을 보내는 메소드
//        List<HouseDTO> list;
//        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        Protocol returnProtocol;
//
//        try{
//            list = houseDAO.getApprovedHouse();
//            returnProtocol = new Protocol(Protocol.Protocol.CODE_SUCCESS);
//            returnProtocol.setObject(list);
//            MyIOStream.oos.writeObject(returnProtocol);
//        } catch (IOException e) {
//            // 예외 클래스 !!
//            returnProtocol = new Protocol(Protocol.CODE_ERROR);
//            MyIOStream.oos.writeObject(returnProtocol);
//        }
    }
}
