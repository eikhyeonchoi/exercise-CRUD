package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberDetailCommand implements Command {
  
  MemberDao memberDao;
  
  public MemberDetailCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      System.out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      Member member = memberDao.findByNo(
          Integer.parseInt(in.readLine()));
      if(member == null) { 
        out.println("해당 회원을 찾을 수 없습니다");
        return;
      }
      out.printf("이름 : %s\n", member.getName());
      out.printf("메일 : %s\n", member.getEmail());
      out.print("암호 : *******\n");
      out.printf("사진 : %s\n", member.getPhoto());
      out.printf("전화 : %s\n", member.getTel());
      out.printf("가입일 : %s\n", member.getRegisteredDate());
      
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

