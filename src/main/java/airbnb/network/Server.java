package airbnb.network;

import airbnb.controller.Handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            Handler handler = new Handler();

            while (true) {
                System.out.println("goood");
                Protocol protocol = (Protocol) objectInputStream.readObject();

                switch (protocol.getProtocolType()) {
                    case Protocol.TYPE_SIGN_UP:
                        handler.receiveSignType(protocol);
                        break;

                    case Protocol.TYPE_LOGIN: // TYPE 이 로그인 요청일 때
                        handler.receiveLoginRequestType(protocol);
                        break;

                    case Protocol.TYPE_PERSONAL_INFO_EDIT:
                        handler.receivePersonalInfoEditType(protocol);
                        break;

                    case Protocol.TYPE_SEARCH_RESERVATION:
                        handler.receiveSearchReservationType(protocol);
                        break;

                    case Protocol.TYPE_WRITTEN_REVIEW:
                        handler.receiveWrittenReviewType(protocol);
                        break;

                    case Protocol.TYPE_STAYED_HOUSE:
                        handler.receiveStayedHouseType(protocol);
                        break;
                    case Protocol.TYPE_SEND_REVIEW:
                        handler.receiveSendReviewType(protocol);
                        break;

                    case Protocol.TYPE_SEARCH_ALL_HOUSE:
                        handler.receiveSearchAllHouseType(protocol);
                        break;

                    case Protocol.TYPE_SELECT_HOUSE_VIEW_DETAIL:
                        handler.receiveSelectHouseViewDetailType(protocol);
                        break;

                    case Protocol.TYPE_REQUEST_RESERVATION:
                        handler.receiveRequestReservationType(protocol);
                        break;
                    case Protocol.TYPE_FILTER:
                        handler.receiveFilterType(protocol);
                        //=================================== 여기까지 게스트

                    case Protocol.TYPE_HOUSE_REGISTRATION:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_REGISTRATION_HOUSE_INFO:
                                System.out.println("숙박 시설 등록 정보 전송 처리");
                                break;
                            case Protocol.CODE_SUCCESS:
                                System.out.println("숙박 시설 등록 성공 처리");
                                break;
                            case Protocol.CODE_ERROR:
                                System.out.println("숙박 시설 등록 실패 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_VIEW_MY_HOUSE:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_MY_HOUSE_LIST:
                                System.out.println("내 숙박 시설 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_MY_HOUSE_LIST:
                                System.out.println("내 숙박 시설 목록 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;
                    case Protocol.TYPE_SET_COST_POLICY:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_DIVISION_COST_POLICY_HOUSE_LIST:
                                System.out.println("요금 정책(분할) 설정 처리");
                                break;
                            case Protocol.CODE_SEND_DIVISION_COST_POLICY:
                                System.out.println("요금 정책(분할) 설정 처리");
                                break;
                            case Protocol.CODE_SEND_WEEKEND_WEEKDAYS_COST_POLICY:
                                System.out.println("주말 및 평일 요금 정책 설정 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_SET_DISCOUNT_POLICY:
                        // 메서드 추가
                        break;

                    case Protocol.TYPE_SET_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS:
                                System.out.println("연속 숙박 할인 정책 설정 처리");
                                break;
                            case Protocol.CODE_APPLY_DISCOUNT_TO_EXISTING_RESERVATIONS:
                                System.out.println("기존 예약에 할인 적용 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_SET_DISCOUNT_ON_QUANTITY_OR_FLAT:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_DISCOUNT_ON_QUANTITY_OR_FLAT:
                                System.out.println("정량 / 정률 할인 정책 설정 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;
                    case Protocol.TYPE_MY_HOUSE_INFO_EDIT:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_SEND_EDITED_HOUSE_INFO:
                                System.out.println("수정된 숙박 시설 정보 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_VIEW_HOUSE_RESERVATION:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_PRINT_HOUSE_RESERVATION:
                                System.out.println("숙박 시설 예약 현황 출력 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

                    case Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_WAITING_FOR_RESERVATION_APPROVAL:
                                System.out.println("예약 승인 대기 요청 처리");
                                break;
                            case Protocol.CODE_SEND_WAITING_FOR_RESERVATION_APPROVAL:
                                System.out.println("예약 승인 대기 정보 전송 처리");
                                break;
                            case Protocol.CODE_REQUEST_RESERVATION_REFUSE_LIST:
                                System.out.println("거부된 예약 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_RESERVATION_REFUSE_LIST:
                                System.out.println("거부된 예약 목록 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;
                    case Protocol.TYPE_REVIEW_MANAGEMENT:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_MOST_RECENT_REVIEW_LIST:
                                System.out.println("최근 리뷰 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_MOST_RECENT_REVIEW_LIST:
                                System.out.println("최근 리뷰 목록 전송 처리");
                                break;
                            case Protocol.CODE_SEND_REPLYING_TO_REVIEW:
                                System.out.println("리뷰에 대한 답변 전송 처리");
                                break;
                            default:
                                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                                break;
                        }
                        break;

//============================ 여기까지 호스트
                    case Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST:
                        switch (protocol.getProtocolCode()) {
                            case Protocol.CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST:
                                System.out.println("숙박 등록 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_ACCOMMODATION_REGISTRATION_LIST:
                                System.out.println("숙박 등록 목록 전송 처리");
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
                            case Protocol.CODE_REQUEST_REJECTED_ACCOMMODATION_LIST:
                                System.out.println("거절된 숙박 목록 요청 처리");
                                break;
                            case Protocol.CODE_SEND_REJECTED_ACCOMMODATION_LIST:
                                System.out.println("거절된 숙박 목록 전송 처리");
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
