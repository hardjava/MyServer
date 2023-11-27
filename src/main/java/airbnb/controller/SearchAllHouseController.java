package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.*;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.MoreHouseInfoDTO;

import java.io.IOException;
import java.util.List;

public class SearchAllHouseController {
    Protocol protocol;
    Protocol returnProtocol;

    public SearchAllHouseController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendAllHouseList() throws IOException { // 모든 숙소 목록을 보내는 메소드
        System.out.println("GUEST - 숙소 목록 요청");
        List<HouseDTO> list;
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        Protocol returnProtocol;

        list = houseDAO.getApprovedHouseSetFeePolicy();
        returnProtocol = new Protocol(Protocol.TYPE_SEARCH_ALL_HOUSE, Protocol.CODE_SUCCESS);
        returnProtocol.setObject(list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tGUEST - 숙소 목록 전달");
    }
}

