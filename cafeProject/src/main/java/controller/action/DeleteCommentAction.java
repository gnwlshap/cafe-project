package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.controller.CommentDao;

public class DeleteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int commentSeq = Integer.parseInt(request.getParameter("comment_seq")); 
		
		CommentDao dao = CommentDao.getInstance();
		dao.deleteCommenBycommentSeq(commentSeq);		
	}

}
