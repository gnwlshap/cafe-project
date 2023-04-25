package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.Review;
import review.controller.ReviewDao;

public class ReadReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reviewNumber = Integer.parseInt(request.getParameter("review_number"));
		ReviewDao dao = ReviewDao.getInstance();
		Review review = dao.getReviewByReviewNumberOnlyData(reviewNumber);
		
		request.setAttribute("review", review);
		request.getRequestDispatcher("reviewdetail").forward(request, response);		
	}

}
