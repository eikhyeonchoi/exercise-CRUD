package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardListCommand extends AbstractCommand {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) {
    try {
      List<Board> list = boardDao.findAll();
      for(Board board : list) {
        response.justPrintln(String.format("%d, %s, %s, %d",
            board.getNo(), board.getContents(),
            board.getCreatedDate(), board.getViewCount()));
      }
    } catch(Exception e) {
      
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

