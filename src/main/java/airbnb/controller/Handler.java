package airbnb.controller;

import airbnb.network.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Handler {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Handler(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void receiveLoginRequestType(Protocol protocol) throws IOException, ClassNotFoundException { // 로그인 request 를 받았을 때 실행
        LoginController loginController = new LoginController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_LOGIN_REQUEST:
                loginController.login(objectOutputStream, objectInputStream, protocol);
                System.out.println("로그인 요청");
                break;
            default:
                System.out.println("알 수 없는 코드: ");
                // 예외 담아서 보내?
                break;
        }
    }

    public void receiveSignType(Protocol protocol) throws IOException {
        SignController signController = new SignController();

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_SIGN_UP_INFO:
                signController.sign(objectOutputStream, objectInputStream, protocol);
                System.out.println("가입 요청");
                break;
            default:
                System.out.println("알수 없는 코드");
                // 예외 담아서 보내?
                break;
        }
    }

    public void receivePersonalInfoEditType(Protocol protocol) throws IOException {
        PersonalInfoEditController personalInfoEditController = new PersonalInfoEditController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_PERSONAL_INFO_REQUEST:
                personalInfoEditController.sendUserInfo();
                System.out.println("유저 정보 요청");
                break;

            case Protocol.CODE_SEND_MODIFY_PERSONAL_INFO:
                personalInfoEditController.modifyUserInfo();
                System.out.println("유저 정보 변경");
                break;
            default:

                break;
        }
    }

    public void receiveSearchReservation(Protocol protocol) throws IOException {
        SearchReservationController searchReservationController = new SearchReservationController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_MY_RESERVATION_REQUEST:
                searchReservationController.sendReservationList();
                break;


            default:

                break;
        }
    }

    public void receiveWrittenReview(Protocol protocol) throws IOException {
        WrittenReviewController writtenReviewController = new WrittenReviewController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_WRITTEN_REVIEW_REQUEST:
                writtenReviewController.sendWrittenReviewList();

                break;

            case Protocol.CODE_SEND_MODIFY_REVIEW:
                writtenReviewController.modifyReview();
                break;
            default:

                break;
        }
    }

    public void receiveStayedHouse(Protocol protocol) throws IOException {
        StayedHouseController stayedHouseController = new StayedHouseController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_STAYED_HOUSE_LIST_REQUEST:
                stayedHouseController.sendStayedHouseList();
                break;

            default:

                break;
        }
    }

    public void receiveSendReview(Protocol protocol) throws IOException {
        SendReviewController sendReviewController = new SendReviewController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_REVIEW:
                sendReviewController.insertReview();
                break;

            default:

                break;
        }
    }

    public void receiveSearchAllHouse(Protocol protocol) throws IOException {
        SearchAllHouseController searchAllHouseController = new SearchAllHouseController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST:
                searchAllHouseController.sendAllHouseList();
                break;

            default:

                break;
        }
    }

    public void receiveSelectHouseViewDetail(Protocol protocol) throws IOException {
        SelectHouseViewDetailController selectHouseViewDetailController = new SelectHouseViewDetailController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SELECT_HOUSE_INFO_REQUEST:
                selectHouseViewDetailController.sendHouseDetailInfo();
                break;

            default:

                break;
        }
    }

    public void receiveRequestReservation(Protocol protocol) throws IOException {
        ReauestReservationController reauestReservationController = new ReauestReservationController();
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_RESERVATION_INFO:
                reauestReservationController.insertReservation();
                break;

            default:

                break;
        }
    }

    //===============================게스트=======================================
    public void receiveHouseRegistration(Protocol protocol) throws IOException {

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_REGISTRATION_HOUSE_INFO:

                break;

            default:

                break;
        }
    }

    public void receiveViewMyHouse(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_MY_HOUSE_LIST:

                break;

            default:

                break;
        }
    }

    public void receiveSetCostPolicy(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_DIVISION_COST_POLICY_HOUSE_LIST:

                break;
            case Protocol.CODE_SEND_WEEKEND_WEEKDAYS_COST_POLICY:

                break;


            default:

                break;
        }
    }

    public void receiveSetDiscountPolicy(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {


            default:

                break;
        }
    }

    public void receiveSetDiscountPolicyOnConsecutiveNights(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS:

                break;
            case Protocol.CODE_APPLY_DISCOUNT_TO_EXISTING_RESERVATIONS:

                break;


            default:

                break;
        }
    }

    public void receiveSetDiscountOnQuantityOrFlat(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_DISCOUNT_ON_QUANTITY_OR_FLAT:

                break;


            default:

                break;
        }
    }

    public void receiveMyHouseInfoEdit(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_EDITED_HOUSE_INFO:

                break;


            default:

                break;
        }
    }

    public void receiveViewHouseReservation(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {


            default:

                break;
        }
    }

    public void receiveReservationAllowOrRefuse(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_WAITING_FOR_RESERVATION_APPROVAL:

                break;
            case Protocol.CODE_REQUEST_RESERVATION_REFUSE_LIST:

                break;

            default:

                break;
        }
    }

    public void receiveReviewManagement(Protocol protocol) throws IOException {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_MOST_RECENT_REVIEW_LIST:

                break;
            case Protocol.CODE_SEND_REPLYING_TO_REVIEW:

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

            case Protocol.CODE_REQUEST_REJECTED_ACCOMMODATION_LIST:
                break;

            case Protocol.CODE_SEND_REJECTED_ACCOMMODATION_LIST:
                break;

            default:
                break;
        }
    }
}
