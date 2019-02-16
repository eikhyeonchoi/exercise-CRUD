package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import exer.server.domain.Member;

public class ServerTest {
  public static void main(String[] args) {

    try(Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ){
      System.out.println("client : 서버와 연결됨");
      
      out.writeObject(new Member(1, "최익현")); out.flush();
      System.out.println(in.readUTF());
      
      out.writeObject(new Member(2, "모나유우키")); out.flush();
      System.out.println(in.readUTF());
      
      System.out.println(in.readObject());
      System.out.println(in.readObject());
      
      

      
      
      System.out.println("client : 서버와 연결을 끊었음");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  } // main
} // end of class
