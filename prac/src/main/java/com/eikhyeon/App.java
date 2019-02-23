package com.eikhyeon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Scanner;
import com.eikhyeon.dao.BoardDao;
import com.eikhyeon.dao.BoardDaoImpl;
import com.eikhyeon.handler.BoardAddCommand;
import com.eikhyeon.handler.BoardDeleteCommand;
import com.eikhyeon.handler.BoardDetailCommand;
import com.eikhyeon.handler.BoardListCommand;
import com.eikhyeon.handler.BoardUpdateCommand;
import com.eikhyeon.handler.Command;

public class App {

  public void service() throws Exception {
    Scanner keyboard = new Scanner(System.in);
    HashMap<String, Command> serviceMap = new HashMap<>();
    
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/crud", "eikhyeon", "8542");
    
    BoardDao boardDao = new BoardDaoImpl(con);
    serviceMap.put("/board/add", new BoardAddCommand(keyboard, boardDao));
    serviceMap.put("/board/list", new BoardListCommand(keyboard, boardDao));
    serviceMap.put("/board/detail", new BoardDetailCommand(keyboard, boardDao));
    serviceMap.put("/board/update", new BoardUpdateCommand(keyboard, boardDao));
    serviceMap.put("/board/delete", new BoardDeleteCommand(keyboard, boardDao));
    
    while (true) {
      System.out.print(">> ");
      String input = keyboard.nextLine();
      
      if (input.equalsIgnoreCase("quit")) {
        System.out.println("프로그램을 종료합니다");
        break;
      }
      
      try {
        
        Command commandHandler = serviceMap.get(input);
        if(commandHandler == null) {
          System.out.println("유효하지 않는 명령어입니다");
          continue;
        }
        commandHandler.execute();

      } catch(Exception e) {
        System.out.println("연결 중 오류 발생 ");
        e.printStackTrace();

      } // try (Socket)
    } // while

    keyboard.close();

  } // service
  
  public static void main(String[] args) throws Exception {
    App app = new App();
    app.service();
  } // main
  
} // end of class
