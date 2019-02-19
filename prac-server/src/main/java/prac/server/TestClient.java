package prac.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
  public static void main(String[] args) {

    try (Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ){
      System.out.println("서버와 연결됨");
      
      out.writeObject(new Board(1, "비트캠프"));
      out.flush();
      
      System.out.println(in.readUTF());
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
