package airbnb.controller;

public class StayedHouseController {

    Protocol protocol;
    Protocol returnProtocol;

    public StayedHouseController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendStayedHouseList() throws IOException {//클라이언트로 묵었던 숙소리스트 보내는 메소드
        List<CompletedStayDTO> list;
        ReservationDAO reservationDAO = new ReservationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        UserDTO userDTO = (UserDTO)protocol.getObject();
        try{
            list = reservationDAO.getCompletedStayReservationByUserId(userDTO.getUserId());
            returnProtocol = new Protocol(Protocol.CODE_SEND_STAYED_HOUSE_LIST, Protocol.CODE_SUCCESS, list);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (IOException e) {
            returnProtocol = new Protocol(Protocol.CODE_SEND_STAYED_HOUSE_LIST, Protocol.CODE_ERROR, e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }
}
