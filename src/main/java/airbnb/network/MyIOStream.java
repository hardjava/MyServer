package airbnb.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyIOStream {
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;

    public MyIOStream(ObjectOutputStream oos, ObjectInputStream ois) {
        MyIOStream.oos = oos;
        MyIOStream.ois = ois;
    }
}
