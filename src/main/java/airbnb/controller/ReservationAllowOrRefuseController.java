package airbnb.controller;
import airbnb.network.Protocol;
public class ReservationAllowOrRefuseController {
    Protocol protocol;

    public ReservationAllowOrRefuseController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendWaitingForReservationApprovalList() {
        // 예약 '승인' 대기 리스트 요청받고 클라이언트에게 리스트 전달(클라이언트→서버)
    }

    public void sendReservationRefuseList() {
        // 예약 '거절' 대기 리스트 요청받고 클라이언트에게 리스트 전달(클라이언트→서버)
    }
}
