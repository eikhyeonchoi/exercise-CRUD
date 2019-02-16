package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import exer.server.domain.Member;

public class ServerApp {
  static ObjectInputStream in = null;
  static ObjectOutputStream out = null;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888); ){
      System.out.println("서버 실행중");

      while(true) {
        try(Socket socket = serverSocket.accept(); 
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ){
          ServerApp.in = in;
          ServerApp.out = out;
          System.out.println("server : 클라이언트와 연결됨");

          ArrayList<AbstractCommand> command = new ArrayList<AbstractCommand>();
          command.add(new BoardCommand(in, out));
          command.add(new MemberCommand(in, out));
          command.add(new LessonCommand(in, out));

          while(true) {
            String response = in.readUTF();
            System.out.println(response);
            if(response.equalsIgnoreCase("quit")) {
              out.writeUTF("종료합니다"); out.flush();
              break;
            }

            if(response.startsWith("/board/")) {
              command.get(0).service(response);

            } else if(response.startsWith("/member/")) {
              command.get(1).service(response);

            } else if(response.startsWith("/lesson/")) {
              command.get(2).service(response);
            }



          } // while

          System.out.println("server : 클라이언트와 연결을 끊었음");
        } catch (Exception e) {
          System.out.println("통신중 오류 발생 " + e.getMessage());
        }
      } // while
    } catch (Exception e) {
      e.printStackTrace();
    }

  } // main

  public static void quit() throws Exception {
    out.writeUTF("종료합니다");
    out.flush();
  } // quit


} // end of class
