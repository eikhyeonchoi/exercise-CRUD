package exer.server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Lesson;

public class LessonService extends AbstractService {

  ArrayList<Lesson> list = new ArrayList<Lesson>();
  
  public LessonService(ObjectInputStream in, ObjectOutputStream out) {
    super(in, out);
  }
  
  @Override
  public void service(String response) throws Exception{
    switch(response) {
      case "quit" :  
        quit();
        return;
      case "/lesson/add" :
        add();
        break;
      case "/lesson/detail" : 
        detail();
        break;
      case "/lesson/delete" : 
        delete();
        break;
      case "/lesson/list" : 
        list();
        break;
      case "/lesson/update" : 
        update();
        break;
      default :
        out.writeUTF("해당 명령어는 지원하지 않습니다");

    } // swtich
    out.flush();
  } // main

  
  private void quit() throws Exception {
    out.writeUTF("종료합니다");
    out.flush();
  } // quit

  
  public void add() throws Exception {
    list.add((Lesson) in.readObject());
    out.writeUTF("add OK");
  } // add

  
  private void detail() throws Exception  {
    int no = in.readInt();
    for(Lesson lesson : list) {
      if(lesson.getNo() == no) {
        out.writeObject(lesson); 
        return;
      }
    } // for

    out.writeObject(null);
  } // detail

  
  private void list() throws Exception {
    out.writeUnshared(list);
  } // list

  private void delete() throws Exception {
    int no = in.readInt();

    int index = 0;
    for(Lesson lesson : list) {
      if(lesson.getNo() == no) {
        list.remove(index);
        out.writeUTF("delete OK");
        return;
      }
      index ++;
    }
    out.writeUTF("delete FAIL");
  } // delete
  

  private void update() throws Exception {
    Lesson newLesson = (Lesson) in.readObject();

    int index = 0;
    for(Lesson lesson : list) {
      if(newLesson.getNo() == lesson.getNo()) {
        list.set(index, newLesson);
        out.writeUTF("update OK");
        return;
      }
      index ++;
    } // for
    out.writeUTF("update FAIL");
  } // update
  
  

} // end of class
