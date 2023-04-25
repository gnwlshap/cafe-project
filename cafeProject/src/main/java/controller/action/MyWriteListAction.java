package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.Review;
import review.controller.ReviewDao;

public class MyWriteListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		ReviewDao dao = ReviewDao.getInstance();
		int count = dao.getReviewCount();
		ArrayList<Review> list = dao.getReviewByUserId(userId, 0, count);			
		
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("mywrite").forward(request, response);
	}

}
