package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonDetailCommand implements Command {
  
  LessonDao lessonDao;
  
  public LessonDetailCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      Lesson lesson = lessonDao.findByNo(
          Integer.parseInt(in.readLine()));
      if(lesson == null) { 
        out.println("해당 수업을 찾을 수 없습니다");
        return;
      }
      out.printf("제목 : %s\n", lesson.getTitle());
      out.printf("내용 : %s\n", lesson.getContents());
      out.printf("시작일 : %s\n", lesson.getStartDate());
      out.printf("종료일 : %s\n", lesson.getEndDate());
      out.printf("총 수업 시간 : %s\n", lesson.getTotalHours());
      out.printf("일 수업 시간 : %s\n", lesson.getDayHours());
      
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

