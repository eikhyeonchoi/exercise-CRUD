package exer.server.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import exer.server.domain.Board;

public class BoardService {
  List<Board> boardList;
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public BoardService(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  public void loadData(String filePath) {
    try (ObjectInputStream out = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(filePath))); ){
      boardList = (List<Board>) in.readObject();
    } catch(Exception e) {
      System.out.println("Board 데이터 로드중 오류발생 " + e.getMessage());
      boardList = new ArrayList<Board>();
    }
  } // loadData
  
  public void saveData(String filePath) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(filePath))); ){
      out.writeObject(boardList);
    } catch(Exception e) {
      System.out.println("데이터 저장중 오류발생 " + e.getMessage());
    }
  } // saveData
  
  
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
    boardList.add((Board) in.readObject());
    out.writeUTF("add OK");
  } // add

  
  private void detail() throws Exception  {
    int no = in.readInt();
    for(Board board : this.boardList) {
      if(board.getNo() == no) {
        out.writeObject(board); 
        return;
      }
    } // for

    out.writeObject(null);
  } // detail

  
  private void list() throws Exception {
    out.writeUnshared(boardList);
  } // boardList

  private void delete() throws Exception {
    int no = in.readInt();

    int index = 0;
    for(Board board : boardList) {
      if(board.getNo() == no) {
        boardList.remove(index);
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
    for(Board board : boardList) {
      if(newBoard.getNo() == board.getNo()) {
        boardList.set(index, newBoard);
        out.writeUTF("update OK");
        return;
      }
      index ++;
    } // for
    out.writeUTF("update FAIL");
  } // update
  
  
  

} // end of class
