package airbnb.controller;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.DiscountPolicyDAO;
import airbnb.persistence.dao.HouseDAO;
import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.HouseAndDiscountDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class SetDiscountPolicyController {
    Protocol protocol;
    Protocol returnProtocol;
    public SetDiscountPolicyController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void setDiscountPolicy() throws IOException {
        DiscountPolicyDTO discountPolicyDTO = (DiscountPolicyDTO) protocol.getObject();
        DiscountPolicyDAO discountPolicyDAO = new DiscountPolicyDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        discountPolicyDAO.insert(discountPolicyDTO);
        returnProtocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SUCCESS);
        MyIOStream.oos.writeObject(returnProtocol);
    }

    // 호스트가 할인 정책 설정하려는데
    // 승인되었고 요금정책 정해진 숙소 정보와 할인 정보 가져와야 함
    public void sendApprovedSetFeePolicyHouse() throws IOException {
        UserDTO userDTO = (UserDTO) protocol.getObject();
        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<HouseAndDiscountDTO> list;
        list = houseDAO.getApprovedHouseSetFeePolicyAndDiscountInfoByHostId(userDTO.getUserId());

        returnProtocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
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
