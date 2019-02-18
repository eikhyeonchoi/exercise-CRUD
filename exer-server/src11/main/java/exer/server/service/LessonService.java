package exer.server.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import exer.server.domain.Lesson;
import exer.server.domain.Lesson;

public class LessonService extends AbstractService<Lesson>{
  
  @SuppressWarnings("unchecked")
  public void loadData(String filePath) {
    try (ObjectInputStream out = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(filePath))); ){
      list = (ArrayList<Lesson>) in.readObject();
    } catch(Exception e) {
      System.out.println("Lesson 데이터 로드중 오류발생 " + e.getMessage());
      list = new ArrayList<Lesson>();
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
  } // lessonList

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
