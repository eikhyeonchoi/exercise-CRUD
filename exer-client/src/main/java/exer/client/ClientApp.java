package exer.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import exer.client.command.BoardAddCommand;
import exer.client.command.Command;

public class ClientApp {
  static ObjectOutputStream out;
  static ObjectInputStream in;
  static HashMap<String, Command> commandMap = new HashMap<String, Command>();

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    try(Socket socket = new Socket("localhost", 8888); 
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ){
      ClientApp.out = out;
      ClientApp.in = in;
      System.out.println("서버와 연결되었습니다");

      commandMap.put("/board/add", new BoardAddCommand(keyboard, out, in));

      while(true) {
        System.out.print(">> ");
        String input = keyboard.nextLine();
        Command command = commandMap.get(input);
        if(command != null) {
          try {
          command.execute();
          } catch(Exception e) {
            System.out.println("명령어 실행중 오류발생 " + e);
          }
        } else if(input.equalsIgnoreCase("quit")) {
          quit();
          break;
        } else {
          System.out.println("실행할 수 없는 명령입니다");
          continue;
        }


      } // while

    } catch (Exception e) {
      e.printStackTrace();
    }

    keyboard.close();

  } // main()


  static void quit() throws Exception {
    out.writeUTF("quit"); out.flush();
    String response = in.readUTF();
    System.out.println(response);
  } // quit()

} // end of class
