package airbnb.network;

import airbnb.controller.AdminHandler;
import airbnb.controller.GuestHandler;
import airbnb.controller.HostHandler;
import airbnb.controller.ViewAccommodationRegistrationListController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static airbnb.network.Protocol.TYPE_HOUSE_REGISTRATION;

public class Server {
    private final static int SERVER_PORT = 5432;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket = null;

        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server Running");
            System.out.println("before socket connect");
            socket = serverSocket.accept();
            System.out.println("after socket connect");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            MyIOStream myIOStream = new MyIOStream(objectOutputStream, objectInputStream);
            GuestHandler guestHandler = new GuestHandler();
            HostHandler hostHandler = new HostHandler();
            while (true) {
                Protocol protocol = (Protocol) objectInputStream.readObject();

                switch (protocol.getProtocolType()) {
                    case Protocol.TYPE_SIGN_UP:
                        guestHandler.receiveSignType(protocol);
                        break;

                    case Protocol.TYPE_LOGIN: // TYPE 이 로그인 요청일 때
                        guestHandler.receiveLoginRequestType(protocol);
                        break;

                    case Protocol.TYPE_PERSONAL_INFO_EDIT:
                        guestHandler.receivePersonalInfoEditType(protocol);
                        break;

                    case Protocol.TYPE_SEARCH_RESERVATION:
                        guestHandler.receiveSearchReservationType(protocol);
                        break;

                    case Protocol.TYPE_WRITTEN_REVIEW:
                        guestHandler.receiveWrittenReviewType(protocol);
                        break;

                    case Protocol.TYPE_STAYED_HOUSE:
                        guestHandler.receiveStayedHouseType(protocol);
                        break;
                    case Protocol.TYPE_SEND_REVIEW:
                        guestHandler.receiveSendReviewType(protocol);
                        break;

                    case Protocol.TYPE_SEARCH_ALL_HOUSE:
                        guestHandler.receiveSearchAllHouseType(protocol);
                        break;

                    case Protocol.TYPE_SELECT_HOUSE_VIEW_DETAIL:
                        guestHandler.receiveSelectHouseViewDetailType(protocol);
                        break;

                    case Protocol.TYPE_REQUEST_RESERVATION:
                        guestHandler.receiveRequestReservationType(protocol);
                        break;
                    case Protocol.TYPE_FILTER:
                        guestHandler.receiveFilterType(protocol);
                        break;
                        //=================================== 여기까지 게스트

                    case TYPE_HOUSE_REGISTRATION:
                        hostHandler.receiveHouseRegistration(protocol);
                        break;

                    case Protocol.TYPE_VIEW_MY_HOUSE:
                        hostHandler.receiveViewMyHouse(protocol);
                        break;
                    case Protocol.TYPE_SET_COST_POLICY:
                        // ---------------------------->> TYPE_SET_COST_POLICY = 12;
                        // -> CODE_REQUEST_APPROVED_NOT_SET_FEE_POLICY_HOUSE_LIST = 4;
                        // 승인됐지만 요금 설정이 안된 숙박 목록 요청(클라이언트 → 서버)
                        hostHandler.receiveSetCostPolicy(protocol);
                        break;

                    case Protocol.TYPE_SET_DISCOUNT_POLICY:
                        hostHandler.receiveSetDiscountPolicy(protocol);
                        break;

//                    case Protocol.TYPE_SET_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS:
//                        hostHandler.receiveSetDiscountPolicyOnConsecutiveNights(protocol);
//                        break;
//
//                    case Protocol.TYPE_SET_DISCOUNT_ON_QUANTITY_OR_FLAT:
//                        hostHandler.receiveSetDiscountOnQuantityOrFlat(protocol);
//
//                        break;
                    case Protocol.TYPE_MY_HOUSE_INFO_EDIT:
                        hostHandler.receiveMyHouseInfoEdit(protocol);

                        break;

                    case Protocol.TYPE_VIEW_HOUSE_RESERVATION:
                        hostHandler.receiveViewHouseReservation(protocol);

                        break;

                    case Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE:
                        hostHandler.receiveReservationAllowOrRefuse(protocol);

                        break;
                    case Protocol.TYPE_REVIEW_MANAGEMENT:
                        hostHandler.receiveReviewManagement(protocol);
                        break;

//============================ 여기까지 호스트
                    case Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST:
                        AdminHandler adminHandler = new AdminHandler();
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST:
                                adminHandler.receiveViewAccommodationRegistrationList(protocol);
                                System.out.println("숙박 등록 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_APPROVAL_OR_REJECT_INFORMATION:
                                System.out.println("승인 또는 거절 정보 전송 처리");
                                break;
                            case Protocol.CODE_SUCCESS:
                                System.out.println("승인 또는 거절 정보 전송 성공 처리");
                                break;
                            case Protocol.CODE_ERROR:
                                System.out.println("승인 또는 거절 정보 전송 실패 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_CALENDAR_WITH_RESERVATION_DETAILS:
                                System.out.println("예약 상세 정보가 포함된 달력 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;
                    case Protocol.TYPE_MONTHLY_TOTAL_REVENUE_FOR_ACCOMMODATION:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_TOTAL_SALES_FOR_MONTH:
                                System.out.println("월별 총 매출 정보 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;
                    default:
                        System.out.println("알 수 없는 프로토콜 타입: " + protocol.getProtocolType());
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
