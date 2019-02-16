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

      add(new Member(111, "첫번째 사람"));
      add(new Member(222, "두번째 사람"));
      add(new Member(333, "익콩이"));
      detail(111);
      System.out.println("--------------");
      list();
      System.out.println("--------------");
      delete(222);
      update(new Member(333, "변경된 익콩이"));
      list();
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

  public static void add(Member member) throws Exception{
    out.writeUTF("/member/add"); out.flush();
    out.writeObject(member);
    String response = in.readUTF();
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // add

  public static void detail(int no) throws Exception{
    out.writeUTF("/member/detail"); out.flush();
    out.writeInt(no); out.flush();
    Member member = (Member) in.readObject();
    if(member == null) {
      System.out.println("FAIL");
    } else System.out.println(member);
  } // detail

  @SuppressWarnings("unchecked")
  public static void list() throws Exception {
    out.writeUTF("/member/list"); out.flush();
    ArrayList<Member> list = (ArrayList<Member>) in.readObject();
    for(Member member : list) {
      System.out.println(member);
    }
    
  } // list
  

  public static void delete(int no) throws Exception {
    out.writeUTF("/member/delete"); out.flush();
    out.writeInt(no); out.flush();
    
    String response = in.readUTF();
    
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");

  } // delete
  
  public static void update(Member newMember) throws Exception {
    out.writeUTF("/member/update"); out.flush();
    out.writeObject(newMember);
    
    String response = in.readUTF();
    
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
    
    
    
  } // update

  
} // end of class















