package airbnb.controller;

import airbnb.exception.ImpossibleCancelException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.CompletedStayDTO;
import airbnb.persistence.dto.ReservationDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class SearchGuestReservationController {

    Protocol protocol;
    Protocol returnProtocol;

    public SearchGuestReservationController(Protocol protocol) {
        this.protocol = protocol;
    }


    public void sendReservationList() throws IOException {//클라이언트로 예약 현황 리스트 보내는 메소드
        List<CompletedStayDTO> list;
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        UserDTO userDTO = (UserDTO)protocol.getObject();

        try{
            list = reservationDAO.getBeforeStayReservationByUserId(userDTO.getUserId());
            returnProtocol = new Protocol(Protocol.CODE_SEND_RESERVATION, Protocol.CODE_SUCCESS, list);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (IOException e) {
            returnProtocol = new Protocol(Protocol.CODE_SEND_RESERVATION, Protocol.CODE_ERROR, e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }   // 예외 바꿔줘야함
    }

    public void requestReservationCancel() throws IOException {
        ReservationDTO reservationDTO = (ReservationDTO) protocol.getObject();
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        try {
            reservationDAO.deleteByReservationId(reservationDTO);
            returnProtocol = new Protocol(Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (ImpossibleCancelException e) {
            returnProtocol = new Protocol(Protocol.CODE_ERROR);
            returnProtocol.setObject(e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }
}
