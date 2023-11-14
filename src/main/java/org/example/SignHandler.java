package org.example;

import java.util.Scanner;

public class SignHandler {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.print("Enter New ID : ");
        String newID = sc.next();
        System.out.print("Enter New Passwd : ");
        String newPd = sc.next();
    }
}

    /*
    public boolean isUserExist(String newID, String newPasswd) {
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<UserDTO> userDTOS = userDAO.selectAll();


    }

    public void addUser() { // return type 고민즁


    }
}

*/