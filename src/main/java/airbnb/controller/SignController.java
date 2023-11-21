package airbnb.controller;

import airbnb.exception.ExsistIdException;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.UserDAO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SignController {
    public void sign(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, Protocol protocol) throws IOException {
        UserDTO userDTO = (UserDTO) protocol.getObject();
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        //회원가입
        try {
            userDAO.insertUser(userDTO);
            protocol.setProtocolCode(Protocol.CODE_SIGN_UP_SUCCESS); // 회원가입 성공
            objectOutputStream.writeObject(protocol);
        } catch (ExsistIdException eie) {
            protocol.setProtocolCode(Protocol.CODE_SIGN_UP_FAIL);
            protocol.setObject(eie.getMessage());
            objectOutputStream.writeObject(protocol);
        }

    }
}
