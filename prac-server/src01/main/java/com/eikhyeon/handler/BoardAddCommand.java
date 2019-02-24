package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardAddCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardAddCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    
    try {
      Board board = new Board();
      
      out.println("내용 ? : ");
      out.println("!{}!");
      out.flush();
      
      board.setContents(in.readLine());
      
      boardDao.insert(board);
      out.println("insert 완료");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

