package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Board;

public class BoardTest  {
  ObjectOutputStream out = null;
  ObjectInputStream in =  null;
  
  public BoardTest(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }
  
  public void test() throws Exception {

    add(new Board(111, "첫번째 게시물"));
    add(new Board(222, "두번째 게시물"));
    add(new Board(333, "익콩이 게시물"));
    detail(111);
    System.out.println("--------------");
    list();
    System.out.println("--------------");
    delete(222);
    update(new Board(333, "변경된 익콩이"));
    list();
  } // service


  private void add(Board Board) throws Exception{
    out.writeUTF("/board/add"); out.flush();
    out.writeObject(Board);
    String response = in.readUTF();
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // add

  private void detail(int no) throws Exception{
    out.writeUTF("/board/detail"); out.flush();
    out.writeInt(no); out.flush();
    Board Board = (Board) in.readObject();
    if(Board == null) {
      System.out.println("FAIL");
    } else System.out.println(Board);
  } // detail

  @SuppressWarnings("unchecked")
  private void list() throws Exception {
    out.writeUTF("/board/list"); out.flush();
    ArrayList<Board> list = (ArrayList<Board>) in.readObject();
    for(Board Board : list) {
      System.out.println(Board);
    }

  } // list

  private void delete(int no) throws Exception {
    out.writeUTF("/board/delete"); out.flush();
    out.writeInt(no); out.flush();

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");

  } // delete

  private void update(Board newBoard) throws Exception {
    out.writeUTF("/board/update"); out.flush();
    out.writeObject(newBoard);

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");

  } // update


} // end of class















