package airbnb.controller;
import airbnb.network.Protocol;
public class SetCostPolicyController {
    Protocol protocol;

    public SetCostPolicyController(Protocol protocol) {
        this.protocol = protocol;
    }


    public void sendHouseList(){// 클라이언트로 가격정책이 적용된 숙소, 적용되지 않은 숙소를 구분하여 숙소 리스트를 전달하는 메소드

    }

    public void insertCostPolicy(){// 클라이언트로 부터 받은 가격정책을 설정하는 메소드

    }
}
