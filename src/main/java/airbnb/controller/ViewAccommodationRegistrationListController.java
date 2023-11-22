package airbnb.controller;

import airbnb.network.Protocol;
public class ViewAccommodationRegistrationListController {

    Protocol protocol;

    public ViewAccommodationRegistrationListController(Protocol protocol) {
        this.protocol = protocol;
    }


    public void sendAccommodationRegistrationList(){//등록 신청된 숙소 목록을 클라이언트로 전달하는 메소

    }
    public void uploadApprovalOrRejectInformation(){//승인 / 거절 정보를 데이터베이스에 업로드하는 메소드

    }
}
