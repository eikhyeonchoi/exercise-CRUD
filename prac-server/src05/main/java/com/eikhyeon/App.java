package com.eikhyeon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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
    try(ServerSocket ss = new ServerSocket(8888)) {
      System.out.println("서버 실행중");

      for(AppContextListener listener : listeners) {
        listener.init(serviceMap);
      }

      while (true) {

        try(Socket socket = ss.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream()); ){

          String message = in.readLine();
          
          if(message.equalsIgnoreCase("stop")) {
            System.out.println("서버를 종료합니다");
            break;
          }
          
          Command command = (Command) serviceMap.get(message);
          if(command == null) {
            out.println("유효하지 않는 명령입니다");
          } else {
            command.execute(out, in);
          }
          
          out.println("!end!");
          out.flush();
          
        } // try(Socket)
        
      } // while
      
    } catch(Exception e) {
      e.printStackTrace();
    } // try(ServerSocket)

    for(AppContextListener listener : listeners) {
      listener.destroy(serviceMap);
    }
  } // service

  public static void main(String[] args) throws Exception {
    App app = new App();
    app.addListener(new AppInitializer());
    app.service();
  } // main

}
