package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  
  SqlSessionFactory sqlSessionFactory;
  
  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  @Override
  public List<PhotoBoard> findAll(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoBoardMapper.findAll", no);
    }
  }

  @Override
  public void insert(PhotoBoard photoBoard) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("PhotoBoardMapper.insert", photoBoard);
      sqlSession.commit();
      
    }
  }

  @Override
  public PhotoBoard findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.update("PhotoBoardMapper.increaseViewCount", no);
      sqlSession.commit();
      PhotoBoard photoBoard = sqlSession.selectOne(
          "PhotoBoardMapper.findByNo", no);
      return photoBoard;
    }
  }
  
  public PhotoBoard findByNoWithFile(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.update("PhotoBoardMapper.increaseViewCount", no);
      sqlSession.commit();
      PhotoBoard photoBoard = sqlSession.selectOne(
          "PhotoBoardMapper.findByNoWithFile", no);
      return photoBoard;
    }
  }
  
  @Override
  public int update(PhotoBoard photoBoard) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int isUpdate = sqlSession.update("PhotoBoardMapper.update", photoBoard);
      sqlSession.commit();
      return isUpdate;
    }
  }
  
  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int isDelete = sqlSession.delete("PhotoBoardMapper.delete", no);
      sqlSession.commit();
      return isDelete;
    }
  }
}
