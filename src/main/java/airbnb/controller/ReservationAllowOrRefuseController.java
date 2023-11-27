package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public class ReservationAllowOrRefuseController {
    Protocol protocol;
    Protocol returnProtocol;

    public ReservationAllowOrRefuseController(Protocol protocol) {
        this.protocol = protocol;
    }

    // HOST -> 예약 승인 대기 승인 or 거절
    public void sendWaitingForReservationApprovalList() {
        System.out.println("HOST - 에약 대기 리스트 요청");
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<ReservationDTO> list = reservationDAO.getWaitingReservationStatusWAITING();
        returnProtocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_SUCCESS, list);
        System.out.println("\t예약 리스트 전송");
    }

    // HOST -> 예약 승인 or 거절 set
    public void setReservationStatus() throws IOException {
        System.out.println("HOST - 예약 승인 및 거절");
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReservationDTO reservationDTO = (ReservationDTO) protocol.getObject();

        if (reservationDTO.getReservationStatus() == Status.REFUSE) {   // 예약 거절
            reservationDAO.deleteByReservationId(reservationDTO);
            returnProtocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
            System.out.println("\t예약 승인");
        } else if (reservationDTO.getReservationStatus() == Status.BEFORE_STAY) {    // 예약 승인
            reservationDAO.updateReservationStatus(reservationDTO);
            returnProtocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_SUCCESS);
            System.out.println("\t예약 승인");
        } else {
            returnProtocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_ERROR);
            System.out.println("\t예약 거절");
        }
    }
}
