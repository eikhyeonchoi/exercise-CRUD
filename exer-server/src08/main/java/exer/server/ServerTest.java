package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import exer.server.domain.Member;

public class ServerTest {
  static ObjectOutputStream out = null;
  static ObjectInputStream in = null;

  public static void main(String[] args) {
    try(Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ){
      ServerTest.in = in;
      ServerTest.out = out;
      System.out.println("client : 서버와 연결됨");

      BoardTest boardTest = new BoardTest(out, in);
      MemberTest memberTest = new MemberTest(out, in);
      LessonTest lessonTest = new LessonTest(out, in);

      boardTest.test();
      memberTest.test();
      lessonTest.test();

      quit();

      System.out.println("client : 서버와 연결을 끊었음");
    } catch (Exception e) {
      e.printStackTrace();
    }

  } // main
  public static void quit() throws Exception{
    out.writeUTF("quit"); out.flush();
    System.out.println(in.readUTF());
  } // quit


} // end of class















