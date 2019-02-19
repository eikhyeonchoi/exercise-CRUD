package prac.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(8888); ) {
      System.out.println("서버실행");
      
      try (Socket socket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); 
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());){
        
        System.out.println(in.readObject());
        out.writeUTF("잘받음"); 
        out.flush();
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    
  }
}
