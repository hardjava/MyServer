package airbnb.controller;
import airbnb.network.Protocol;

public class AdminHandler {

    public void receiveViewAccommodationRegistrationList(Protocol protocol) { // 숙소 등록 신청 목록보기
        switch (protocol.getProtocolCode()) {

            case Protocol.CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST:

                break;

            case Protocol.CODE_SEND_APPROVAL_OR_REJECT_INFORMATION:
                break;

            default:
                break;
        }
    }

    public void receiveMonthlyReservationStatusForAccommodation(Protocol protocol) {

    }

    public void receiveMonthlyToTalRevenueForAccommodation(Protocol protocol)  {

    }
}
