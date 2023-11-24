package airbnb.controller;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.HouseDAO;
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

    public void setDiscountPolicy() {

    }

    public void sendApprovedSetFeePolicyHouse() throws IOException {
//        UserDTO userDTO = (UserDTO) protocol.getObject();
//        HouseDAO houseDAO = new HouseDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        List<HouseAndDiscountDTO> list;
//
//        returnProtocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SUCCESS, list);
//        MyIOStream.oos.writeObject(returnProtocol);
    }
}
