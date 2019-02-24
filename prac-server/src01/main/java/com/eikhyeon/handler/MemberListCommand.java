package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberListCommand implements Command {

  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      List<Member> list = memberDao.findAll();
      for(Member member : list) {
        out.printf("%d, %5s, %10s,  *****, %10s, %s, %s\n",
            member.getNo(), member.getName(), member.getEmail()
            , member.getPhoto(), member.getTel(), member.getRegisteredDate());
      }
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

