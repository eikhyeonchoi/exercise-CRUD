package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardDetailCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Board board = boardDao.findByNo(response.requestInt("번호 ? :"));
      if(board == null) { 
        response.justPrintln("해당 게시물을 찾을 수 없습니다");
        return;
      }
      response.justPrintln(String.format("내용 : %s", board.getContents()));
      response.justPrintln(String.format("생성일 : %s", board.getCreatedDate()));
      response.justPrintln(String.format("조회수 : %s", board.getViewCount()));
      
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

