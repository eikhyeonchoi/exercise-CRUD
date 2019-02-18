package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import exer.server.domain.Lesson;

public class LessonTest  {
  ObjectOutputStream out = null;
  ObjectInputStream in = null;
  public LessonTest(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  public void test() throws Exception {

    add(new Lesson(111, "첫번째 제목"));
    add(new Lesson(222, "두번째 제목"));
    add(new Lesson(333, "익콩이 제목"));
//    detail(111);
//    System.out.println("--------------"통신중 오류 발생 null);
//    list();
//    System.out.println("--------------");
//    delete(222);
//    update(new Lesson(333, "변경된 제목"));
    list();
  } // service


  public void add(Lesson lesson) throws Exception{
    out.writeUTF("/lesson/add"); out.flush();
    out.writeObject(lesson);
    String response = in.readUTF();
    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // add

  public void detail(int no) throws Exception{
    out.writeUTF("/lesson/detail"); out.flush();
    out.writeInt(no); out.flush();
    Lesson lesson = (Lesson) in.readObject();
    if(lesson == null) {
      System.out.println("FAIL");
    } else System.out.println(lesson);
  } // detail

  @SuppressWarnings("unchecked")
  public void list() throws Exception {
    out.writeUTF("/lesson/list"); out.flush();
    ArrayList<Lesson> list = (ArrayList<Lesson>) in.readObject();
    for(Lesson lesson : list) {
      System.out.println(lesson);
    }
  } // list

  public  void delete(int no) throws Exception {
    out.writeUTF("/lesson/delete"); out.flush();
    out.writeInt(no); out.flush();

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // delete

  public  void update(Lesson newLesson) throws Exception {
    out.writeUTF("/lesson/update"); out.flush();
    out.writeObject(newLesson);

    String response = in.readUTF();

    if(response.contains("OK")) {
      System.out.println(response);
    } else System.out.println("FAIL");
  } // update


} // end of class















