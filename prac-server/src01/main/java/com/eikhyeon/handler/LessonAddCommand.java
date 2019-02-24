package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonAddCommand implements Command {
  
  LessonDao lessonDao;
  
  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      Lesson lesson = new Lesson();
      
      out.println("제목 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setTitle(in.readLine());
      
      out.println("내용 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setContents(in.readLine());
      
      out.println("시작일 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setStartDate(Date.valueOf(in.readLine()));
      
      out.println("종료일 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setEndDate(Date.valueOf(in.readLine()));
      
      out.println("총 수업시간 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setTotalHours(Integer.parseInt(in.readLine()));
      
      out.println("일 수업시간 ? : ");
      out.println("!{}!");
      out.flush();
      lesson.setDayHours(Integer.parseInt(in.readLine()));
      
      lessonDao.insert(lesson);
      out.println("insert 완료");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class


