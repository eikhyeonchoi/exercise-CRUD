package com.eikhyeon.handler;
import java.sql.Date;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberUpdateCommand extends AbstractCommand {

  MemberDao memberDao;

  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(Response response) {
    try {
      Member member = memberDao.findByNo(response.requestInt("번호? : "));
      if(member == null) {
        response.justPrintln("해당 회원이 없습니다");
        return;
      }

      Member clone = member.clone();
      response.justPrintln("#update = 엔터시 기존값이 재사용됩니다");

      String input = response.requestString("이름? : ");
      clone.setName(input.length() > 0 ? input : member.getName());

      input =  response.requestString("메일? : ");
      clone.setEmail(input.length() > 0 ? input : member.getEmail());

      input =  response.requestString("암호? : ");
      clone.setPassword(input.length() > 0 ? input : member.getPassword());

      input = response.requestString("암호? : ");
      clone.setPhoto(input.length() > 0 ? input : member.getPhoto());

      input =  response.requestString("전화? : ");
      clone.setTel(input.length() > 0 ? input : member.getTel());

      clone.setRegisteredDate(new Date(System.currentTimeMillis()));

      if (memberDao.update(clone) == 0) {
        response.justPrintln("update 실패");
        return ;
      }
      response.justPrintln("update 성공");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

