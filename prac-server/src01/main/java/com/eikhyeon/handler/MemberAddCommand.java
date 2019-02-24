package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberAddCommand implements Command {
  
  MemberDao memberDao;
  
  public MemberAddCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      Member member = new Member();
      out.println("이름? : ");
      out.println("!{}!");
      out.flush();
      member.setName(in.readLine());
      
      out.println("메일 ? : ");
      out.println("!{}!");
      out.flush();
      member.setEmail(in.readLine());
      
      out.println("암호 ? : ");
      out.println("!{}!");
      out.flush();
      member.setPassword(in.readLine());
      
      out.println("사진 ? : ");
      out.println("!{}!");
      out.flush();
      member.setPhoto(in.readLine());
      
      out.println("전화 ? : ");
      out.println("!{}!");
      out.flush();
      member.setTel(in.readLine());
      
      member.setRegisteredDate(new Date(System.currentTimeMillis()));
      
      memberDao.insert(member);
      out.println("insert 완료");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

