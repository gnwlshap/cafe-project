package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import comment.Comment;
import comment.controller.CommentDao;

public class SearchCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		int reviewNumber = Integer.parseInt(request.getParameter("review_number"));

		CommentDao dao = CommentDao.getInstance();
		ArrayList<Comment> list = dao.getCommentByReviewNumber(reviewNumber);
		
		JSONArray data = new JSONArray();
		
		for(Comment x : list) {
			JSONObject obj = new JSONObject();
			obj.put("commentSeq", x.getCommentSeq());
			obj.put("userId", x.getUserId());
			obj.put("commentContent", x.getCommentContent());
			obj.put("commentPostDate", x.getCommentPostDate().toString().subSequence(0,10));
			obj.put("review_number", reviewNumber);
			data.put(obj);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		out.print(data.toString());
		out.flush();
	}

}
