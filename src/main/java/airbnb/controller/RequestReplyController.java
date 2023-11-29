package airbnb.controller;

import airbnb.exception.ExistReivewException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.ReplyDAO;
import airbnb.persistence.dao.ReviewDAO;
import airbnb.persistence.dto.ReplyDTO;
import airbnb.persistence.dto.ReviewCheckDTO;
import airbnb.persistence.dto.ReviewDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class RequestReplyController {
    Protocol protocol;
    Protocol returnProtocol;

    public RequestReplyController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sendReviewNotReply() throws IOException {
        System.out.println("HOST - 대댓글 작성 안된 리뷰 목록 요청");
        List<ReviewCheckDTO> list;
        UserDTO userDTO = (UserDTO) protocol.getObject();
        ReviewDAO reviewDAO = new ReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        list = reviewDAO.getReviewNotReply(userDTO.getUserId());

        returnProtocol = new Protocol(Protocol.TYPE_REVIEW_MANAGEMENT, Protocol.CODE_SUCCESS, list);
        MyIOStream.oos.writeObject(returnProtocol);
        System.out.println("\tHOST - 대댓글 작성 안된 리뷰 목록 전송");
    }

    public void requestReply() throws IOException {
        System.out.println("HOST - 대댓글 작성 요청");
        ReplyDTO replyDTO = (ReplyDTO) protocol.getObject();
        ReplyDAO replyDAO = new ReplyDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        try {
            replyDAO.insert(replyDTO);
            returnProtocol = new Protocol(Protocol.TYPE_REVIEW_MANAGEMENT, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
            System.out.println("\tHOST - 대댓글 작성 승인");
        } catch (ExistReivewException e) {
            returnProtocol = new Protocol(Protocol.TYPE_REVIEW_MANAGEMENT, Protocol.CODE_ERROR, e.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
            System.out.println("\tHOST - 대댓글 작성 거절");
        }
    }
}
