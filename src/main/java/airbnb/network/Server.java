package airbnb.network;

import airbnb.controller.Handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int SERVER_PORT = 5432;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket;
        Socket socket = null;

        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server Running");
            System.out.println("before socket connect");
            socket = serverSocket.accept();
            System.out.println("after socket connect");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Protocol protocol = (Protocol) objectInputStream.readObject();
                Handler handler = new Handler(objectOutputStream, objectInputStream);

                switch (protocol.getProtocolType()) {
                    case Protocol.TYPE1_LOGIN_REQUEST: // TYPE 이 로그인 요청일때
                        System.out.println("로그인 데이터 수신");
                        handler.receiveLoginRequestType(protocol);
                        break;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
