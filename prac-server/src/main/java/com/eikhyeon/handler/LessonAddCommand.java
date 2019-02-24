package com.eikhyeon.handler;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonAddCommand extends AbstractCommand {
  
  LessonDao lessonDao;
  
  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
      Lesson lesson = new Lesson();
      
      lesson.setTitle(response.requestString("제목 ? : "));
      
      lesson.setContents(response.requestString("내용 ? : "));
      
      lesson.setStartDate(response.requestDate("시작일? : "));
      
      lesson.setEndDate(response.requestDate("종료일? : "));
      
      lesson.setTotalHours(response.requestInt("총 수업 시간? : "));
      
      lesson.setDayHours(response.requestInt("일 수업 시간? : "));
      
      lessonDao.insert(lesson);
      response.justPrintln("insert 완료");
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class


