package exer.client.command;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Command {
  void execute();
  //ObjectOutputStream out, ObjectInputStream in
}
