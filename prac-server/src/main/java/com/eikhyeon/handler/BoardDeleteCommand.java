package com.eikhyeon.handler;
import com.eikhyeon.dao.BoardDao;

public class BoardDeleteCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      if(boardDao.delete(response.requestInt("번호 ? :")) == 0) {
        response.justPrintln("해당 게시물이 없습니다");
        return;
      }
      response.justPrintln("delete 성공");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

