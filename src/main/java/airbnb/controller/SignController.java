package airbnb.controller;

import airbnb.exception.ExsistIdException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.UserDAO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SignController {

    Protocol protocol;
    public SignController(Protocol protocol) {
        this.protocol = protocol;
    }
    public void sign() throws IOException {
        UserDTO userDTO = (UserDTO) protocol.getObject();
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        //회원가입
        try {
            userDAO.insertUser(userDTO);
            protocol.setProtocolCode(Protocol.CODE_SUCCESS); // 회원가입 성공
          MyIOStream.oos.writeObject(protocol);
        } catch (ExsistIdException eie) {
            protocol.setProtocolCode(Protocol.CODE_ERROR);
            protocol.setObject(eie.getMessage());
            MyIOStream.oos.writeObject(protocol);
        }
    }
}
