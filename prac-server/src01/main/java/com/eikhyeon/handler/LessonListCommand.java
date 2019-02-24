package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonListCommand implements Command {

  LessonDao lessonDao;

  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      List<Lesson> list = lessonDao.findAll();
      for(Lesson lesson : list) {
        out.printf("%d, %10s, %15s, %10s, %10s, %8d, %8d\n",
            lesson.getNo(), lesson.getTitle(), lesson.getContents(),
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours(), lesson.getDayHours());
      }
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)

} // end of class

