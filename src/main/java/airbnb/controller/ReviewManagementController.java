package airbnb.controller;
import airbnb.network.Protocol;

public class ReviewManagementController {
    Protocol protocol;

    public ReviewManagementController(Protocol protocol) {
        this.protocol = protocol;
    }

    public void sentMostRecentReviewList() {
        //최근순 리뷰 리스트를 요청받고 데이터베이스에서 받아서 전달(클라이언트→서버)
    }

    public void uploadReplyingToReview() {
        //리뷰 답글 작성 정보를 전달받고 데이터베이스로 업로드(클라이언트 → 서버)
    }
}