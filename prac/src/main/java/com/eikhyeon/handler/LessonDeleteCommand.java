package com.eikhyeon.handler;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;

public class LessonDeleteCommand implements Command {
  
  Scanner keyboard;
  LessonDao lessonDao;
  
  public LessonDeleteCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute() {
    try {
      System.out.print("번호 ? : ");
      if(lessonDao.delete(Integer.parseInt(keyboard.nextLine())) == 0) {
        System.out.println("해당 수업이 없습니다");
        return;
      }
      System.out.println("delete 성공");
    } catch(Exception e) {
      System.out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

