package airbnb.controller;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.CompletedStayDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class StayedHouseController {

    Protocol protocol;
    Protocol returnProtocol;

    public StayedHouseController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendStayedHouseList() throws IOException {//클라이언트로 묵었던 숙소리스트 보내는 메소드
        List<CompletedStayDTO> list;
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        UserDTO userDTO = (UserDTO)protocol.getObject();
        try{
            list = reservationDAO.getCompletedStayReservationByUserId(userDTO.getUserId());
            returnProtocol = new Protocol(Protocol.TYPE_STAYED_HOUSE, Protocol.CODE_SUCCESS);
            returnProtocol.setObject(list);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (IOException e) {
            returnProtocol = new Protocol(Protocol.TYPE_STAYED_HOUSE, Protocol.CODE_ERROR);
            returnProtocol.setObject(e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }

}
