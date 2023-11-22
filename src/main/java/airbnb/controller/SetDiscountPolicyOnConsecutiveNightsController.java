package airbnb.controller;
import airbnb.network.Protocol;
public class SetDiscountPolicyOnConsecutiveNightsController {
    Protocol protocol;

    public SetDiscountPolicyOnConsecutiveNightsController(Protocol protocol) {
        this.protocol = protocol;
    }

    private void uploadNightsDiscountPolicy(Object discountPolicyDetails) {
        // 연속 숙박 할인 정책을 데이터베이스에 업데이트
        // ...
    }

    private void applyDiscountToExistingReservations(Object discountPolicyDetails) {
        // 기존 예약에 할인 적용 로직
        // ...
    }
}
