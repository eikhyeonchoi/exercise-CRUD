package com.eikhyeon.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface Command {
  default void execute() {
    
  }
  default void execute(PrintWriter out, BufferedReader in) {
    
  }
}
