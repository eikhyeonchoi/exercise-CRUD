package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardAddCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardAddCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute() {
    
    try {
      Board board = new Board();
      System.out.print("내용 ? : ");
      board.setContents(keyboard.nextLine());
      
      boardDao.insert(board);
      System.out.println("insert 완료");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

