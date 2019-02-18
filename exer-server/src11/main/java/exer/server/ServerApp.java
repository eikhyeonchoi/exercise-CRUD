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

  static BoardService boardService;
  static LessonService lessonService;
  static MemberService memberService;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888); ){
      System.out.println("서버 실행중");

      boardService = new BoardService();
      boardService.loadData(BOARD_FILE);

      lessonService = new LessonService();
      lessonService.loadData(LESSON_FILE);

      memberService = new MemberService();
      memberService.loadData(MEMBER_FILE);

      while(true) {
        try(Socket socket = serverSocket.accept(); 
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ){
          ServerApp.in = in;
          ServerApp.out = out;
          System.out.println("server : 클라이언트와 연결됨");
          boardService.init(in, out);
          lessonService.init(in, out);
          memberService.init(in, out);

          while(true) {
            String response = in.readUTF();
            System.out.println(response);
            if(response.equalsIgnoreCase("quit")) {
              quit();
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

  public static void quit() throws Exception{
    boardService.saveData(BOARD_FILE);
    memberService.saveData(MEMBER_FILE);
    boardService.saveData(LESSON_FILE);
    out.writeUTF("종료합니다"); out.flush();
  }

} // end of class
