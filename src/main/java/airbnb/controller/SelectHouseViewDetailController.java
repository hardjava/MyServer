package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.*;
import airbnb.persistence.dto.DiscountPolicyDTO;
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
        System.out.println("GUEST - 숙소 상세 정보 요청");
        MoreHouseInfoDTO moreHouseInfoDTO = new MoreHouseInfoDTO();
        HouseDTO houseDTO = (HouseDTO) protocol.getObject();

        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        FeePolicyDAO feePolicyDAO = new FeePolicyDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewDAO reviewDAO = new ReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        DiscountPolicyDAO discountPolicyDAO = new DiscountPolicyDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        moreHouseInfoDTO.setDiscountPolicyDTO(discountPolicyDAO.getDiscountPolicyByHouseId(houseDTO.getHouseId()));
        moreHouseInfoDTO.setAmenitiesDTOList(amenitiesDAO.getAmenitiesByHouseId(houseDTO.getHouseId()));
        FeePolicyDTO temp = feePolicyDAO.getFeePolicyByHouseId(houseDTO.getHouseId());
        moreHouseInfoDTO.setFeePolicyDTO(temp);
        moreHouseInfoDTO.setReservationDTOList(reservationDAO.getReservationByHouseId(houseDTO.getHouseId()));
        moreHouseInfoDTO.setReviewDTOList(reviewDAO.getReviewByHouseId(houseDTO.getHouseId()));

        Protocol returnProtocol = new Protocol(Protocol.TYPE_SELECT_HOUSE_VIEW_DETAIL, Protocol.CODE_SEND_SELECT_HOUSE_INFO, moreHouseInfoDTO);

        returnProtocol.setObject(moreHouseInfoDTO);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tGUEST - 숙소 상세 정보 전달");
    }

    public void receiveReservationRequest() {

    }
}
