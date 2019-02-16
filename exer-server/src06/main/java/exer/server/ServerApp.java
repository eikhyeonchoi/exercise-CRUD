package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import exer.server.domain.Member;

public class ServerApp {
  static ArrayList<Member> list = new ArrayList<>();
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
          list.clear();

          loop: while(true) {
            String response = in.readUTF();
            System.out.println(response);
            switch(response) {
              case "quit" :  
                quit();
                break loop;
              case "/member/add" :
                add();
                break;
              case "/member/detail" : 
                detail();
                break;
              case "/member/delete" : 
                delete();
                break;
              case "/member/list" : 
                list();
                break;
              case "/member/update" : 
                update();
                break;
              default :
                out.writeUTF("해당 명령어는 지원하지 않습니다");

            } // swtich

            out.flush();

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

  public static void add() throws Exception {
    list.add((Member) in.readObject());
    out.writeUTF("add OK");
  } // add

  public static void detail() throws Exception  {
    int no = in.readInt();
    for(Member member : list) {
      if(member.getNo() == no) {
        out.writeObject(member); 
        return;
      }
    } // for
    
    out.writeObject(null);
  } // detail

  public static void list() throws Exception {
    out.writeUnshared(list);
  } // list

  public static void delete() throws Exception {
    int no = in.readInt();
    
    int index = 0;
    for(Member member : list) {
      if(member.getNo() == no) {
        list.remove(index);
        out.writeUTF("delete OK");
        return;
      }
      index ++;
    }
    out.writeUTF("delete FAIL");
  } // delete

  public static void update() throws Exception {
    Member newMember = (Member) in.readObject();
    
    int index = 0;
    for(Member member : list) {
      if(newMember.getNo() == member.getNo()) {
        list.set(index, newMember);
        out.writeUTF("update OK");
        return;
      }
      index ++;
    } // for
    out.writeUTF("update FAIL");
  } // update

} // end of class
