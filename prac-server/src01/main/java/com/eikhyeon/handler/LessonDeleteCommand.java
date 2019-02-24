package com.eikhyeon.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.eikhyeon.dao.LessonDao;

public class LessonDeleteCommand implements Command {
  
  LessonDao lessonDao;
  
  public LessonDeleteCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("번호 ? : ");
      out.println("!{}!");
      out.flush();
      if(lessonDao.delete(Integer.parseInt(in.readLine())) == 0) {
        out.println("해당 수업이 없습니다");
        return;
      }
      out.println("delete 성공");
    } catch(Exception e) {
      out.println("명령어 처리중 오류 발생");
      e.printStackTrace();
    }
  } // execute(2)
  
} // end of class

