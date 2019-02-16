package exer.server.service;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import exer.server.domain.Board;

public class BoardService extends AbstractService {
  ArrayList<Board> list = new ArrayList<Board>();
  
  public BoardService(ObjectInputStream in, ObjectOutputStream out) {
    super(in, out);
  }

  @Override
  public void service(String response) throws Exception{
    switch(response) {
      case "/board/add" :
        add();
        break;
      case "/board/detail" : 
        detail();
        break;
      case "/board/delete" : 
        delete();
        break;
      case "/board/list" : 
        list();
        break;
      case "/board/update" : 
        update();
        break;
      default :
        out.writeUTF("해당 명령어는 지원하지 않습니다");
        
    } // swtich
    
    out.flush();
    
  } // main
  
  
  private void add() throws Exception {
    list.add((Board) in.readObject());
    out.writeUTF("add OK");
  } // add

  
  private void detail() throws Exception  {
    int no = in.readInt();
    for(Board board : list) {
      if(board.getNo() == no) {
        out.writeObject(board); 
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
    for(Board board : list) {
      if(board.getNo() == no) {
        list.remove(index);
        out.writeUTF("delete OK");
        return;
      }
      index ++;
    }
    out.writeUTF("delete FAIL");
  } // delete
  

  private void update() throws Exception {
    Board newBoard = (Board) in.readObject();

    int index = 0;
    for(Board board : list) {
      if(newBoard.getNo() == board.getNo()) {
        list.set(index, newBoard);
        out.writeUTF("update OK");
        return;
      }
      index ++;
    } // for
    out.writeUTF("update FAIL");
  } // update
  
  

} // end of class
