package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  
  public List<Board> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardMapper.findAll");
    }
  }

  
  public void insert(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.selectList("BoardMapper.insert", board);
    }
  }

  
  public Board findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.update("BoardMapper.increaseViewCount",no);
      sqlSession.commit();
      
      Board board = sqlSession.selectOne("BoardMapper.findByNo", no);
      return board;
    }
  }

  
  public int update(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int isUpdate = sqlSession.update("BoardMapper.update", board);
      sqlSession.commit();
      
      return isUpdate;
    }
  }

  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int isDelete = sqlSession.delete("BoardMapper.delete", no);
      sqlSession.commit();
      
      return isDelete;
    } 
  } // delete 
  
  
} // end of class









