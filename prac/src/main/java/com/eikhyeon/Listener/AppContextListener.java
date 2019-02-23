package com.eikhyeon.Listener;

import java.util.HashMap;

public interface AppContextListener {
  
  void init(HashMap<String, Object> observers);
  void destroy(HashMap<String, Object> observers);
}
