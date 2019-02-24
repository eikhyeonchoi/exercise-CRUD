package com.eikhyeon.handler;
import java.sql.Date;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.domain.Member;

public class MemberAddCommand extends AbstractCommand{
  
  MemberDao memberDao;
  
  public MemberAddCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Member member = new Member();
      member.setName(response.requestString("이름? : "));
      member.setEmail(response.requestString("메일? : "));
      member.setPassword(response.requestString("암호? : "));
      member.setPhoto(response.requestString("사진? : "));
      member.setTel(response.requestString("전화? : "));
      member.setRegisteredDate(new Date(System.currentTimeMillis()));
      
      memberDao.insert(member);
      response.justPrintln("insert 완료");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

