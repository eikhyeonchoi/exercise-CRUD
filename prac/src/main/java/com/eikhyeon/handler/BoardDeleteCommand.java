package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;

public class BoardDeleteCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardDeleteCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      if(boardDao.delete(Integer.parseInt(keyboard.nextLine())) == 0) {
        System.out.println("해당 게시물이 없습니다");
        return;
      }
      System.out.println("delete 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

