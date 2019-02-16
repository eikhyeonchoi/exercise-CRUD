package exer.server.service;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Member;

public class MemberService extends AbstractService{
  ArrayList<Member> list = new ArrayList<Member>();

  public MemberService(ObjectInputStream in, ObjectOutputStream out) {
    super(in, out);
  }
  
  @Override
  public void service(String response) throws Exception{
    switch(response) {
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
  } // main

  
  public void add() throws Exception {
    list.add((Member) in.readObject());
    out.writeUTF("add OK");
  } // add

  
  private void detail() throws Exception  {
    int no = in.readInt();
    for(Member member : list) {
      if(member.getNo() == no) {
        out.writeObject(member); 
        return;
      }
    } // for

    out.writeObject(null);
  } // detail

  
  private void list() throws Exception {
    out.writeUnshared(list);
  } // list

  private void delete() throws Exception {
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
  

  private void update() throws Exception {
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
