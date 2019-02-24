package com.eikhyeon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

  public void service() {
    Scanner keyboard = new Scanner(System.in);

    while(true) {
      System.out.print("command>> ");
      String command = keyboard.nextLine();
      if(command.equalsIgnoreCase("quit")) {
        System.out.println("프로그램을 종료합니다");
        break;
      }
      
      try(Socket socket = new Socket("localhost",8888);
          PrintWriter out = new PrintWriter(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))){

        out.println(command);
        out.flush();
        
        if(command.equalsIgnoreCase("stop")) {
          System.out.println("프로그램을 종료합니다\n서버를 종료합니다");
          break;
        }
        
        while(true) {
          String response = in.readLine();
          
          if(response.equals("!{}!")) {
            String input = keyboard.nextLine();
            out.println(input);
            out.flush();
            continue;
          }
          if(response.contentEquals("!end!")) break;
          
          System.out.println(response);
        }

      } catch(Exception e) {
        System.out.println("오류 발생");
        e.printStackTrace();
      }
    } // while
    keyboard.close();
  } // service

  public static void main(String[] args) {
    ClientApp app = new ClientApp();
    app.service();
  } // main()
} // end of class
