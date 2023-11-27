package airbnb.controller;

import airbnb.exception.ExistHouseException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.AmenitiesDAO;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.AmenitiesDTO;
import airbnb.persistence.dto.RequestHouseDTO;

import java.io.IOException;
import java.util.List;

public class HouseRegistrationController {

    Protocol protocol;
    Protocol returnProtocol;

    public HouseRegistrationController(Protocol protocol) {
        this.protocol = protocol;
    }

    // 숙박 등록 요청
    public void houseRegistration() throws IOException {// 클라이언트로(X) HOST 로부터 받은 숙소정보를 등록 하는 메소드
        System.out.println("HOST - 숙박 등록 요청");

        RequestHouseDTO requestHouseDTO = (RequestHouseDTO) protocol.getObject();
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        try {
            houseDAO.insertHouse(requestHouseDTO.getHouseDTO());
            if (requestHouseDTO.getAmenitiesDTOList() != null) {
                int houseId = houseDAO.getHouseByName(requestHouseDTO.getHouseDTO().getHouseName()).getHouseId();
                for (AmenitiesDTO amenitiesDTO : requestHouseDTO.getAmenitiesDTOList()) {
                    amenitiesDTO.setHouseId(houseId);
                    amenitiesDAO.insertAmenities(amenitiesDTO);
                }
            }
            System.out.println("\tHOST - 숙박 등록 성공");
            returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (ExistHouseException e) {
            System.out.println("\tHOST - 숙박 등록 실패");
            returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_ERROR, e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }

    public void getBasicAmenities() throws IOException {
        System.out.println("HOST - 숙소 기본 편의 시설 요청");
        List<AmenitiesDTO> list;
        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        list = amenitiesDAO.getBasicAmenities();

        returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tHOST - 숙소 기본 편의 시설 전달");
    }

    public void getSafetyAmenities() throws IOException {
        System.out.println("HOST - 숙소 안전 편의 시설 요청");

        List<AmenitiesDTO> list;
        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        list = amenitiesDAO.getSafetyAmenities();

        returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);

        System.out.println("\tHOST - 숙소 안전 편의 시설 전달");
    }

    public void getAccessAmenities() throws IOException {
        System.out.println("HOST - 숙소 접근성 편의 시설 요청");

        List<AmenitiesDTO> list;
        AmenitiesDAO amenitiesDAO = new AmenitiesDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        list = amenitiesDAO.getAccessAmenities();

        returnProtocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);

        System.out.println("\tHOST - 숙소 접근성 편의 시설 전달");
    }
}
