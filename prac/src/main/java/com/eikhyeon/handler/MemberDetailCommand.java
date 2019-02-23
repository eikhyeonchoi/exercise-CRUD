package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberDetailCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberDao;
  
  public MemberDetailCommand(Scanner keyboard, MemberDao memberDao) {
    this.keyboard = keyboard;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      Member member = memberDao.findByNo(
          Integer.parseInt(keyboard.nextLine()));
      if(member == null) { 
        System.out.println("해당 회원을 찾을 수 없습니다");
        return;
      }
      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("메일 : %s\n", member.getEmail());
      System.out.print("암호 : *******\n");
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("전화 : %s\n", member.getTel());
      System.out.printf("가입일 : %s\n", member.getRegisteredDate());
      
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

