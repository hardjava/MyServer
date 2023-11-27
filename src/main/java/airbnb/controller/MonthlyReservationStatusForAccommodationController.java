package airbnb.controller;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public class MonthlyReservationStatusForAccommodationController {

    Protocol protocol;
    Protocol returnProtocol;

    public MonthlyReservationStatusForAccommodationController(Protocol protocol) {
        this.protocol = protocol;
    }

    // 숙소 아이디로 예약 현황 조회 및 수익 조회
    // 숙소 아이디로 완료된 예약 리스트 반환
    public void sendReservation() throws IOException {
        HouseDTO houseDTO = (HouseDTO) protocol.getObject();
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<ReservationDTO> list;

        list = reservationDAO.getReservationWithStatusAfterStayByHouseId(houseDTO.getHouseId());

        returnProtocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
    }

    // 승인되었고 요금 정책 설정된 모든 숙소 목록
    public void sendAllHouse() throws IOException {
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<HouseDTO> list = houseDAO.getApprovedHouseSetFeePolicy();

        returnProtocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
    }
}
