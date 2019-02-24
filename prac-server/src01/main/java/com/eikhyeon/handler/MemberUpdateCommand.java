package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberUpdateCommand implements Command {

  MemberDao memberDao;

  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      Member member = memberDao.findByNo(
          Integer.parseInt(in.readLine()));
      if(member == null) {
        out.println("해당 회원이 없습니다");
        return;
      }

      Member clone = member.clone();
      out.println("#update = 엔터시 기존값이 재사용됩니다");

      out.println("이름? : ");
      out.println("!{}!");
      out.flush();
      String input = in.readLine();
      clone.setName(input.length() > 0 ? input : member.getName());

      out.println("메일? : ");
      out.println("!{}!");
      out.flush();
      input =  in.readLine();
      clone.setEmail(input.length() > 0 ? input : member.getEmail());

      out.println("암호? : ");
      out.println("!{}!");
      out.flush();
      input =  in.readLine();
      clone.setPassword(input.length() > 0 ? input : member.getPassword());

      out.println("사진? : ");
      out.println("!{}!");
      out.flush();
      input =  in.readLine();
      clone.setPhoto(input.length() > 0 ? input : member.getPhoto());

      out.println("전화? : ");
      out.println("!{}!");
      out.flush();
      input =  in.readLine();
      clone.setTel(input.length() > 0 ? input : member.getTel());

      clone.setRegisteredDate(new Date(System.currentTimeMillis()));

      if (memberDao.update(clone) == 0) {
        out.println("update 실패");
        return ;
      }
      out.println("update 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

