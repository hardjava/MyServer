package airbnb.controller;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.DiscountPolicyDAO;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.HouseAndDiscountDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class SetDiscountPolicyController {
    Protocol protocol;
    Protocol returnProtocol;
    public SetDiscountPolicyController(Protocol protocol) {
        this.protocol = protocol;
    }

    // 이거 set 하고 원래 예약되있던 것도 할인 적용해줘야 한다. 메소드 하나 만들어서 진행하머ㅕㄴ 될듯? DAO 에서 하자..
    public void setDiscountPolicy() throws IOException {
        System.out.println("HOST - 할인 정책 설정 요청");
        DiscountPolicyDTO discountPolicyDTO = (DiscountPolicyDTO) protocol.getObject();
        DiscountPolicyDAO discountPolicyDAO = new DiscountPolicyDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        discountPolicyDAO.insert(discountPolicyDTO);
        returnProtocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SUCCESS);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tHOST - 할인 정책 설정 승인");
    }

    // 호스트가 할인 정책 설정하려는데
    // 승인되었고 요금정책 정해진 숙소 정보와 할인 정보 가져와야 함
    public void sendApprovedSetFeePolicyHouse() throws IOException {
        System.out.println("HOST - 요금 정책 설정된 숙소 목록 요청");
        UserDTO userDTO = (UserDTO) protocol.getObject();
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<HouseAndDiscountDTO> list;
        list = houseDAO.getApprovedHouseSetFeePolicyAndDiscountInfoByHostId(userDTO.getUserId());

        returnProtocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tHOST - 요금 정책 설정된 숙소 목록 전송");
        // ---------------------------------->> !!! 이전 예약 건에 대해서도 적용해야함 -> 숙박 전꺼 해야함
    }

        private void uploadNightsDiscountPolicy(Object discountPolicyDetails) {
        // 연속 숙박 할인 정책을 데이터베이스에 업데이트
        // ...
    }

    private void applyDiscountToExistingReservations(Object discountPolicyDetails) {
        // 기존 예약에 할인 적용 로직
        // ...
    }

    public void uploadDiscountOnQuantityOrFlat() {
        // 할인 적용 정보를 전달 받고 데이터베이스로 올리는 코드
    }
}
