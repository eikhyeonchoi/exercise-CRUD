package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import exer.server.domain.Member;

public class ServerApp {
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(8888); ){
      System.out.println("서버 실행중");
       try(Socket socket = serverSocket.accept(); 
           ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
           ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ){
         System.out.println("server : 클라이언트와 연결됨");
       
         // 다형성
         Object obj = in.readObject();
         
         // type casting 안해도 되네 ?
         System.out.println(obj);
         
         
         out.writeUTF("객체를 받았습니다");
         
         System.out.println("server : 클라이언트와 연결을 끊었음");
       } catch (Exception e) {
         e.printStackTrace();
       }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
  } // main
} // end of class
