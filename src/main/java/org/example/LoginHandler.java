package org.example;

import airbnb.network.RoleType;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.UserDAO;
import airbnb.persistence.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class LoginHandler {
    /*
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
            RoleType userType = userDTO.getRole();

            switch (userType) {
                case GUEST:
                    new GuestHandler().run();
                    break;
                case HOST:
                    new HostHandler().run();
                    break;
                case ADMIN:
                    new AdminHandler().run();
                    break;
                default:
                    break;
            }
        }
    }


    public UserDTO verifyUser(String id, String password) { //id, pw 존재하는지 여부와 type
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<UserDTO> userDTOS = userDAO.getAll();

        for (UserDTO userDTO : userDTOS) {
            if (userDTO.getLogin_id().equals(id) && userDTO.getLogin_pwd().equals(password)) {
                return userDTO;
            }
        }

        return null;
    }
*/

}