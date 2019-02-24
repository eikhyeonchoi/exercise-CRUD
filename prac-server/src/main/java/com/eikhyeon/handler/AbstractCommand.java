package com.eikhyeon.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class AbstractCommand implements Command{
 
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      execute(new Response(out, in));
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public void execute(Response response) throws Exception{
    
  }

}
