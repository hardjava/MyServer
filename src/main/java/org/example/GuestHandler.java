package org.example;

import java.util.Scanner;

public class GuestHandler {
    Scanner sc = new Scanner(System.in);

    public void run() {
        int enter = getCommand();
    }

    private int getCommand() {
        System.out.println("1. 숙소 검색");
        System.out.println("2. 숙소 목록");
        System.out.println("3. 마이 페이지");
        System.out.println("4. 로그아웃");

        return sc.nextInt();
    }

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
}
// 초기 화면 -> 숙소 검색
// 마이페이지 -> 예약 현황 및 프로필 조회 -> 프로필 수정, 숙박 완료, 숙박 예약