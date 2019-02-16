package java.exer.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerTest {
  public static void main(String[] args) {

    try(Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream()); ){
      System.out.println("client : 서버와 연결됨");
      
      
      
      
      
      System.out.println("client : 서버와 연결을 끊었음");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  } // main
} // end of class
