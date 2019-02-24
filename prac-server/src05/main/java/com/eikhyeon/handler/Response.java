package com.eikhyeon.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;

public class Response {
  PrintWriter out;
  BufferedReader in;
  
  public Response(PrintWriter out, BufferedReader in) {
    this.out = out;
    this.in = in;
  }
  
  public void justPrintln(String message) {
    out.println(message);
    out.flush();
  }
  
 
  public String requestString(String message) throws Exception {
    out.println(message);
    out.println("!{}!");
    out.flush();
    return in.readLine();
  }
  
  public int requestInt(String message) throws Exception {
    return Integer.parseInt(requestString(message));
  }
  public Date requestDate(String message) throws Exception {
    return Date.valueOf(requestString(message));
  }
 
}
