package com.eikhyeon.handler;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardAddCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardAddCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) {
    
    try {
      Board board = new Board();
      board.setContents(response.requestString("내용 ? :"));
      
      boardDao.insert(board);
      response.justPrintln("insert 완료");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

