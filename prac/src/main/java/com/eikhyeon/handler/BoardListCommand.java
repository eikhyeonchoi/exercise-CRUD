package com.eikhyeon.handler;
import java.util.List;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardListCommand implements Command {

  Scanner keyboard;
  BoardDao boardDao;

  public BoardListCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      List<Board> list = boardDao.findAll();
      for(Board board : list) {
        System.out.printf("%d, %s, %s, %d\n",
            board.getNo(), board.getContents(),
            board.getCreatedDate(), board.getViewCount());
      }
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

