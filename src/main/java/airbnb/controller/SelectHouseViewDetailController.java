package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.AmenitiesDAO;
import airbnb.persistence.dao.FeePolicyDAO;
import airbnb.persistence.dao.ReservationDAO;
import airbnb.persistence.dao.ReviewDAO;
import airbnb.persistence.dto.FeePolicyDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.MoreHouseInfoDTO;

import java.io.IOException;

public class SelectHouseViewDetailController {
    Protocol protocol;

    public SelectHouseViewDetailController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendMoreHouseInfo() throws IOException {
        MoreHouseInfoDTO moreHouseInfoDTO = new MoreHouseInfoDTO();
        HouseDTO houseDTO = (HouseDTO) protocol.getObject();

        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        FeePolicyDAO feePolicyDAO = new FeePolicyDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewDAO reviewDAO = new ReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        moreHouseInfoDTO.setAmenitiesDTOList(amenitiesDAO.getAmenitiesByHouseId(houseDTO.getHouseId()));
        moreHouseInfoDTO.setReservationDTOList(reservationDAO.getReservationByHouseId(houseDTO.getHouseId()));
        moreHouseInfoDTO.setReviewDTOList(reviewDAO.getReviewByHouseId(houseDTO.getHouseId()));
        moreHouseInfoDTO.setFeePolicyDTO(feePolicyDAO.getFeePolicyByHouseId(houseDTO.getHouseId()));

        Protocol returnProtocol = new Protocol(Protocol.TYPE_SELECT_HOUSE_VIEW_DETAIL, Protocol.CODE_SEND_SELECT_HOUSE_INFO, moreHouseInfoDTO);

        returnProtocol.setObject(moreHouseInfoDTO);
        MyIOStream.oos.writeObject(returnProtocol);
    }
}
