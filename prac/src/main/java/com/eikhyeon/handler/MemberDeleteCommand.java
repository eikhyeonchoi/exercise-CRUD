package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;

public class MemberDeleteCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberDao;
  
  public MemberDeleteCommand(Scanner keyboard, MemberDao memberDao) {
    this.keyboard = keyboard;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      if(memberDao.delete(Integer.parseInt(keyboard.nextLine())) == 0) {
        System.out.println("해당 회원이 없습니다");
        return;
      }
      System.out.println("delete 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

