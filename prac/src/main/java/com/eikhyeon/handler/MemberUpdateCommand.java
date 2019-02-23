package com.eikhyeon.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberUpdateCommand implements Command {

  Scanner keyboard;
  MemberDao memberDao;

  public MemberUpdateCommand(Scanner keyboard, MemberDao memberDao) {
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
        System.out.println("해당 회원이 없습니다");
        return;
      }

      Member clone = member.clone();

      System.out.println("#update = 엔터시 기존값이 재사용됩니다");

      System.out.print("이름? : ");
      String input = keyboard.nextLine();
      clone.setName(input.length() > 0 ? input : member.getName());

      System.out.print("메일? : ");
      input = keyboard.nextLine();
      clone.setEmail(input.length() > 0 ? input : member.getEmail());

      System.out.print("암호? : ");
      input = keyboard.nextLine();
      clone.setPassword(input.length() > 0 ? input : member.getPassword());

      System.out.print("사진? : ");
      input = keyboard.nextLine();
      clone.setPhoto(input.length() > 0 ? input : member.getPhoto());

      System.out.print("전화? : ");
      input = keyboard.nextLine();
      clone.setTel(input.length() > 0 ? input : member.getTel());

      clone.setRegisteredDate(new Date(System.currentTimeMillis()));

      if (memberDao.update(clone) == 0) {
        System.out.println("update 실패");
        return ;
      }
      System.out.println("update 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

