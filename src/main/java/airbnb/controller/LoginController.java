package airbnb.controller;

import airbnb.exception.InvalidIdException;
import airbnb.exception.InvalidPwdException;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.LoginDAO;
import airbnb.persistence.dto.LoginDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    public void login(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, Protocol protocol) throws IOException, ClassNotFoundException {
        LoginDTO loginDTO = (LoginDTO) protocol.getObject();
        LoginDAO loginDAO = new LoginDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        protocol.setProtocolType(Protocol.TYPE2_LOGIN_RESPONSE);

        try {
            UserDTO userDTO = loginDAO.login(loginDTO);
            protocol.setProtocolCode(Protocol.CODE_TYPE2_LOGIN);
            protocol.setObject(userDTO);
            objectOutputStream.writeObject(protocol);
        } catch (InvalidIdException iie) {
            protocol.setProtocolCode(Protocol.CODE_ERROR);
            protocol.setObject(iie.getMessage());
            objectOutputStream.writeObject(protocol);

        } catch (InvalidPwdException ipe) {
            protocol.setProtocolCode(Protocol.CODE_ERROR);
            protocol.setObject(ipe.getMessage());
            objectOutputStream.writeObject(protocol);

        }
    }
}
