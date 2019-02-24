package com.eikhyeon.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eikhyeon.domain.Board;
import com.eikhyeon.domain.Member;

public class MemberDaoImpl implements MemberDao{

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_member(name, email, pwd, photo, tel, cdt)"
            + " values(?, ?, password(?), ?, ?, ?)") ){
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoto());
      stmt.setString(5, member.getTel());
      stmt.setDate(6, member.getRegisteredDate());

      stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } //insert(1)

  @Override
  public List<Member> findAll() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from lms_member") ){

      try(ResultSet rs = stmt.executeQuery()) {
        ArrayList<Member> list = new ArrayList<Member>();

        while(rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setPhoto(rs.getString("photo"));
          member.setTel(rs.getString("tel"));
          member.setRegisteredDate(rs.getDate("cdt"));
          list.add(member);
        }
        return list;
      }
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // findAll();

  @Override
  public Member findByNo(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from lms_member where member_id = ?") ){
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if(rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setPhoto(rs.getString("photo"));
          member.setTel(rs.getString("tel"));
          member.setRegisteredDate(rs.getDate("cdt"));
          return member;
        } else return null;
      }
    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // findByNo(1)

  @Override
  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update lms_member set name =?, email = ?, pwd = ?, photo = ? ,"
        + " tel = ?, cdt = ? where member_id = ?") ){
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoto());
      stmt.setString(5, member.getTel());
      stmt.setDate(6, member.getRegisteredDate());
      stmt.setInt(7, member.getNo());

      return stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  } // update(1)

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from lms_member where member_id = ?") ){
      stmt.setInt(1, no);

      return stmt.executeUpdate();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }





}
