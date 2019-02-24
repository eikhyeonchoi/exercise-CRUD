package com.eikhyeon.handler;
import com.eikhyeon.dao.MemberDao;

public class MemberDeleteCommand extends AbstractCommand {
  
  MemberDao memberDao;
  
  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      if(memberDao.delete(response.requestInt("번호? : ")) == 0) {
        response.justPrintln("해당 회원이 없습니다");
        return;
      }
      response.justPrintln("delete 성공");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

