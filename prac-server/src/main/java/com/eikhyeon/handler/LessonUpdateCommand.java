package com.eikhyeon.handler;
import java.sql.Date;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonUpdateCommand extends AbstractCommand {
  
  LessonDao lessonDao;
  
  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Lesson lesson = lessonDao.findByNo(response.requestInt("번호? : "));
      if(lesson == null) {
        response.justPrintln("해당 수업이 없습니다");
        return;
      }
     
      Lesson clone = lesson.clone();
      response.justPrintln("#update = 엔터시 기존값이 재사용됩니다");
      
      String input = response.requestString("제목? : ");
      clone.setTitle(input.length() > 0 ? input : lesson.getTitle());
      
      input = response.requestString("내용? : ");
      clone.setContents(input.length() > 0 ? input : lesson.getContents());
      
      input = response.requestString("시작일? : ");
      clone.setStartDate(input.length() > 0 ? Date.valueOf(input) : lesson.getStartDate());
      
      input = response.requestString("종료일? : ");
      clone.setEndDate(input.length() > 0 ? Date.valueOf(input) : lesson.getEndDate());
      
      input = response.requestString("총 수업 시간? : ");
      clone.setTotalHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getTotalHours());
      
      input = response.requestString("일 수업 시간? : ");
      clone.setDayHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getDayHours());
      
      if (lessonDao.update(clone) == 0) {
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

