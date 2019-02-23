package com.eikhyeon.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonAddCommand implements Command {
  
  Scanner keyboard;
  LessonDao lessonDao;
  
  public LessonAddCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute() {
    try {
      Lesson lesson = new Lesson();
      System.out.print("제목 ? : ");
      lesson.setTitle(keyboard.nextLine());
      
      System.out.print("내용 ? : ");
      lesson.setContents(keyboard.nextLine());
      
      System.out.print("시작일 ? : ");
      lesson.setStartDate(Date.valueOf(keyboard.nextLine()));
      
      System.out.print("종료일 ? : ");
      lesson.setEndDate(Date.valueOf(keyboard.nextLine()));
      
      System.out.print("총 수업시간 ? : ");
      lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));
      
      System.out.print("일 수업시간 ? : ");
      lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));
      
      lessonDao.insert(lesson);
      System.out.println("insert 완료");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

