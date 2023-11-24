package airbnb.controller;

import airbnb.network.Protocol;

import java.io.IOException;

public class HostHandler {
       public void receiveHouseRegistration(Protocol protocol) throws IOException {
        HouseRegistrationController houseRegistrationController = new HouseRegistrationController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_REGISTRATION_HOUSE_INFO:
                houseRegistrationController.houseRegistration();
                break;

            default:

                break;
        }
    }

    public void receiveViewMyHouse(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_MY_HOUSE_LIST:

                break;

            default:

                break;
        }
    }

    public void receiveSetCostPolicy(Protocol protocol) throws IOException {
            SetCostPolicyController setCostPolicyController = new SetCostPolicyController(protocol);
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_DIVISION_COST_POLICY_HOUSE_LIST:

                break;
            case Protocol.CODE_SEND_WEEKEND_WEEKDAYS_COST_POLICY:
                setCostPolicyController.insertCostPolicy();
                break;
            case Protocol.CODE_REQUEST_APPROVED_NOT_SET_FEE_POLICY_HOUSE_LIST:
                setCostPolicyController.sendNotSetFeePolicyHouseList();
                break;

            default:

                break;
        }
    }

    public void receiveSetDiscountPolicy(Protocol protocol) {
        switch (protocol.getProtocolCode()) {


            default:

                break;
        }
    }

    public void receiveSetDiscountPolicyOnConsecutiveNights(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS:

                break;
            case Protocol.CODE_APPLY_DISCOUNT_TO_EXISTING_RESERVATIONS:

                break;


            default:

                break;
        }
    }

    public void receiveSetDiscountOnQuantityOrFlat(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_DISCOUNT_ON_QUANTITY_OR_FLAT:

                break;


            default:

                break;
        }
    }

    public void receiveMyHouseInfoEdit(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SEND_EDITED_HOUSE_INFO:

                break;


            default:

                break;
        }
    }

    public void receiveViewHouseReservation(Protocol protocol) {
        switch (protocol.getProtocolCode()) {


            default:

                break;
        }
    }

    public void receiveReservationAllowOrRefuse(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_WAITING_FOR_RESERVATION_APPROVAL:

                break;
            case Protocol.CODE_REQUEST_RESERVATION_REFUSE_LIST:

                break;

            default:

                break;
        }
    }

    public void receiveReviewManagement(Protocol protocol) {
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_REQUEST_MOST_RECENT_REVIEW_LIST:

                break;
            case Protocol.CODE_SEND_REPLYING_TO_REVIEW:

                break;

            default:

                break;
        }
    }
}
