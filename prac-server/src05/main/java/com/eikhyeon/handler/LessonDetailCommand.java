package com.eikhyeon.handler;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonDetailCommand extends AbstractCommand  {
  
  LessonDao lessonDao;
  
  public LessonDetailCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Lesson lesson = lessonDao.findByNo(response.requestInt("번호? : "));
      if(lesson == null) { 
        response.justPrintln("해당 수업을 찾을 수 없습니다");
        return;
      }
      response.justPrintln(String.format("제목 : %s", lesson.getTitle()));
      response.justPrintln(String.format("내용 : %s", lesson.getContents()));
      response.justPrintln(String.format("시작일 : %s", lesson.getStartDate()));
      response.justPrintln(String.format("종료일 : %s", lesson.getEndDate()));
      response.justPrintln(String.format("총 수업 시간 : %s", lesson.getTotalHours()));
      response.justPrintln(String.format("일 수업 시간 : %s", lesson.getDayHours()));
      
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

