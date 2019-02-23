package com.eikhyeon.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonUpdateCommand implements Command {
  
  Scanner keyboard;
  LessonDao lessonDao;
  
  public LessonUpdateCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      Lesson lesson = lessonDao.findByNo(
          Integer.parseInt(keyboard.nextLine()));
      if(lesson == null) {
        System.out.println("해당 수업이 없습니다");
        return;
      }
     
      Lesson clone = lesson.clone();
      
      System.out.println("#update = 엔터시 기존값이 재사용됩니다");
      
      System.out.print("제목? : ");
      String input = keyboard.nextLine();
      clone.setTitle(input.length() > 0 ? input : lesson.getTitle());
      
      System.out.print("제목? : ");
      input = keyboard.nextLine();
      clone.setContents(input.length() > 0 ? input : lesson.getContents());
      
      System.out.print("시작일? : ");
      input = keyboard.nextLine();
      clone.setStartDate(input.length() > 0 ? Date.valueOf(input) : lesson.getStartDate());
      
      System.out.print("종료일? : ");
      input = keyboard.nextLine();
      clone.setEndDate(input.length() > 0 ? Date.valueOf(input) : lesson.getEndDate());
      
      System.out.print("총 수업 시간? : ");
      input = keyboard.nextLine();
      clone.setTotalHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getTotalHours());
      
      System.out.print("일 수업 시간? : ");
      input = keyboard.nextLine();
      clone.setDayHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getDayHours());
      
      if (lessonDao.update(clone) == 0) {
        System.out.println("update 실패");
        return ;
      }
      System.out.println("update 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

