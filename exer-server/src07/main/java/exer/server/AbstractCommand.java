package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Lesson;

public abstract class AbstractCommand {
  ArrayList<Lesson> list;
  ObjectInputStream in = null;
  ObjectOutputStream out = null;
  
  public AbstractCommand(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
  public abstract void service(String response) throws Exception;
}
