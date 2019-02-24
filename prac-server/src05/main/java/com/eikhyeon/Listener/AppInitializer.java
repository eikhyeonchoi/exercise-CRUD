package com.eikhyeon.Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.dao.BoardDaoImpl;
import com.eikhyeon.dao.LessonDao;
import com.eikhyeon.dao.LessonDaoImpl;
import com.eikhyeon.dao.MemberDao;
import com.eikhyeon.dao.MemberDaoImpl;
import com.eikhyeon.handler.BoardAddCommand;
import com.eikhyeon.handler.BoardDeleteCommand;
import com.eikhyeon.handler.BoardDetailCommand;
import com.eikhyeon.handler.BoardListCommand;
import com.eikhyeon.handler.BoardUpdateCommand;
import com.eikhyeon.handler.LessonAddCommand;
import com.eikhyeon.handler.LessonDeleteCommand;
import com.eikhyeon.handler.LessonDetailCommand;
import com.eikhyeon.handler.LessonListCommand;
import com.eikhyeon.handler.LessonUpdateCommand;
import com.eikhyeon.handler.MemberAddCommand;
import com.eikhyeon.handler.MemberDeleteCommand;
import com.eikhyeon.handler.MemberDetailCommand;
import com.eikhyeon.handler.MemberListCommand;
import com.eikhyeon.handler.MemberUpdateCommand;

public class AppInitializer implements AppContextListener {

  Scanner keyboard;
  Connection con;
  
  @Override
  public void init(HashMap<String, Object> serviceMap) {
    try {
    con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/crud", "eikhyeon", "8542");

    this.keyboard = (Scanner) serviceMap.get("keyboard");
    
    BoardDao boardDao = new BoardDaoImpl(con);
    serviceMap.put("/board/add", new BoardAddCommand(boardDao));
    serviceMap.put("/board/list", new BoardListCommand(boardDao));
    serviceMap.put("/board/detail", new BoardDetailCommand(boardDao));
    serviceMap.put("/board/update", new BoardUpdateCommand(boardDao));
    serviceMap.put("/board/delete", new BoardDeleteCommand(boardDao));
    
    LessonDao lessonDao = new LessonDaoImpl(con);
    serviceMap.put("/lesson/add", new LessonAddCommand(lessonDao));
    serviceMap.put("/lesson/list", new LessonListCommand(lessonDao));
    serviceMap.put("/lesson/detail", new LessonDetailCommand(lessonDao));
    serviceMap.put("/lesson/update", new LessonUpdateCommand(lessonDao));
    serviceMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
    
    MemberDao memberDao = new MemberDaoImpl(con);
    serviceMap.put("/member/add", new MemberAddCommand(memberDao));
    serviceMap.put("/member/list", new MemberListCommand(memberDao));
    serviceMap.put("/member/detail", new MemberDetailCommand(memberDao));
    serviceMap.put("/member/update", new MemberUpdateCommand(memberDao));
    serviceMap.put("/member/delete", new MemberDeleteCommand(memberDao));
    
    }catch(Exception e) {
      System.out.println("초기화중 오류 발생"); 
      e.printStackTrace();
    }
    
  }

  @Override
  public void destroy(HashMap<String, Object> listeners) {
    try {
      con.close();
    } catch(Exception e) {
      System.out.println("마무리중 오류 발생");
      e.printStackTrace();
      
    }
  }

}
