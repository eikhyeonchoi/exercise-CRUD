package java.exer.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(8888); ){
      System.out.println("서버 실행중");
       try(Socket socket = serverSocket.accept(); 
           DataInputStream in = new DataInputStream(socket.getInputStream());
           DataOutputStream out = new DataOutputStream(socket.getOutputStream()); ){
         System.out.println("server : 클라이언트와 연결됨");
         
         
         System.out.println("server : 클라이언트와 연결을 끊었음");
       } catch (Exception e) {
         e.printStackTrace();
       }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
  } // main
} // end of class
