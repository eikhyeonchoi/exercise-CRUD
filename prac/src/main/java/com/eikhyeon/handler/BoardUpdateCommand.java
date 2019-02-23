package com.eikhyeon.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardUpdateCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardUpdateCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.println("번호 ? : ");
      Board board = boardDao.findByNo(
          Integer.parseInt(keyboard.nextLine()));
      if(board == null) {
        System.out.println("해당 게시물이 없습니다");
        return;
      }
     
      Board clone = board.clone();
      
      System.out.println("새로운 내용? (엔터시 기존내용): ");
      String contents = keyboard.nextLine();
      clone.setContents(contents.length() > 0 ? contents : board.getContents());
      
      clone.setCreatedDate(new Date(System.currentTimeMillis()));
      
      if (boardDao.update(clone) == 0) {
        System.out.println("update 실패");
        return ;
      }
      System.out.println("update 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

