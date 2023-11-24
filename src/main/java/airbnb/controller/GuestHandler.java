package airbnb.controller;

import airbnb.network.Protocol;

import java.io.IOException;

public class GuestHandler {


    public void receiveLoginRequestType(Protocol protocol) throws IOException, ClassNotFoundException { // 로그인 request 를 받았을 때 실행
        LoginController loginController = new LoginController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_LOGIN_REQUEST:
                loginController.login();
                System.out.println("로그인 요청");
                break;
            default:
                System.out.println("알 수 없는 코드: ");
                // 예외 담아서 보내?
                break;
        }
    }

    public void receiveSignType(Protocol protocol) throws IOException {
        SignController signController = new SignController(protocol);

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_SIGN_UP_INFO:
                signController.sign();
                System.out.println("가입 요청");
                break;
            default:
                System.out.println("알수 없는 코드");
                // 예외 담아서 보내?
                break;
        }
    }

    public void receivePersonalInfoEditType(Protocol protocol) throws IOException {
        PersonalInfoEditController personalInfoEditController = new PersonalInfoEditController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_MODIFY_NAME_INFO:
                personalInfoEditController.modifyUserNameInfo();
                System.out.println("유저 이름 변경");
                break;
            case Protocol.CODE_SEND_MODIFY_PHONENUMBER_INFO:
                personalInfoEditController.modifyUserPhoneNumberInfo();
                System.out.println("유저 핸드폰 번호 변경");
                break;
            case Protocol.CODE_SEND_MODIFY_BIRTHDAY_INFO:
                personalInfoEditController.modifyUserBirthdayInfo();
                System.out.println("유저 생일 변경");
                break;
            default:

                break;
        }
    }

    public void receiveSearchReservationType(Protocol protocol) throws IOException {
        SearchGuestReservationController searchReservationController = new SearchGuestReservationController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_MY_RESERVATION_REQUEST:
                searchReservationController.sendReservationList();
                break;
            case Protocol.CODE_REQUEST_RESERVATION_CANCELLATION:
                searchReservationController.requestReservationCancel();
                break;
            default:

                break;
        }
    }

    public void receiveWrittenReviewType(Protocol protocol) throws IOException {
        WrittenReviewController writtenReviewController = new WrittenReviewController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_WRITTEN_REVIEW_REQUEST:
                writtenReviewController.sendWrittenReviewList();

                break;

            case Protocol.CODE_SEND_MODIFY_REVIEW:
                //writtenReviewController.modifyReview();
                break;
            default:

                break;
        }
    }

    public void receiveStayedHouseType(Protocol protocol) throws IOException {
        StayedHouseController stayedHouseController = new StayedHouseController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_STAYED_HOUSE_LIST_REQUEST:
                stayedHouseController.sendStayedHouseList();
                break;

            default:

                break;
        }
    }

    public void receiveSendReviewType(Protocol protocol) throws IOException {
        SendReviewController sendReviewController = new SendReviewController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_REVIEW:
                sendReviewController.insertReview();
                break;

            default:

                break;
        }
    }

    public void receiveSearchAllHouseType(Protocol protocol) throws IOException {
        SearchAllHouseController searchAllHouseController = new SearchAllHouseController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST:
                searchAllHouseController.sendAllHouseList();
                break;

            default:

                break;
        }
    }

    public void receiveSelectHouseViewDetailType(Protocol protocol) throws IOException {
        SelectHouseViewDetailController selectHouseViewDetailController = new SelectHouseViewDetailController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SELECT_HOUSE_INFO_REQUEST:
                selectHouseViewDetailController.sendMoreHouseInfo();
                break;

            default:

                break;
        }
    }

    public void receiveRequestReservationType(Protocol protocol) throws IOException {
        RequestReservationController requestReservationController = new RequestReservationController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_RESERVATION_INFO:
                requestReservationController.insertReservation();
                break;

            default:

                break;
        }
    }

    public void receiveFilterType(Protocol protocol) throws IOException {
        FilterController filterController = new FilterController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_SELECT_FILTER:
                filterController.sendFilteredHouseList();
                break;

            default:

                break;
        }
    }

    //================================================관리자================================================

    public void receiveViewAccommodationRegistrationList(Protocol protocol) throws IOException { // 숙소 등록 신청 목록보기
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST:
                break;

            case Protocol.CODE_SEND_APPROVAL_OR_REJECT_INFORMATION:
                break;

            default:
                break;
        }
    }

    public void receiveMonthlyReservationStatusForAccommodation(Protocol protocol) throws IOException {

    }

    public void receiveMonthlyToTalRevenueForAccommodation(Protocol protocol) throws IOException {

    }
}
