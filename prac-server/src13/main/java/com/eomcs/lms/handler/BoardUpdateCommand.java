package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand extends AbstractCommand {

  BoardDao boardDao;

  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void execute(Response response) throws Exception {

    Board board = new Board();
    int no = response.requestInt("번호 ? :");
    
    Board old = boardDao.findByNo(no);
    
    board.setNo(no);
    board.setContents(response.requestString(
        String.format("내용(%s) ? : ",  old.getContents())));
    
    //l 내용이 있어야 update 단, 번호가 없으면 내용이 있어도 update 당연히 x
    //l 내용이 없으면 번호 유효성 검사도 없이 그냥 취소 ㄱㄱ
    if(board.getContents().length() > 0) {
      if (boardDao.update(board) == 0) {
        response.println("해당 번호의 게시물이 없습니다.");
        return;
      }else {
        response.println("변경 했습니다");
      }
    } else {
      response.println("변경을 취소했습니다");
    }
  } // execute



} // end of class
