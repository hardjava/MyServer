package org.example;

import java.util.Scanner;

public class AccommodationReservationApp {
    Scanner sc = new Scanner(System.in);

    public void run() {
        for (; ; ) {
            int enter = getCommand();

            switch (enter) {
                case 1:
                    new LoginHandler().run();
                    break;
                case 2:
                    new SignHandler().run();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please Enter (1) ~ (3)");
                    break;
            }
        }
    }

    private int getCommand() {
        System.out.println("1. Login"); // 로그인
        System.out.println("2. Sign in"); // 회원 가입
        System.out.println("3. Terminate"); // 종료
        System.out.print("enter : ");

        return sc.nextInt();
    }
}
