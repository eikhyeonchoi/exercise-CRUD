package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.domain.Board;

public class BoardDetailCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardDetailCommand(BoardDao boardDao) {
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
        out.println("해당 게시물을 찾을 수 없습니다");
        return;
      }
      out.printf("내용 : %s\n", board.getContents());
      out.printf("생성일 : %s\n", board.getCreatedDate());
      out.printf("조회수 : %s\n", board.getViewCount());
      
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

