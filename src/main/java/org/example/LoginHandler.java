package org.example;

import java.util.Scanner;

public class LoginHandler {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("\n\t\t<Login>");

        System.out.print("\t\tID : ");
        String id = sc.next();
        System.out.print("\t\tPassword : ");
        String password = sc.next();

        int userType = verifyUser(id, password);

        switch (userType) {
            case 1:
                new GuestHandler().run();
                break;
            case 2:
                new HostHandler().run();
                break;
            case 3:
                new AdminHandler().run();
                break;
            default:
                break;
        }
    }

    public int verifyUser(String id, String password) { //id, pw 존재하는지 여부와 type
        return -1;
    }
}