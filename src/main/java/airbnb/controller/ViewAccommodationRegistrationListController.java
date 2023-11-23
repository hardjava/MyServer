package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.WaitingDAO;
import airbnb.persistence.dto.WaitingDTO;

import java.io.IOException;
import java.util.List;
public class ViewAccommodationRegistrationListController {

    Protocol protocol;

    public ViewAccommodationRegistrationListController(Protocol protocol) {
        this.protocol = protocol;
    }


    public void sendAccommodationRegistrationList() throws IOException {//등록 신청된 숙소 목록을 클라이언트로 전달하는 메소드

        WaitingDAO waitingDAO = new WaitingDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<WaitingDTO> list = waitingDAO.getAll();
        Protocol newProtocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST,Protocol.CODE_SEND_ACCOMMODATION_REGISTRATION_LIST,list);
        MyIOStream.oos.writeObject(newProtocol);
    }
    public void uploadApprovalOrRejectInformation(){//승인 / 거절 정보를 데이터베이스에 업로드하는 메소드

    }


}
