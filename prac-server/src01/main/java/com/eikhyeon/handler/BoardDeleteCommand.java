package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;

public class BoardDeleteCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      if(boardDao.delete(Integer.parseInt(in.readLine())) == 0) {
        out.println("해당 게시물이 없습니다");
        return;
      }
      out.println("delete 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

