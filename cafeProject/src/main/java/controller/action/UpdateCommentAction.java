package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import comment.Comment;
import comment.controller.CommentDao;

public class UpdateCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String commentContent = request.getParameter("comment_content");
		
		commentContent = commentContent.replace("\r\n", "<br>").replace("\n", "<br>");
		int commentSeq = Integer.parseInt(request.getParameter("comment_seq"));
		
		CommentDao dao = CommentDao.getInstance();
		Comment comment = dao.updateCommentContentBycommentSeq(commentContent, commentSeq);
		
		JSONObject obj = new JSONObject();
		obj.put("commentSeq",comment.getCommentSeq());
		obj.put("reviewNumber",comment.getReviewNumber());
		obj.put("userId",comment.getUserId());
		obj.put("content",comment.getCommentContent());
		obj.put("postDate",comment.getCommentPostDate());
		obj.put("editDate",comment.getCommentEditDate());
		
		response.getWriter().print(obj);
		response.getWriter().flush();
	}

}
