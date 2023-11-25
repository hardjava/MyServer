package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class SearchHostReservationController {
    Protocol protocol;
    Protocol returnProtocol;

    public SearchHostReservationController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendHouseByHostId() throws IOException {
        List<HouseDTO> list;
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        UserDTO userDTO = (UserDTO) protocol.getObject();

        list = houseDAO.getApprovedHouseSetFeePolicyByHostId(userDTO.getUserId());

        returnProtocol = new Protocol(Protocol.TYPE_VIEW_MY_HOUSE, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
    }

    public void sendReservationByHouseId() throws IOException {
        List<ReservationDTO> list;
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        HouseDTO houseDTO = (HouseDTO) protocol.getObject();

        list = reservationDAO.getReservationByHouseId(houseDTO.getHouseId());
        returnProtocol = new Protocol(Protocol.TYPE_VIEW_MY_HOUSE, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
    }
}
