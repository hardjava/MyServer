package org.example;

import persistence.MyBatisConnectionFactory;
import persistence.dao.UserDAO;
import persistence.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class LoginHandler {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("\n\t\t<Login>");

        System.out.print("\t\tID : ");
        String id = sc.next();
        System.out.print("\t\tPassword : ");
        String password = sc.next();

        UserDTO userDTO = verifyUser(id, password);

        if (userDTO == null) {
            System.out.println("You have entered an incorrect ID or password.\n" +
                    "Please check what you have entered again.");
        } else {
            int userType = userDTO.getRoleID();

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
    }

    public UserDTO verifyUser(String id, String password) { //id, pw 존재하는지 여부와 type
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<UserDTO> userDTOS = userDAO.selectAll();

        for (UserDTO userDTO : userDTOS) {
            if (userDTO.getLoginID().equals(id) && userDTO.getLoginPWD().equals(password)) {
                return userDTO;
            }
        }

        return null;
    }
}