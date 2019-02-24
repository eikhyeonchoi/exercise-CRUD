package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardUpdateCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Board board = boardDao.findByNo(response.requestInt("번호 ? :"));
      if(board == null) {
        response.justPrintln("해당 게시물이 없습니다");
        return;
      }
      Board clone = board.clone();
      response.justPrintln("#update = 엔터시 기존값이 재사용됩니다");
      String contents = response.requestString("내용? : ");
      clone.setContents(contents.length() > 0 ? contents : board.getContents());
      
      clone.setCreatedDate(new Date(System.currentTimeMillis()));
      
      if (boardDao.update(clone) == 0) {
        response.justPrintln("update 실패");
        return ;
      }
      response.justPrintln("update 성공");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

