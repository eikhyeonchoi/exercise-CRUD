package exer.server.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import exer.server.domain.Board;

public class AbstractService<E> {
  List<E> list;
  ObjectInputStream in;
  ObjectOutputStream out;

  public void init(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  public void loadData(String filePath) {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(filePath))); ){
      list = (List<E>) in.readObject();
    } catch(Exception e) {
      System.out.println("데이터 로드중 오류발생 " + e.getMessage());
      list = new ArrayList<E>();
    }
  } // loadData

  
  public void saveData(String filePath) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(filePath))); ){
      out.writeObject(list);
    } catch(Exception e) {
      System.out.println("데이터 저장중 오류발생 " + e.getMessage());
    }
  } // saveData

  
} // end of class
