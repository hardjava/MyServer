package airbnb.controller;
import airbnb.network.Protocol;

public class MyHouseInfoEditController {
    Protocol protocol;
    public MyHouseInfoEditController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void uploadEditedHouseInfo(){
        //수정된 숙박 정보 전달받고 데이터베이스에 등록(클라이언트 → 서버)
    }
}
