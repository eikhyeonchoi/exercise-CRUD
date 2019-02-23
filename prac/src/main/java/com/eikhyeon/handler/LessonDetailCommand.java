package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.domain.Lesson;

public class LessonDetailCommand implements Command {
  
  Scanner keyboard;
  LessonDao lessonDao;
  
  public LessonDetailCommand(Scanner keyboard, LessonDao lessonDao) {
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
        System.out.println("해당 수업을 찾을 수 없습니다");
        return;
      }
      System.out.printf("제목 : %s\n", lesson.getTitle());
      System.out.printf("내용 : %s\n", lesson.getContents());
      System.out.printf("시작일 : %s\n", lesson.getStartDate());
      System.out.printf("종료일 : %s\n", lesson.getEndDate());
      System.out.printf("총 수업 시간 : %s\n", lesson.getTotalHours());
      System.out.printf("일 수업 시간 : %s\n", lesson.getDayHours());
      
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

