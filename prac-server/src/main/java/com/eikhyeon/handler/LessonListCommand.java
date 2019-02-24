package com.eikhyeon.handler;
import java.util.List;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonListCommand extends AbstractCommand  {

  LessonDao lessonDao;

  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(Response response) {
    try {
      List<Lesson> list = lessonDao.findAll();
      for(Lesson lesson : list) {
        response.justPrintln(String.format("%d, %10s, %15s, %10s, %10s, %8d, %8d",
            lesson.getNo(), lesson.getTitle(), lesson.getContents(),
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours(), lesson.getDayHours()));
      }
    } catch(Exception e) {
      response.justPrintln("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

