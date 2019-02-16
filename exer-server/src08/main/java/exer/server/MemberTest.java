package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Member;

public class MemberTest {
  ObjectOutputStream out = null;
  ObjectInputStream in = null;

  public MemberTest(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  public void test() throws Exception {
    add(new Member(111, "첫번째 게시물"));
    add(new Member(222, "두번째 게시물"));
    add(new Member(333, "익콩이 게시물"));
    detail(111);
    System.out.println("--------------");
    list();
    System.out.println("--------------");
    delete(222);
    update(new Member(333, "변경된 익콩이"));
    list();
  } // service


  public void add(Member member) throws Exception{
    out.writeUTF("/member/add"); out.flush();
    out.writeObject(member);
    String response = in.readUTF();
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // add

  public void detail(int no) throws Exception{
    out.writeUTF("/member/detail"); out.flush();
    out.writeInt(no); out.flush();
    Member member = (Member) in.readObject();
    if(member == null) {
      System.out.println("FAIL");
    } else System.out.println(member);
  } // detail

  @SuppressWarnings("unchecked")
  public void list() throws Exception {
    out.writeUTF("/member/list"); out.flush();
    ArrayList<Member> list = (ArrayList<Member>) in.readObject();
    for(Member member : list) {
      System.out.println(member);
    }

  } // list


  public void delete(int no) throws Exception {
    out.writeUTF("/member/delete"); out.flush();
    out.writeInt(no); out.flush();

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");

  } // delete

  public void update(Member newMember) throws Exception {
    out.writeUTF("/member/update"); out.flush();
    out.writeObject(newMember);

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");

  } // update


} // end of class















