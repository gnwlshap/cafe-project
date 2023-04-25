package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.CommentDto;
import comment.controller.CommentDao;
import user.User;

public class CreateCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("log");
		int reviewNumber = Integer.parseInt(request.getParameter("review_number"));
		String userId = user.getUserId();
		String commentContent = request.getParameter("comment_content");

		commentContent = commentContent.replace("\r\n", "<br>").replace("\n", "<br>");
		
		CommentDao dao = CommentDao.getInstance();
		CommentDto comment = new CommentDto(reviewNumber, userId, commentContent);
		dao.createComment(comment);
	}

}
