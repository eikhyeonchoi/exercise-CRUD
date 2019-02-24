package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonUpdateCommand implements Command {
  
  LessonDao lessonDao;
  
  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.print("번호 ? : ");
      out.println("!{}!");
      out.flush();
      Lesson lesson = lessonDao.findByNo(
          Integer.parseInt(in.readLine()));
      if(lesson == null) {
        out.println("해당 수업이 없습니다");
        return;
      }
     
      Lesson clone = lesson.clone();
      out.println("#update = 엔터시 기존값이 재사용됩니다");
      
      out.println("제목? : ");
      out.println("!{}!");
      out.flush();
      String input = in.readLine();
      clone.setTitle(input.length() > 0 ? input : lesson.getTitle());
      
      out.println("제목? : ");
      out.println("!{}!");
      out.flush();
      input = in.readLine();
      clone.setContents(input.length() > 0 ? input : lesson.getContents());
      
      out.println("시작일? : ");
      out.println("!{}!");
      out.flush();
      input = in.readLine();
      clone.setStartDate(input.length() > 0 ? Date.valueOf(input) : lesson.getStartDate());
      
      out.println("종료일? : ");
      out.println("!{}!");
      out.flush();
      input = in.readLine();
      clone.setEndDate(input.length() > 0 ? Date.valueOf(input) : lesson.getEndDate());
      
      out.println("총 수업 시간? : ");
      out.println("!{}!");
      out.flush();
      input = in.readLine();
      clone.setTotalHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getTotalHours());
      
      out.println("일 수업 시간? : ");
      out.println("!{}!");
      out.flush();
      input = in.readLine();
      clone.setDayHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getDayHours());
      
      if (lessonDao.update(clone) == 0) {
        out.println("update 실패");
        return ;
      }
      out.println("update 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

