package airbnb.controller;

import airbnb.network.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Handler {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Handler(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void receiveLoginRequestType(Protocol protocol) throws IOException, ClassNotFoundException { // 로그인 request 를 받았을 때 실행
        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_LOGIN_REQUEST:
                LoginController loginController = new LoginController();
                loginController.login(objectOutputStream, objectInputStream, protocol);
                System.out.println("로그인 요청");
                break;
            default:
                System.out.println("알 수 없는 코드: " + protocol.getProtocolCode());
                break;
        }
    }
}
