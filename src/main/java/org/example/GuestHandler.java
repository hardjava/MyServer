package org.example;

import java.util.Scanner;

public class GuestHandler {

    private void getMyPage() { // 마이페이지 선택 시, 나오는 목록
        System.out.println("개인정보 딸깍");
        System.out.println("1. 숙박 완료 보기");
        System.out.println("2. 숙박 예약 보기");
        System.out.println("3. 뒤로 가기");
    }

    private void sss() { // 숙박 완료 화면
        // 리스트로 불러와야함. 데이터베이스에서
        /*
        List <ReservationDTO> reservationList = new List<>();

        for (int i = 1; i <= reservationList.length(); i ++) {
            System.out.println(i + ". " + reservationList.get(i - 1).getAccommodationName());
        }성
        System.out.println("1.
         */
    }

    private void getProfile() {    // 개인정보
    }

    private void setProfile() {     // 개인정보 설정
    }

    void all(){
        System.out.println("- 마이페이지");
        System.out.println("1. 개인 정보 수정");///////마이페이지 클릭 후 1.개인정보수정 2.예약현황조회 3.리뷰,별점 등록
        System.out.println("이름 :");
        System.out.println("전화번호 :");
        System.out.println("생년월일 :");

        System.out.println("2. 예약 현황 조회");///////
        System.out.println("1. 완료된 숙박");
        System.out.println("2. 예약 대기 숙소");
        System.out.println("3. 예약된 숙소");

        System.out.println("3. 리뷰, 별점 등록");/////// 3. 리뷰, 별점 등록 을 누르면 1.작성한 리뷰, 2.완료된 숙소 리스트 나옴
        System.out.println("1. 작성한 리뷰");
        // 작성한 리뷰 선택하면 작성된 숙소 리뷰 리스트업
        // 리스트업 된 작성된 숙소 리뷰 중 하나를 선택하면 "뒤로가기"와 "리뷰 수정" 버튼 등장
        System.out.println("2. 숙박 완료 리스트");
        // 숙박 완료 리스트 선택시 완료된 숙박 리스트업
        // 리스트업 된 완료된 숙박 시설 중 하나를 선택하면 "별점"과 "후기" 일괄작성 그리고 "뒤로가기"
        System.out.println("###########리뷰 작성###########");
        System.out.println("별점을 입력해주세요(1~5점) : ");
        System.out.println("후기를 작성해주세요(500자 이내) : ");


        System.out.println("- 숙소 목록 보기");
        System.out.println("1. 전체 목록 조회");
        System.out.println("2. 숙소 필터링 검색");//필터링에 대한 자세한 정보 필요
        //목록이 나오면 그중 하나를 선택했을 시 "상세보기"로 넘어감
        System.out.println("숙소명 :");
        System.out.println("숙소 소개 :");
        System.out.println("편의시설 :");
        System.out.println("숙박 요금 :");
        System.out.println("예약가능 일자 :");
        System.out.println("후기 :");
        System.out.println("1. 예약 신청");
        System.out.println("2. 뒤로가기");
        //예약 신청시 if 게스트의 일정이 중복된 상황이라면 메시지 띄우기
        System.out.println("게스트님의 여행 일정이 중복되어 예약이 불가합니다.");
        System.out.println("예약을 등록합니다");
        System.out.println("총 요금 : 0000원");
    }
}
// 초기 화면 -> 숙소 검색
// 마이페이지 -> 예약 현황 및 프로필 조회 -> 프로필 수정, 숙박 완료, 숙박 예약