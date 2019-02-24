package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;

public class MemberDeleteCommand implements Command {
  
  MemberDao memberDao;
  
  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      if(memberDao.delete(Integer.parseInt(in.readLine())) == 0) {
        out.println("해당 회원이 없습니다");
        return;
      }
      out.println("delete 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

