package com.eikhyeon.handler;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberDetailCommand extends AbstractCommand{
  
  MemberDao memberDao;
  
  public MemberDetailCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Member member = memberDao.findByNo(response.requestInt("번호? : "));
      if(member == null) { 
        response.justPrintln("해당 회원을 찾을 수 없습니다");
        return;
      }
      response.justPrintln(String.format("이름 : %s", member.getName()));
      response.justPrintln(String.format("메일 : %s", member.getEmail()));
      response.justPrintln("암호 : *******");
      response.justPrintln(String.format("사진 : %s", member.getPhoto()));
      response.justPrintln(String.format("전화 : %s", member.getTel()));
      response.justPrintln(String.format("가입일 : %s", member.getRegisteredDate()));
      
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

