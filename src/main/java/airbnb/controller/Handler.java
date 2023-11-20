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
            case Protocol.CODE_UNDEFINED: // TYPE 이 로그인 요청이고 CODE가 0 일때
                protocol.setProtocolType(Protocol.TYPE2_LOGIN_RESPONSE); // Protocol 의 TYPE 을 로그인 응답 으로 바꾸어서
                objectOutputStream.writeObject(protocol); // 로그인 응답 Protocol 보냄
                break;
            case Protocol.CODE_TYPE1_LOGIN:
                LoginController loginController = new LoginController();
                loginController.login(objectOutputStream, objectInputStream, protocol);
                break;
        }
    }
}
