package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardUpdateCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      Board board = boardDao.findByNo(
          Integer.parseInt(in.readLine()));
      if(board == null) {
        out.println("해당 게시물이 없습니다");
        return;
      }
      Board clone = board.clone();
      out.println("#update = 엔터시 기존값이 재사용됩니다");
      out.println("내용? : ");
      out.println("!{}!");
      out.flush();
      String contents = in.readLine();
      clone.setContents(contents.length() > 0 ? contents : board.getContents());
      
      clone.setCreatedDate(new Date(System.currentTimeMillis()));
      
      if (boardDao.update(clone) == 0) {
        out.println("update 실패");
        return ;
      }
      out.println("update 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

