package com.eikhyeon.dao;

import java.util.List;
import com.eikhyeon.domain.Board;

public interface BoardDao {

  public void insert(Board board);
  public List<Board> findAll();
  public Board findByNo(int no);
  public int update(Board board);
  public int delete(int no);
}
