package airbnb.controller;

import airbnb.exception.ExsistIdException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.ReviewDAO;
import airbnb.persistence.dto.ReviewDTO;

import java.io.IOException;

public class SendReviewController {
    Protocol protocol;
    Protocol returnProtocol;
    public SendReviewController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void insertReview() throws IOException {// 별점과 리뷰를 등록 하는 메소드
        ReviewDAO reviewDAO = new ReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewDTO reviewDTO = (ReviewDTO) protocol.getObject();

        try {
            reviewDAO.insertReview(reviewDTO);
            returnProtocol = new Protocol(Protocol.TYPE_SEND_REVIEW, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(protocol);
        } catch (ExsistIdException e) {
            returnProtocol = new Protocol(Protocol.TYPE_SEND_REVIEW, Protocol.CODE_ERROR);
            protocol.setObject(e.getMessage());
            MyIOStream.oos.writeObject(protocol);
        }
    }
}
