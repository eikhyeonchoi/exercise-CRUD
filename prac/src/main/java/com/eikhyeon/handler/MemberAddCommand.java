package com.eikhyeon.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberAddCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberDao;
  
  public MemberAddCommand(Scanner keyboard, MemberDao memberDao) {
    this.keyboard = keyboard;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {
    try {
      Member member = new Member();
      System.out.print("이름? : ");
      member.setName(keyboard.nextLine());
      
      System.out.print("메일 ? : ");
      member.setEmail(keyboard.nextLine());
      
      System.out.print("암호 ? : ");
      member.setPassword(keyboard.nextLine());
      
      System.out.print("사진 ? : ");
      member.setPhoto(keyboard.nextLine());
      
      System.out.print("전화 ? : ");
      member.setTel(keyboard.nextLine());
      
      member.setRegisteredDate(new Date(System.currentTimeMillis()));
      
      memberDao.insert(member);
      System.out.println("insert 완료");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

