package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardDetailCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardDetailCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      Board board = boardDao.findByNo(
          Integer.parseInt(keyboard.nextLine()));
      if(board == null) { 
        System.out.println("해당 게시물을 찾을 수 없습니다");
        return;
      }
      System.out.printf("내용 : %s\n", board.getContents());
      System.out.printf("생성일 : %s\n", board.getCreatedDate());
      System.out.printf("조회수 : %s\n", board.getViewCount());
      
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

