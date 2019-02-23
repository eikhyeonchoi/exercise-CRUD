package com.eikhyeon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.eikhyeon.Listener.AppContextListener;
import com.eikhyeon.Listener.AppInitializer;
import com.eikhyeon.handler.Command;

public class App {
  
  HashMap<String, Object> serviceMap = new HashMap<>();
  ArrayList<AppContextListener> listeners = new ArrayList<AppContextListener>();
  
  public void addListener(AppContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {
    Scanner keyboard = new Scanner(System.in);
    serviceMap.put("keyboard", keyboard);
    
    for(AppContextListener listener : listeners) {
      listener.init(serviceMap);
    }
    
    while (true) {
      System.out.print("command>> ");
      String input = keyboard.nextLine();
      
      if (input.equalsIgnoreCase("quit")) {
        System.out.println("프로그램을 종료합니다");
        break;
      }
      
      try {
        Command commandHandler = (Command) serviceMap.get(input);
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
    for(AppContextListener listener : listeners) {
      listener.destroy(serviceMap);
    }

  } // service
  
  public static void main(String[] args) throws Exception {
    App app = new App();
    app.addListener(new AppInitializer());
    app.service();
  } // main
  
} // end of class
