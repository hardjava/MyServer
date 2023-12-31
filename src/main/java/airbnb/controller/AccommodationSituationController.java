package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.HouseAndHostDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public class AccommodationSituationController {
    Protocol protocol;
    Protocol returnProtocol;

    public AccommodationSituationController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendApprovedHouseList() throws IOException {
        System.out.println("ADMIN - 숙소 (승인&요금 설정) 목록 요청");
        List<HouseDTO> list;
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        list = houseDAO.getApprovedHouseSetFeePolicy();
        returnProtocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tADMIN - 숙소 (승인&요금 설정) 목록 전송");
    }

    public void sendReservationByHouseId() throws IOException {
        System.out.println("ADMIN - 선택된 숙소 예약 목록 요청");
        List<ReservationDTO> list;
        HouseDTO houseDTO = (HouseDTO) protocol.getObject();
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        list = reservationDAO.getReservationWithStatusAfterStayByHouseId(houseDTO.getHouseId());

        returnProtocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("ADMIN - 선택된 숙소 예약 목록 전송");
    }
}
