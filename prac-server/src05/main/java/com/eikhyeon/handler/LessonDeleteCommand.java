package com.eikhyeon.handler;
import com.eikhyeon.dao.LessonDao;

public class LessonDeleteCommand extends AbstractCommand  {
  
  LessonDao lessonDao;
  
  public LessonDeleteCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      if(lessonDao.delete(response.requestInt("번호? : ")) == 0) {
        response.justPrintln("해당 수업이 없습니다");
        return;
      }
      response.justPrintln("delete 성공");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

