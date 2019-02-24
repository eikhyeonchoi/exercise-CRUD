package com.eikhyeon.dao;

import java.util.List;
import com.eikhyeon.domain.Member;

public interface MemberDao {

  public void insert(Member member);
  public List<Member> findAll();
  public Member findByNo(int no);
  public int update(Member member);
  public int delete(int no);
}
