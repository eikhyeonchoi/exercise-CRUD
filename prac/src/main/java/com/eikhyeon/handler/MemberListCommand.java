package com.eikhyeon.handler;
import java.util.List;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberListCommand implements Command {

  Scanner keyboard;
  MemberDao memberDao;

  public MemberListCommand(Scanner keyboard, MemberDao memberDao) {
    this.keyboard = keyboard;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      List<Member> list = memberDao.findAll();
      for(Member member : list) {
        System.out.printf("%d, %5s, %10s,  *****, %10s, %s, %s\n",
            member.getNo(), member.getName(), member.getEmail()
            , member.getPhoto(), member.getTel(), member.getRegisteredDate());
      }
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

