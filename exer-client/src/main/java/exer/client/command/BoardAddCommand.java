package exer.client.command;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;
import exer.client.domain.Board;

public class BoardAddCommand implements Command{
  Scanner keyboard;
  ObjectOutputStream out;
  ObjectInputStream in;
  public BoardAddCommand(Scanner keyboard, ObjectOutputStream out, ObjectInputStream in) {
    this.keyboard = keyboard;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Board board = new Board();

    System.out.print("번호 : ");
    String input = keyboard.nextLine();
    board.setNo(Integer.parseInt(input));
    System.out.print("내용 : ");
    input = keyboard.nextLine();
    board.setContents(input);
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      out.writeUTF("/board/add"); out.flush();
      out.writeObject(board);
      String response = in.readUTF();
      if(response.contains("OK")) {
        System.out.println(response);
      } else 
        System.out.println("FAIL");
      
      System.out.println(response);
      
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("데이터 전송오류 " + e);
    }


  } // execute()
} // end of class
