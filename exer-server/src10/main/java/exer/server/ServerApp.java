package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import exer.server.service.BoardService;
import exer.server.service.LessonService;
import exer.server.service.MemberService;

public class ServerApp {
  static ObjectInputStream in = null;
  static ObjectOutputStream out = null;
  static final String BOARD_FILE = "board.bin";
  static final String MEMBER_FILE = "member.bin";
  static final String LESSON_FILE = "lesson.bin";

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

          BoardService boardService = new BoardService(in, out);
          LessonService lessonService = new LessonService(in, out);
          MemberService memberService = new MemberService(in, out);
          boardService.loadData(BOARD_FILE);
          memberService.loadData(MEMBER_FILE);
          lessonService.loadData(LESSON_FILE);

          while(true) {
            String response = in.readUTF();
            System.out.println(response);
            if(response.equalsIgnoreCase("quit")) {
              boardService.saveData(BOARD_FILE);
              memberService.saveData(MEMBER_FILE);
              boardService.saveData(LESSON_FILE);
              out.writeUTF("종료합니다");
              out.flush();
              break;
            }
            if(response.startsWith("/board/")) {
              boardService.service(response);
            } else if(response.startsWith("/member/")) {
              memberService.service(response);
            } else if(response.startsWith("/lesson/")) {
              lessonService.service(response);
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

} // end of class
