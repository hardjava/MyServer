package airbnb.controller;

import airbnb.exception.WrongBirthdayException;
import airbnb.exception.WrongPhoneNumberException;
import airbnb.exception.WrongUserNameException;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.MyBatisConnectionFactory;
import airbnb.persistence.dao.UserDAO;
import airbnb.persistence.dto.ModifyBirthdayDTO;
import airbnb.persistence.dto.ModifyPhoneNumberDTO;
import airbnb.persistence.dto.ModifyUserNameDTO;

import java.io.IOException;

public class PersonalInfoEditController {
    Protocol protocol;
    Protocol returnProtocol;

    public PersonalInfoEditController(Protocol protocol) {
        this.protocol = protocol;
    }



    }

    public void modifyUserPhoneNumberInfo() throws IOException {//유저전화번호를 수정하는 메소드
        ModifyPhoneNumberDTO modifyPhoneNumberDTO = (ModifyPhoneNumberDTO) protocol.getObject();
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        try {
            userDAO.updateUserPhone(modifyPhoneNumberDTO);
            returnProtocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (WrongPhoneNumberException wpe) {
            returnProtocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_ERROR, wpe.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }

    public void modifyUserBirthdayInfo() throws IOException {//유저 생년월일을 수정하는 메소드
        ModifyBirthdayDTO modifyBirthdayDTO = (ModifyBirthdayDTO) protocol.getObject();
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        try {
            userDAO.updateUserBirthday(modifyBirthdayDTO);
            returnProtocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_SUCCESS);
            MyIOStream.oos.writeObject(returnProtocol);
        } catch (WrongBirthdayException wbe) {
            returnProtocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_ERROR, wbe.getMessage());
            MyIOStream.oos.writeObject(returnProtocol);
        }
    }

}
