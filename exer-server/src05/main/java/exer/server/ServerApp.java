package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApp {
  public static void main(String[] args) {

    ArrayList<Object> list = new ArrayList<Object>();
    try (ServerSocket serverSocket = new ServerSocket(8888); ){
      System.out.println("서버 실행중");
       try(Socket socket = serverSocket.accept(); 
           ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
           ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ){
         System.out.println("server : 클라이언트와 연결됨");
         
         list.add(in.readObject());
         out.writeUTF("OK"); out.flush();
         
         list.add(in.readObject());
         out.writeUTF("OK"); out.flush();
         
         out.writeObject(list.get(0)); out.flush();
         out.writeObject(list.get(1)); out.flush();
       
         
         
         System.out.println("server : 클라이언트와 연결을 끊었음");
       } catch (Exception e) {
         e.printStackTrace();
       }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
  } // main
} // end of class
