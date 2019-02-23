package com.eikhyeon.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eikhyeon.domain.Board;

public class BoardDaoImpl implements BoardDao{

  Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_board(conts) values(?)") ){
      stmt.setString(1, board.getContents());

      stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } //insert(1)

  @Override
  public List<Board> findAll() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from lms_board") ){

      try(ResultSet rs = stmt.executeQuery()) {
        ArrayList<Board> list = new ArrayList<Board>();

        while(rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setContents(rs.getString("conts"));
          board.setCreatedDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));
          list.add(board);
        }
        return list;
      }
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // findAll();

  @Override
  public Board findByNo(int no) {
    try {
      try (PreparedStatement stmt = con.prepareStatement(
          "update lms_board set vw_cnt = vw_cnt + 1 where board_id = ?")){
        stmt.setInt(1, no);
        stmt.executeUpdate();
      }
      try (PreparedStatement stmt = con.prepareStatement(
          "select * from lms_board where board_id = ?") ){
        stmt.setInt(1, no);
        try (ResultSet rs = stmt.executeQuery()) {
          if(rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_id"));
            board.setContents(rs.getString("conts"));
            board.setCreatedDate(rs.getDate("cdt"));
            board.setViewCount(rs.getInt("vw_cnt"));
            return board;
          } else return null;
        }
      }
    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // findByNo(1)

  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update lms_board set conts = ? where board_id = ?") ){
      stmt.setString(1, board.getContents());
      stmt.setInt(2, board.getNo());
      
      return stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // update(1)

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from lms_board where board_id = ?") ){
      stmt.setInt(1, no);

      return stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }





}
